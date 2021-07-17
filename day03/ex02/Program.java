import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Program {
    public static int total = 0;

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 2 || !args[0].contains("--arraySize=") || !args[1].contains("--threadsCount=")) {
            System.err.println("Missing argument --arraySize or --threadsCount");
            System.exit(-1);
        }

        String parseArraySize = args[0].substring(args[0].indexOf('=') + 1);

        String parseThreadsCount = args[1].substring(args[1].indexOf('=') + 1);

        if (parseArraySize.length() < 1 || parseThreadsCount.length() < 1) {
            System.err.println("Missing value");
            System.exit(-1);
        }

        int arraySize = Integer.parseInt(parseArraySize);

        int threadsCount = Integer.parseInt(parseThreadsCount);

        int delimiter = arraySize / threadsCount;

        int[] numbers = new int[arraySize];

        long sum = 0;

        int start = 0;

        int end = 0;

        for (int i = 0; i < arraySize; i++) {
            numbers[i] = ThreadLocalRandom.current().nextInt(-1000, 1000);
            sum += numbers[i];
        }

        System.out.println("Sum: " + sum);

        ArrayList<Thread> threads = new ArrayList<>();

        int threadNumber = 1;

        for (int i = 0; i < threadsCount - 1; i++) {
            start = i * delimiter;
            end = start + delimiter - 1;
            threads.add(new SumThread(numbers, start, end, threadNumber++));
        }

        start = delimiter * (threadsCount - 1);

        threads.add(new SumThread(numbers, start, arraySize - 1, threadNumber));

        for (Thread th : threads) {
            th.start();
            th.join();
            System.out.println(th.toString());
        }

        System.out.printf("Sum by threads: %d\n", total);
    }
}