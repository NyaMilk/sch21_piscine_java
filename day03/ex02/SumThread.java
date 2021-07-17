public class SumThread extends Thread {
    private int[] numbers;
    private long sum;
    private int start;
    private int end;
    private int threadNumber;

    public SumThread(int[] numbers, int start, int end, int threadNumber) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
        this.sum = 0;
        this.threadNumber = threadNumber;
    }

    public void sum() {
        Program.total += sum;
    }

    public void run() {
        for (int i = start; i <= end; i++) {
            sum += numbers[i];
        }

        sum();
    }

    @Override
    public String toString() {
        return "Thread " + threadNumber + ": from " + start + " to " + end + " sum is " + sum;
    }
}