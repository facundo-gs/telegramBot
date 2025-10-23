package ar.edu.utn.dds.bot;

import ar.edu.utn.dds.bot.core.DdsBot;
import ar.edu.utn.dds.bot.core.EnvLoader;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class App {
    public static void main(String[] args) throws Exception {
        EnvLoader.load();
        String token = EnvLoader.get("TELEGRAM_BOT_TOKEN");
        String username = EnvLoader.get("TELEGRAM_BOT_USERNAME");
        if (token == null || token.isBlank() || username == null || username.isBlank()) {
            System.err.println("❌ Faltan TELEGRAM_BOT_TOKEN y/o TELEGRAM_BOT_USERNAME en el archivo .env");
            System.exit(1);
        }
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(new DdsBot(username, token));
        System.out.println("✅ Bot iniciado como @" + username);
    }
}
