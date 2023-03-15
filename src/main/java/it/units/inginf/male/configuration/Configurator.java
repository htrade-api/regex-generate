package it.units.inginf.male.configuration;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author nvhuy9527
 */
public class Configurator {
    public static Configuration configure(String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(new File(fileName));
        InputStreamReader isr = new InputStreamReader(fis);
        StringBuilder sb;
        try (BufferedReader bufferedReader = new BufferedReader(isr)) {
            sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        }
        String json = sb.toString();
        return configureFromJson(json);
    }
    
    public static Configuration configureFromJson(String jsonConfiguration){
        Gson gson = new Gson();
        Configuration configuration = gson.fromJson(jsonConfiguration, Configuration.class);
        configuration.setup();
        return configuration;
    }
}
