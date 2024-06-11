package org.easyCargoProject.org.config;

import org.easyCargoProject.org.bot.TelegramBotStarter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringAppConfig {
    @Value("${telegram.bot.username}")
    private String TELEGRAM_BOT_USERNAME;
    @Value("${telegram.bot.token}")
    private String TELEGRAM_BOT_TOKEN;
    @Value("${spring.datasource.url}")
    private String DB_URL;
    @Value("${spring.datasource.username}")
    private String DB_USERNAME;
    @Value("${spring.datasource.password}")
    private String DB_PASSWORD;


    @Bean
    public TelegramBotStarter telegramBotStarter() {
        TelegramBotStarter telegramBotStarter = new TelegramBotStarter();
        telegramBotStarter.startBot(TELEGRAM_BOT_TOKEN, TELEGRAM_BOT_USERNAME);
        return telegramBotStarter;
    }
}