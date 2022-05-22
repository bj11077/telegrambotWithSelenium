package june.telegrambot.bot;

import june.telegrambot.selenium.WebDriverUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Slf4j
@RequiredArgsConstructor
public class JuneBot extends TelegramLongPollingBot {

        private final WebDriverUtil webDriverUtil;

        @Value("${telegram.bot.name}")
        private String botName;

        @Value("${telegram.bot.token}")
        private String botToken;

        @Value("${telegram.bot.chatId}")
        private String botChatId;


    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
      log.info("update id : {}",update.getMessage().getFrom().getId());
      log.info("get text : {}",update.getMessage().getText());
      String message = webDriverUtil.useDriver(update.getMessage().getText());
        try {
            sendMessage(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String sendMessage) throws TelegramApiException {
        log.info("create SendMessage");
        SendMessage message = new SendMessage();
        message.setChatId(botChatId);
        message.setText(sendMessage);
        execute(message);
    }

}
