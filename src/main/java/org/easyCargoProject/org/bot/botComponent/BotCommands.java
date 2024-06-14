package org.easyCargoProject.org.bot.botComponent;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;

public interface BotCommands {
    List<BotCommand> LIST_OF_COMMANDS = List.of(
            new BotCommand("/start", "start bot"),
            new BotCommand("/help", "bot info"),
            new BotCommand("/create_car", "Create new car"),
            new BotCommand("/cargo_creation", "Create new cargo"),
            new BotCommand("/getCarByName", "Car info by car name")
    );

    String HELP_TEXT = "This bot carries out transportation, " +
            "it will help you create a truck of the size you need, " +
            "as well as create and load cargo.\n\n " +
            "The following commands are available to you:\n\n" +
            "/start - start the bot\n" +
            "/help - help menu\n" +
            "/create_car - create new test car\n" +
            "/cargo_creation - create new cargo\n" +
            "/getCarByName - get car info\n";


    String EXIT_CAR_CREATION_TEXT = "Процесс создания прерван, введеные данные удалены.";

    String CAR_SET_NAME = "Введите имя для новой машины: ";

    String HELP_TEXT_CAR_NAME = "Введите жейлаймое имя для новой машины \n/exit - что бы прервать процесс создания машины";

    String CAR_SET_W = "Введите желаймую ширину для новой машины: ";

    String HELP_TEXT_CAR_W = "Введите жейлаймую ширину для новой машины \n/exit - что бы прервать процесс создания машины";

    String CAR_SET_H = "Введите жейлаймую длинну для новой машины: ";

    String HELP_TEXT_CAR_H = "Введите жейлаймую длину для новой машины \n/exit - что бы прервать процесс создания машины";

    String GET_CAR_BY_NAME = "Введите имя вашей машины";

    String CARGO_SET_NAME = "Введите жейлаймое имя для нового груза \n/exit - что бы прервать процесс создания груза";

    String HELP_TEXT_CARGO_NAME = "Введите жейлаймое имя для нового груза \n/exit - что бы прервать процесс создания груза";

    String CARGO_SET_SCHEME = "Введите схему груза через запяитую, где после каждой запятой новая строка" +
            "\nПример: 010,111,010 - крест" +
            "\n010\n111\n010";

    String CARGO_CAR_NAME = "Введите название машины к которой хотите привязать груз";

}
