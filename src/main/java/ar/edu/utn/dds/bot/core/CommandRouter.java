package ar.edu.utn.dds.bot.core;

import ar.edu.utn.dds.bot.modules.agregador.AgregadorCommand;
import ar.edu.utn.dds.bot.modules.fuente.FuenteCommand;
import ar.edu.utn.dds.bot.modules.pdi.PdiCommand;
import ar.edu.utn.dds.bot.modules.solicitud.SolicitudCommand;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.util.LinkedHashMap;
import java.util.Map;

public class CommandRouter {
    private final Map<String, Command> commands = new LinkedHashMap<>();

    public CommandRouter() {
        register(new AgregadorCommand());
        register(new FuenteCommand());
        register(new PdiCommand());
        register(new SolicitudCommand());
    }

    public void register(Command cmd) {
        commands.put(cmd.name(), cmd);
    }

    public boolean route(Update update, DdsBot bot) throws Exception {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText().trim();
            if ("/start".equalsIgnoreCase(text)) {
                bot.sendMainMenu(update.getMessage().getChatId());
                return true;
            }
        }
        if (update.hasCallbackQuery()) {
            String key = update.getCallbackQuery().getData();
            Command cmd = commands.get(key);
            if (cmd != null) {
                cmd.execute(update, bot);
                return true;
            }
        }
        return false;
    }
}
