package org.easyCargoProject.org.bot;

import org.easyCargoProject.org.bot.TelegramBotStarter;
import org.easyCargoProject.org.controller.CarController;
import org.easyCargoProject.org.database.CarRepository;
import org.easyCargoProject.org.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import org.easyCargoProject.org.service.CarService;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.List;


public class TelegramBot extends TelegramLongPollingBot {

    @Autowired
    CarService carService;

    @Autowired
    CarRepository carRepository;


    private final TelegramBotStarter telegramBotStarter;
    private final String token;
    private final String username;

    public TelegramBot(
            TelegramBotStarter telegramBotStarter,
            String bot_token,
            String bot_username,
            @Autowired
            CarRepository carRepository
    ) {
        this.telegramBotStarter = telegramBotStarter;
        this.username = bot_username;
        this.token = bot_token;
        this.carRepository = carRepository;
        System.out.println(carRepository);
    }

    String flagOfCarCreation;
    String flagOfCarOperation;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    String carName = null;
    int carWidth = 0;
    int carHeight = 0;

    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();
        Long chatID = message.getChatId();
        Document fileMessage = message.getDocument();


        String unknownCommand = "Неизвестная команда - "
                + message.getText()
                + "\nВоспользуйтесь командой /help для поиска доступных команд";

        if (message != null && message.hasText()) {
            if (flagOfCarCreation == null) {
                switch (message.getText()) {
                    case "/start":
                        mainMenu(message, "Добро пожаловать, данный бот создает грузовые машины!");
                        System.out.println(message.getText());
                        break;
                    case "/help":
                        mainMenu(message, "/setCargo - Создать грузовую машишу и заполнить ее данными" +
                                "\n" + "/getCargo - Получить данные о грузовой машине");
                        System.out.println(message.getText());
                        break;
                    case "/setCar":
                        carMakerStartMenu(message, "Введите имя для новой машины");
                        flagOfCarCreation = "name";
                        System.out.println(message.getText());
                        break;
                    case "/getCar":
                        carMakerStartMenu(message, "Введите имя машины:");
                        flagOfCarOperation = "getByName";
                        System.out.println(message.getText());
                        break;
                    default:
                        mainMenu(message, unknownCommand);
                        System.out.println(message.getText());
                        break;
                }
            } else if (flagOfCarCreation.equals("name")) {
                switch (message.getText()) {
                    case "/help":
                        carMakerStartMenu(message, "/exit - выйти из процесса создания машины");
                        System.out.println(message.getText());
                        break;
                    case "/exit":
                        carMakerStartMenu(message, "Процесс создания машины - прерван");
                        flagOfCarCreation = null;
                        break;
                    default:
                        carName = message.getText();
                        flagOfCarCreation = "width"; //переход от 1 -> 2
                        carMakerSetCarNameMessage(message, carName);
                }
            } else if (flagOfCarCreation.equals("width")) {
                switch (message.getText()) {
                    case "/help":
                        carMakerStartMenu(message, "/exit - выйти из процесса создания машины");
                        System.out.println(message.getText());
                        break;
                    case "/exit":
                        carMakerStartMenu(message, "Процесс создания машины - прерван");
                        flagOfCarCreation = null;
                        break;
                    default:
                        carWidth = Integer.parseInt(message.getText());
                        flagOfCarCreation = "height"; //переход от 2 -> 3
                        carMakerSetCarWidthMessage(message, carWidth);
                }
            } else if (flagOfCarCreation.equals("height")) {
                switch (message.getText()) {
                    case "/help":
                        carMakerStartMenu(message, "Введите любое число или введите команду - " +
                                "/exit - выйти из процесса создания машины");
                        System.out.println(message.getText());
                        break;
                    case "/exit":
                        carMakerStartMenu(message, "Процесс создания машины - прерван");
                        flagOfCarCreation = null;
                        break;
                    default:
                        carHeight = Integer.parseInt(message.getText());
                        flagOfCarCreation = "Save"; //переход от 3 -> Save
                        carMakerSetCarHeightMessage(message, carHeight);
                        System.out.println("Имя машины - " + carName);
                        Car car = new Car(carName, carWidth, carHeight, message.getChatId());
                        System.out.println(car.getName());
                        System.out.println(car.getChatId());
                        //car.setId(1);
                        //System.out.println(car.getId());
                        System.out.println(carRepository);
                        //carRepository.save(car);
                        flagOfCarCreation = null;
                        carMakerSuccessSaveMessage(message, car);
                        mainMenu(message, "Возвращение в главное меню.");
                }
            } else if (flagOfCarOperation.equals("getByName")) {
                switch (message.getText()) {
                    case "/help":
                        carMakerStartMenu(message, "Введите название машины или введите команду - " +
                                "/exit - выйти из процесса создания машины");
                        System.out.println(message.getText());
                        break;
                    case "/exit":
                        carMakerStartMenu(message, "Процесс создания машины - прерван");
                        flagOfCarCreation = null;
                        break;
//                    default:
//                        carMakerSetCarHeightMessage(message, carName);
//                        Car car =
//                        System.out.println("Имя мащины - " + carName);
                }
            } else {
                switch (message.getText()) {
                    default:
                        mainMenu(message, "Error - такого сценария не предусмотрено,"
                                + " откатываем создание/редактирование машины.");
                        flagOfCarCreation = null;
                        flagOfCarOperation = null;
                        System.out.println(message.getText());
                        break;
                }
            }
        }
    }

    public void mainMenu(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        // Создаем клавиатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new
                ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow(2);
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add("/getCar");
        keyboardFirstRow.add("/setCar");
        KeyboardRow keyboard2Row = new KeyboardRow(1);
        keyboard2Row.add("/help");

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboard2Row);
        // и устанавливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);

        sendMessage.setChatId(message.getChatId().toString());
        //sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void carMakerStartMenu(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        // Создаем клавиатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboard3Row = new KeyboardRow(2);
        keyboard3Row.add("/help");
        keyboard3Row.add("/exit");

        keyboard.add(keyboard3Row);
        replyKeyboardMarkup.setKeyboard(keyboard);

        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void carMakerSetCarNameMessage(Message message, String carName) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(carName + " - Имя вашей новой машины" +
                "\nвведите желаймую ширину новой машины");
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void carMakerSetCarWidthMessage(Message message, Integer carWidth) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(carWidth + " - ширина новой машины" +
                "\nвведите желаймую высоту новой машины");
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void carMakerSetCarHeightMessage(Message message, Integer carHeight) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(carHeight + " - высота новой машины");
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void cargoMakerSetWidthMessage(Message message, Integer cargoWidth) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(cargoWidth + " - ширина посылки");
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void cargoMakerSetHeightMessage(Message message, Integer cargoHeight) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(cargoHeight + " - ширина посылки");
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void carMakerSuccessSaveMessage(Message message, Car car) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText("Машина - " + car.getName() + " успешно создана");
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
