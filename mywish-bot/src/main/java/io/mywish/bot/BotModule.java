package io.mywish.bot;

import io.mywish.bot.service.MyWishBot;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.telegram.telegrambots.ApiContext;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.bots.DefaultBotOptions;

@ComponentScan
@Configuration
@PropertySource("classpath:module.properties")
public class BotModule {
    static {
        ApiContextInitializer.init();
    }

    @Value("${io.mywish.bot.http-proxy:#{null}}")
    private String proxy;

    @Bean
    public TelegramBotsApi telegramBotsApi() {
        return new TelegramBotsApi();
    }

    @Bean
    public MyWishBot myWishBot() {
        DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
        if (proxy != null) {
            botOptions.setRequestConfig(
                    RequestConfig
                            .custom()
                            .setProxy(HttpHost.create(proxy))
                            .setAuthenticationEnabled(false)
                            .build()
            );
        }
        return new MyWishBot(botOptions);
    }
}
