package com.study.onlineshop.configuration;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class  ServerConfiguration {

        private static ServerConfiguration serverConfiguration;
        private final Map<String,String> applicationProperties;

        public static ServerConfiguration load(String file) {
            if (serverConfiguration == null)
                serverConfiguration = new ServerConfiguration(file);
            return serverConfiguration;
        }

        public static ServerConfiguration get() {
            return serverConfiguration;
        }

        public String getProperty(String property) {
            String value = applicationProperties.get(property);
            return value;
        }

        private ServerConfiguration(String file) {
            applicationProperties = new HashMap<>();
            try(BufferedReader propertyFile = new BufferedReader(new FileReader(file))){
                String propertyLine;
                while((propertyLine = propertyFile.readLine()) != null) {
                    if (!propertyLine.trim().isEmpty() && !propertyLine.startsWith("//")) {
                        try {
                            String[] property = propertyLine.split("=", 2);
                            applicationProperties.put(property[0].trim(), property[1].trim());
                        } catch (IndexOutOfBoundsException e1) {

                        }
                    }
                }
            }catch (IOException e){
                e.printStackTrace();
                throw new RuntimeException("Unable load configuration from " + file,e);
            }
        }

}
