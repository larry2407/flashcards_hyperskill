import java.util.Scanner;
import java.time.LocalTime;

class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String[] input = sc.nextLine().trim().split(":");
        int h = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        int counterClockH = sc.nextInt();
        int counterClockM = sc.nextInt();
        LocalTime result = counterClockwise(h, m, counterClockH, counterClockM);
        System.out.println(result);

    }

    private static LocalTime counterClockwise(int h, int m, int counterClockH, int counterClockM) {
        LocalTime time = LocalTime.of(h, m);
        return time.minusHours(counterClockH).minusMinutes(counterClockM);
    }
}
/*
import java.time.LocalTime;
import java.util.Scanner;

public class Main {

    public static void main(final String[] args) {
        final Scanner sc = new Scanner(System.in);

        final String timeString = sc.next();
        final LocalTime time = LocalTime.parse(timeString).withSecond(0);
        final long hours = sc.nextLong();
        final long minutes = sc.nextLong();
        final LocalTime timeBefore = time.minusHours(hours).minusMinutes(minutes);

        System.out.println(timeBefore.toString());
    }
}

 */