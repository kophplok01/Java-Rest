package com.webops.automation.java.testing.helpers;
import lombok.SneakyThrows;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class Helpers {

    @SneakyThrows
    public static String getParameterValue(String location, String parametername) {
        location = URLDecoder.decode(location, StandardCharsets.UTF_8.name());
        location = location.substring(location.indexOf(parametername) + parametername.length()+1);
        try {
            location = location.substring(0, location.indexOf('&'));
        } catch (StringIndexOutOfBoundsException ignored) {}
        return location;
    }
}