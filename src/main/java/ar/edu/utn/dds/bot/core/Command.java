package ar.edu.utn.dds.bot.core;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface Command {
    String name();
    void execute(Update update, DdsBot bot) throws Exception;
}
