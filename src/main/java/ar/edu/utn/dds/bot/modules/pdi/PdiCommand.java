package ar.edu.utn.dds.bot.modules.pdi;

import ar.edu.utn.dds.bot.core.Command;
import ar.edu.utn.dds.bot.core.DdsBot;
import ar.edu.utn.dds.bot.core.Texts;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class PdiCommand implements Command {
    @Override public String name() { return "PDI"; }
    @Override
    public void execute(Update update, DdsBot bot) throws Exception {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        bot.notifyCallbackOk(update);
        bot.execute(SendMessage.builder().chatId(chatId.toString()).text(Texts.PDI_WELCOME + "\n(Pronto: submenú, acciones, etc.)").build());
    }
}
