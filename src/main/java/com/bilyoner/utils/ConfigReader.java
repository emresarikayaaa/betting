package com.bilyoner.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = new Properties();
    // Properties: key=value formatındaki dosyaları okumak için Java'nın built-in sınıfı

    static {
        // static block: sınıf ilk yüklendiğinde bir kez çalışır
        try {
            FileInputStream file = new FileInputStream("src/main/resources/config.properties");
            // config.properties dosyasını aç

            properties.load(file);
            // Dosyadaki tüm key=value çiftlerini belleğe yükle

        } catch (IOException e) {
            throw new RuntimeException("config.properties bulunamadı!", e);
            // Dosya bulunamazsa test başlamadan hata fırlat
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
        // Kullanım: ConfigReader.get("deviceName") → "emulator-5554"
        // Kullanım: ConfigReader.get("appiumServerUrl") → "http://127.0.0.1:4723"
    }
}