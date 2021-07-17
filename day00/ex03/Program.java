import java.util.Scanner;

public class Program {
    private static final int COUNT_OF_WEEKS = 18;

    private static final int COUNT_OF_GRADES = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int weekNumber = 0;

        long allMinimumGrades = 0;

        while (weekNumber++ < COUNT_OF_WEEKS) {
            String week = scanner.next();

            if (week.equals("42")) {
                break;
            } else if (!week.equals("Week") || weekNumber != scanner.nextInt()) {
                System.err.println("IllegalArgument");
                System.exit(-1);
            }

            int tmp = 0;

            int currentMinimum = 9;

            for (int i = 0; i < COUNT_OF_GRADES; i++) {
                tmp = scanner.nextInt();

                if (tmp < 1 || tmp > 9) {
                    System.err.println("IllegalArgument");
                    System.exit(-1);
                }

                if (tmp < currentMinimum) {
                    currentMinimum = tmp;
                }
            }

            allMinimumGrades = allMinimumGrades * 10 + currentMinimum;
        }

        long allMinimumGradesRevers = 0;

        while (allMinimumGrades > 0) {
            allMinimumGradesRevers = allMinimumGradesRevers * 10 + allMinimumGrades % 10;
            allMinimumGrades /= 10;
        }

        for (int j = 1; j < weekNumber; j++) {
            System.out.print("Week ");
            System.out.print(j);

            if (j > 9) {
                System.out.print(" ");
            } else {
                System.out.print("  ");
            }

            for (int k = 0; k < allMinimumGradesRevers % 10; k++) {
                System.out.print("=");
            }

            System.out.println(">");

            allMinimumGradesRevers /= 10;
        }

        scanner.close();
    }
}