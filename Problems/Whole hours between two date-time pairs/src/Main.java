import java.util.Scanner;
import java.time.LocalDateTime;

class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String d1 = sc.nextLine().trim();
        String d2 = sc.nextLine().trim();
        LocalDateTime ldt1 = LocalDateTime.parse(d1);
        LocalDateTime ldt2 = LocalDateTime.parse(d2);
        int mins1 = 1440 *(ldt1.getDayOfYear() - 1) + 60 * ldt1.getHour() + ldt1.getMinute();
        int mins2 = 1440 *(ldt2.getDayOfYear() - 1) + 60 * ldt2.getHour() + ldt2.getMinute();
        int wholeHoursBetween = Math.abs(mins2 - mins1)/60;
        System.out.println(wholeHoursBetween);
    }
}
/*
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Scanner;

public class Main {

    public static void main(final String[] args) {
        final Scanner sc = new Scanner(System.in);

        final String dateTimeString1 = sc.next();
        final LocalDateTime dateTime1 = LocalDateTime.parse(dateTimeString1);

        final String dateTimeString2 = sc.next();
        final LocalDateTime dateTime2 = LocalDateTime.parse(dateTimeString2);

        final long secondsBetweenDates = Math.abs(dateTime2.toEpochSecond(ZoneOffset.UTC) - dateTime1.toEpochSecond(ZoneOffset.UTC));
        final long hoursBetweenDates = secondsBetweenDates / 3600;

        System.out.println(hoursBetweenDates);
    }
}
 */