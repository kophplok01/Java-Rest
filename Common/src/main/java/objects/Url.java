package objects;

public class Url {
    public static void main(String[] args) {}

    public static String getBaseUrl() {
        return "https://" + PropertiesFile.Urls.read(
                System.getProperty("tenant") + ".url." + System.getProperty("environment"));
    }
}