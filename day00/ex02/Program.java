import java.util.Scanner;

public class Program {
    private static final int END_COUNT = 42;

    public static int additionNumbers(int number) {
        int result = 0;

        while (number > 0) {
            result += number % 10;
            number /= 10;
        }

        return result;
    }

    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }

        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = 0;

        int count = 0;

        while ((number = scanner.nextInt()) != END_COUNT) {
            number = additionNumbers(number);
            if (isPrime(number)) {
                count++;
            }
        }

        scanner.close();

        System.out.println("Count of coffee-request – " + count);
    }
}