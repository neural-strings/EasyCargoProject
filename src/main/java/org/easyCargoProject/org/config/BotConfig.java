package org.easyCargoProject.org.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.beans.factory.annotation.Value;

@Configuration
@Data
@PropertySource("application.properties")
public class BotConfig {
    @Value("${telegram.bot.username}") String botName;
    @Value("${telegram.bot.token}") String token;
}