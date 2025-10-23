package ar.edu.utn.dds.bot.modules.solicitud;

import ar.edu.utn.dds.bot.core.Command;
import ar.edu.utn.dds.bot.core.DdsBot;
import ar.edu.utn.dds.bot.core.Texts;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class SolicitudCommand implements Command {
    @Override public String name() { return "SOLICITUD"; }
    @Override
    public void execute(Update update, DdsBot bot) throws Exception {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        bot.notifyCallbackOk(update);
        bot.execute(SendMessage.builder().chatId(chatId.toString()).text(Texts.SOLICITUD_WELCOME + "\n(Pronto: submenú, acciones, etc.)").build());
    }
}
