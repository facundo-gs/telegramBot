package ar.edu.utn.dds.bot.core;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import java.util.List;

public class KeyboardFactory {
    public static InlineKeyboardMarkup mainMenu() {
        InlineKeyboardButton b1 = InlineKeyboardButton.builder().text("1) Agregador").callbackData("AGREGADOR").build();
        InlineKeyboardButton b2 = InlineKeyboardButton.builder().text("2) Fuente").callbackData("FUENTE").build();
        InlineKeyboardButton b3 = InlineKeyboardButton.builder().text("3) PdI").callbackData("PDI").build();
        InlineKeyboardButton b4 = InlineKeyboardButton.builder().text("4) Solicitud").callbackData("SOLICITUD").build();
        return InlineKeyboardMarkup.builder().keyboard(List.of(List.of(b1), List.of(b2), List.of(b3), List.of(b4))).build();
    }
}
