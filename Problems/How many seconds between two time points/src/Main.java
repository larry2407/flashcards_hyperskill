import java.time.LocalTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LocalTime t1 = LocalTime.parse(scanner.nextLine().trim());
        LocalTime t2 = LocalTime.parse(scanner.nextLine().trim());
        int deltaSecs = t2.toSecondOfDay() - t1.toSecondOfDay();
        System.out.println(Math.abs(deltaSecs));
    }
}