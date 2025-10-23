package ar.edu.utn.dds.bot.core;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class DdsBot extends TelegramLongPollingBot {
    private final String username;
    private final CommandRouter router = new CommandRouter();

    public DdsBot(String username, String token) {
        super(token);
        this.username = username;
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            boolean handled = router.route(update, this);
            if (!handled && update.hasMessage() && update.getMessage().hasText()) {
                sendMainMenu(update.getMessage().getChatId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            try { notifyCallbackError(update); } catch (Exception ignore) {}
        }
    }

    public void sendMainMenu(Long chatId) throws Exception {
        SendMessage m = SendMessage.builder()
            .chatId(chatId.toString())
            .text(Texts.MAIN_TITLE + "\n1_Agregador\n2_Fuente\n3_Pdi\n4_Solicitud")
            .replyMarkup(KeyboardFactory.mainMenu())
            .build();
        execute(m);
    }

    public void notifyCallbackOk(Update update) throws Exception {
        if (update.hasCallbackQuery()) {
            execute(AnswerCallbackQuery.builder()
                .callbackQueryId(update.getCallbackQuery().getId())
                .text("OK")
                .showAlert(false)
                .build());
        }
    }

    private void notifyCallbackError(Update update) throws Exception {
        if (update.hasCallbackQuery()) {
            execute(AnswerCallbackQuery.builder()
                .callbackQueryId(update.getCallbackQuery().getId())
                .text("Ocurrió un error. Probá de nuevo.")
                .showAlert(true)
                .build());
        }
    }
}
