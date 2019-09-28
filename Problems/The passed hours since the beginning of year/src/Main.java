import java.util.Scanner;
import java.time.LocalDateTime;

class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String dt1 = sc.next();
        LocalDateTime ldt1 = LocalDateTime.parse(dt1);
        int hours = (ldt1.getDayOfYear() - 1) * 24 + ldt1.getHour();
        System.out.println(hours);
    }
}
/*
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Scanner;

public class Main {

    public static void main(final String[] args) {
        final Scanner sc = new Scanner(System.in);

        final String dateTimeString = sc.next();
        final LocalDateTime dateTime = LocalDateTime.parse(dateTimeString);

        final LocalDateTime firstJanuary = LocalDateTime.of(dateTime.getYear(), 1, 1, 0, 0);
        final long dateTimeLong = dateTime.toEpochSecond(ZoneOffset.UTC);
        final long firstJanuaryLong = firstJanuary.toEpochSecond(ZoneOffset.UTC);

        final long betweenSeconds = dateTimeLong - firstJanuaryLong;
        final long betweenHours = betweenSeconds / 3600;

        System.out.println(betweenHours);
    }
}
 */