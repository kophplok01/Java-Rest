package objects;

import lombok.SneakyThrows;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Properties;

public enum PropertiesFile {
    Users("users.properties"),
    Data("data.properties"),
    Urls("urls.properties");

    private String fileName;
    private HashMap<String, String[]> savedProperties = new HashMap<>();

    PropertiesFile(String text) {
        this.fileName = text;
    }

    public String[] readProperties(String key) {
        if(!savedProperties.containsKey(key)){
            Properties p = readFile();
            if(!p.containsKey(key)) {
                throw new NullPointerException("Key '" + key + "' is missing from '" + this.fileName + "'");
            }
            savedProperties.put(key, p.getProperty(key).split(","));
        }

        return savedProperties.get(key);
    }

    public String read(String key, Integer index) {
        return readProperties(key)[index];
    }

    public String read(String key) {
        return readProperties(key)[0];
    }

    @SneakyThrows
    private Properties readFile() {
        Properties p = new Properties();
        String strFile = System.getProperty("user.dir");
        strFile = strFile.substring(0, strFile.lastIndexOf(File.separator));
        strFile = strFile + File.separator +
                "Common" + File.separator +
                "src" + File.separator +
                "main" + File.separator +
                "resources" + File.separator +
                this.fileName;
        p.load(new FileReader(strFile));
        return p;
    }
}
