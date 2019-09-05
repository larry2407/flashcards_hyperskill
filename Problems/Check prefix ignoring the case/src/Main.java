import java.util.Locale;
import java.util.Scanner;

class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println(sc.next().toLowerCase(Locale.ENGLISH).startsWith("j"));

    }
}