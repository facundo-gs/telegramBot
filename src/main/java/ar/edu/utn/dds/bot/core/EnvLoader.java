package ar.edu.utn.dds.bot.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EnvLoader {
    private static final String ENV_PATH = ".env";

    public static void load() {
        Map<String, String> envMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ENV_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                String[] parts = line.split("=", 2);
                if (parts.length == 2) envMap.put(parts[0].trim(), parts[1].trim());
            }
        } catch (IOException e) {
            System.err.println("⚠️  No se encontró el archivo .env o no se pudo leer: " + ENV_PATH);
        }
        envMap.forEach((key, value) -> {
            if (System.getenv(key) == null) System.setProperty(key, value);
        });
    }

    public static String get(String key) {
        String val = System.getenv(key);
        if (val == null) val = System.getProperty(key);
        return val;
    }
}
