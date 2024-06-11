package org.easyCargoProject.org.bot;

import lombok.NoArgsConstructor;
import lombok.Setter;

import org.easyCargoProject.org.database.CarRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@NoArgsConstructor
@Setter
@Service
public class TelegramBotStarter {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(TelegramBotStarter.class);
    @Autowired
    CarRepository carRepository;

    public void startBot(String bot_token, String bot_username) {
        try {
            new TelegramBotsApi(DefaultBotSession.class)
                    .registerBot(
                            new TelegramBot(this, bot_token, bot_username, carRepository)
                    );
        } catch (TelegramApiException e) {
            logger.error("Ошибка запуска телеграм-бота. Подробнее: ", e);
        }
    }
}
