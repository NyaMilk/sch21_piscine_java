import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = scanner.nextInt();

        int count = 1;

        boolean flag = true;

        if (number <= 1) {
            System.err.println("IllegalArgument");
            System.exit(-1);
        }

        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                flag = !flag;
                break;
            }
            count++;
        }

        scanner.close();

        System.out.println(flag + " " + count);
    }
}