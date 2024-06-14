package org.easyCargoProject.org.bot;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.easyCargoProject.org.bot.botComponent.BotCommands;
import org.easyCargoProject.org.config.BotConfig;
import org.easyCargoProject.org.database.CarRepository;
import org.easyCargoProject.org.database.CargoRepository;
import org.easyCargoProject.org.entity.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import org.easyCargoProject.org.entity.Car;

@Slf4j
@Component
public class CargoCarTelegramBot extends TelegramLongPollingBot implements BotCommands {
    final BotConfig config;

    String carFlagOfCreation = "null";
    String cargoFlagOfCreation = "null";
    String carManipulationFlag = "null";

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CargoRepository cargoRepository;

    String carName = "";
    int carW = 0;
    int carH = 0;

    String cargoName = "";
    String cargoScheme = "111,010,111";
    int cargoCarId = 0;

    public CargoCarTelegramBot(BotConfig config) {
        this.config = config;
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(@NotNull Update update) {
        long chatId = 0;
        long userId = 0;
        String userName = null;
        String receivedMessage;

        if (update.hasMessage()) {
            chatId = update.getMessage().getChatId();
            userId = update.getMessage().getFrom().getId();
            userName = update.getMessage().getFrom().getFirstName();

            if (update.getMessage().hasText()) {
                receivedMessage = update.getMessage().getText();
                botAnswerUtils(receivedMessage, chatId, userName);
            }
        } else if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
            userId = update.getCallbackQuery().getFrom().getId();
            userName = update.getCallbackQuery().getFrom().getFirstName();
            receivedMessage = update.getCallbackQuery().getData();

            botAnswerUtils(receivedMessage, chatId, userName);
        }
    }

    private void botAnswerUtils(String receivedMessage, long chatId, String userName) {
        if (carFlagOfCreation != "null") {
            if (carFlagOfCreation == "CAR_NAME") {
                switch (receivedMessage) {
                    case "/exit":
                        carFlagOfCreation = "null";
                        sendHelpText(chatId, EXIT_CAR_CREATION_TEXT);
                        break;
                    case "/help":
                        sendHelpText(chatId, HELP_TEXT_CAR_NAME);
                        break;
                    default:
                        carName = receivedMessage;
                        carFlagOfCreation = "CAR_WIDTH";
                        sendHelpText(chatId, CAR_SET_W);
                        break;
                }
            } else if (carFlagOfCreation == "CAR_WIDTH") {
                switch (receivedMessage) {
                    case "/exit":
                        carFlagOfCreation = "null";
                        carName = "";
                        sendHelpText(chatId, EXIT_CAR_CREATION_TEXT);
                        break;
                    case "/help":
                        sendHelpText(chatId, HELP_TEXT_CAR_W);
                        break;
                    default:
                        carW = Integer.parseInt(receivedMessage);
                        sendHelpText(chatId, CAR_SET_H);
                        carFlagOfCreation = "CAR_HEIGHT";
                        System.out.println("TEST 1" + carH + " " + carW);
                        break;
                }
            } else if (carFlagOfCreation == "CAR_HEIGHT")
                switch (receivedMessage) {
                    case "/exit":
                        carFlagOfCreation = "null";
                        carName = "";
                        carW = 0;
                        sendHelpText(chatId, EXIT_CAR_CREATION_TEXT);
                        break;
                    case "/help":
                        sendHelpText(chatId, HELP_TEXT_CAR_H);
                        break;
                    default:
                        System.out.println("TEST 2" + " " + receivedMessage);
                        carH = Integer.parseInt(receivedMessage);
                        sendHelpText(chatId, "Машина " + carName + " успешно создана\n"
                                + "Ширина: " + carW + "\n"
                                + "Длинна: " + carH + "\n");
                        Car car = new Car(carName, carW, carH, chatId);
                        carRepository.save(car);
                        carFlagOfCreation = "null";
                        break;
                }
            else {
                switch (receivedMessage) {
                    case "/start":
                        startBot(chatId, userName);
                        break;
                    case "/help":
                        sendHelpText(chatId, HELP_TEXT);
                        break;
                    case "/create_car":
                        carFlagOfCreation = "CAR_NAME";
                        sendCarName(chatId, CAR_SET_NAME);
                        break;
                    case "/getCarByName":
                        carManipulationFlag = "GET_CAR_BY_NAME";
                        sendCarName(chatId, GET_CAR_BY_NAME);
                        break;
                    case "/cargo_creation":
                        cargoFlagOfCreation = "CARGO_NAME";
                        sendCarName(chatId, CARGO_SET_NAME);
                        break;
                    default:
                        sendHelpText(chatId, HELP_TEXT);
                        break;
                }
            }
        } else if (cargoFlagOfCreation != "null") {
            if (cargoFlagOfCreation == "CARGO_NAME") {
                switch (receivedMessage) {
                    case "/exit":
                        cargoFlagOfCreation = "null";
                        sendHelpText(chatId, EXIT_CAR_CREATION_TEXT);
                        break;
                    case "/help":
                        sendHelpText(chatId, HELP_TEXT_CARGO_NAME);
                        break;
                    default:
                        cargoName = receivedMessage;
                        System.out.println("\nTest 0\n");
                        cargoFlagOfCreation = "CARGO_SCHEME";
                        sendHelpText(chatId, CARGO_SET_SCHEME);
                        break;
                }
            } else if (cargoFlagOfCreation == "CARGO_SCHEME") {
                System.out.println("\nTest 1\n");
                switch (receivedMessage) {
                    case "/exit":
                        cargoFlagOfCreation = "null";
                        carName = "";
                        sendHelpText(chatId, EXIT_CAR_CREATION_TEXT);
                        break;
                    case "/help":
                        sendHelpText(chatId, HELP_TEXT_CAR_W);
                        break;
                    default:
                        System.out.println("\nTest 2\n");
                        cargoScheme = (receivedMessage);
                        sendHelpText(chatId, CARGO_CAR_NAME);
                        cargoFlagOfCreation = "CARGO_CAR_NAME";
                        System.out.println("TEST 1" + carH + " " + carW);
                        break;
                }
            } else if (cargoFlagOfCreation == "CARGO_CAR_NAME")
                switch (receivedMessage) {
                    case "/exit":
                        cargoFlagOfCreation = "null";
                        cargoName = "";
                        cargoScheme = "";
                        sendHelpText(chatId, EXIT_CAR_CREATION_TEXT);
                        break;
                    case "/help":
                        sendHelpText(chatId, CARGO_CAR_NAME + "\n /exit - что бы прервать процесс");
                        break;
                    default:
                        cargoCarId = carRepository.findByName(receivedMessage).getId();
                        Cargo cargo = new Cargo(cargoName, cargoScheme, chatId, cargoCarId);
                        cargoRepository.save(cargo);
                        sendHelpText(chatId, "Груз " + cargoName + " успешно создан\n");
                        cargoFlagOfCreation = "null";
                        break;
                }
        } else if (carManipulationFlag != "null") {
            if (carManipulationFlag == "GET_CAR_BY_NAME") {
                switch (receivedMessage) {
                    case "/exit":
                        carManipulationFlag = "null";
                        sendHelpText(chatId, EXIT_CAR_CREATION_TEXT);
                        break;
                    default:
                        sendHelpText(chatId, "\nПо вашему запросу найдена машина: "
                                + carRepository.findByName(receivedMessage).getName());
                        carManipulationFlag = "null";
                        break;
                }
            }
        } else {
            switch (receivedMessage) {
                case "/start":
                    startBot(chatId, userName);
                    break;
                case "/help":
                    sendHelpText(chatId, HELP_TEXT);
                    break;
                case "/create_car":
                    carFlagOfCreation = "CAR_NAME";
                    sendCarName(chatId, CAR_SET_NAME);
                    break;
                case "/getCarByName":
                    carManipulationFlag = "GET_CAR_BY_NAME";
                    sendCarName(chatId, GET_CAR_BY_NAME);
                    break;
                case "/cargo_creation":
                    cargoFlagOfCreation = "CARGO_NAME";
                    sendCarName(chatId, CARGO_SET_NAME);
                    break;
                default:
                    sendHelpText(chatId, HELP_TEXT);
                    break;
            }
        }

    }

    private void sendHelpText(long chatId, String textToSpend) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textToSpend);

        try {
            execute(message);
            log.info("Reply sent");
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendCarName(long chatId, String textToSpend) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textToSpend);

        try {
            execute(message);
            log.info("Reply sent");
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void startBot(long chatId, String userName) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Hello, " + userName + "! I'm a Telegram bot.");

        try {
            execute(message);
            log.info("Reply sent");
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
}



