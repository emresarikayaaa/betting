package com.bilyoner.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

    public class DataProvider {

        private static final ObjectMapper mapper = new ObjectMapper();
        private static final Map<String, Map<String, Map<String, String>>> cache = new ConcurrentHashMap<>();

        public static Map<String, String> getData(String fileName, String key) {
            Map<String, Map<String, String>> fileData = cache.computeIfAbsent(fileName, DataProvider::loadFile);
            Map<String, String> data = fileData.get(key);
            if (data == null) {
                throw new RuntimeException("Test verisi bulunamadı: " + fileName + " -> " + key);
            }
            return data;
        }

        private static Map<String, Map<String, String>> loadFile(String fileName) {
            String path = "testdata/" + fileName + ".json";
            try (InputStream is = DataProvider.class.getClassLoader().getResourceAsStream(path)) {
                if (is == null) {
                    throw new RuntimeException("Test veri dosyası bulunamadı: " + path);
                }
                return mapper.readValue(is, new TypeReference<>() {});
            } catch (Exception e) {
                throw new RuntimeException("Test veri dosyası okunamadı: " + path, e);
            }
        }
    }

