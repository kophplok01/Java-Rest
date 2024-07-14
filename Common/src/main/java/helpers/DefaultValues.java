package helpers;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ThreadLocalRandom;


public enum DefaultValues {;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom random = new SecureRandom();

    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }

    public static String generateRandomGtin() {
        int length = 14;
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt("0123456789".length());
            sb.append("0123456789".charAt(randomIndex));
        }
        return sb.toString();
    }

    public static String getCurrentDateInISOFormat() {
        ZonedDateTime now = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
        return now.format(formatter);
    }

    public static String getRandomDateBeforeTodayInISOFormat() {
        LocalDate today = LocalDate.now();
        LocalDate start = LocalDate.of(1900, 1, 1); // or any date in the past
        long days = start.until(today, ChronoUnit.DAYS);
        long randomDays = ThreadLocalRandom.current().nextLong(days);
        LocalDate randomDate = start.plusDays(randomDays);
        ZonedDateTime randomDateTime = randomDate.atStartOfDay(ZoneId.systemDefault());
        return randomDateTime.format(DateTimeFormatter.ISO_INSTANT);
    }

    public static String getCurrentDateTime(int daysToAdd) {
        LocalDateTime now = LocalDateTime.now().plusDays(daysToAdd);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return now.format(formatter);
    }

    public static String getRandomDateAfterTodayInISOFormat() {
        LocalDate today = LocalDate.now();
        LocalDate end = today.plusYears(100); // or any date in the future
        long days = today.until(end, ChronoUnit.DAYS);
        long randomDays = ThreadLocalRandom.current().nextLong(days);
        LocalDate randomDate = today.plusDays(randomDays);
        ZonedDateTime randomDateTime = randomDate.atStartOfDay(ZoneId.systemDefault());
        return randomDateTime.format(DateTimeFormatter.ISO_INSTANT);
    }
}