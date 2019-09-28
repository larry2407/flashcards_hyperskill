import java.util.Scanner;
import java.time.LocalTime;

class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String[] input = sc.nextLine().trim().split(":");
        int h = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        LocalTime result = LocalTime.of(h, m);
        System.out.println(result);

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

        System.out.println(time.toString());
    }
}

 */