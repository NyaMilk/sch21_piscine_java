import java.util.Scanner;

public class Program {
    static final int MAX_LENGTH = 65535;

    static final int MAX_COUNT = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String string = scanner.nextLine();

        int[] allChars = new int[MAX_LENGTH];

        int[] countCharsLimit = new int[MAX_COUNT];

        int[] charsLimit = new int[MAX_COUNT];

        int[] gridCount = new int[MAX_COUNT];

        for (char c : string.toCharArray()) {
            if ((int) c < MAX_LENGTH) {
                allChars[c]++;
            }
        }

        for (int i = 0; i < MAX_COUNT; i++) {
            int maxCount = 0;

            int maxChar = 0;

            for (int j = 0; j < MAX_LENGTH; j++) {
                if (allChars[j] > maxCount) {
                    maxCount = allChars[j];
                    maxChar = j;
                }
            }

            countCharsLimit[i] = maxCount;

            charsLimit[i] = maxChar;

            allChars[maxChar] = 0;
        }

        for (int i = 0; i < MAX_COUNT; i++) {
            if (countCharsLimit[0] > MAX_COUNT) {
                gridCount[i] = (countCharsLimit[i] * MAX_COUNT) / countCharsLimit[0];
            } else {
                gridCount[i] = countCharsLimit[i];
            }
        }

        int max = (countCharsLimit[0] > MAX_COUNT) ? MAX_COUNT : countCharsLimit[0];

        if (countCharsLimit[0] > 0) {
            for (int i = max; i >= 0; i--) {
                for (int j = 0; j < MAX_COUNT; j++) {
                    if (countCharsLimit[j] > 0) {
                        if (i > gridCount[j]) {
                            System.out.print("   ");
                        } else if (i == gridCount[j]) {
                            System.out.printf("%3d", countCharsLimit[j]);
                        } else {
                            System.out.print("  #");
                        }
                    }
                }

                System.out.println();
            }

            for (int k = 0; k < MAX_COUNT; k++) {
                System.out.print("  " + (char) charsLimit[k]);
            }

            System.out.println();
        }

        scanner.close();
    }
}