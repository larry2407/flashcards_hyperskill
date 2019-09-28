import java.util.Scanner;
import java.time.LocalDateTime;

class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String dt1 = sc.next();
        int daysToAdd = sc.nextInt();
        int hoursToSubstract = sc.nextInt();

        LocalDateTime ld1 = LocalDateTime.parse(dt1);
        LocalDateTime ld2 = ld1.plusDays(daysToAdd).minusHours(hoursToSubstract);
        System.out.println(ld2);
    }
}
/*
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {

    public static void main(final String[] args) {
        final Scanner sc = new Scanner(System.in);

        final String dateTimeString = sc.next();
        final LocalDateTime dateTime = LocalDateTime.parse(dateTimeString);
        final long days = sc.nextLong();
        final long hours = sc.nextLong();
        final LocalDateTime newDateTime = dateTime.plusDays(days).minusHours(hours);

        System.out.println(newDateTime.toString());
    }
}
 */