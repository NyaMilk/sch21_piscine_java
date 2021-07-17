public class Program {
    public static void main(String[] args) throws InterruptedException {
        if (args.length != 1 || !args[0].contains("--count=")) {
            System.err.println("Missing argument --count");
            System.exit(-1);
        }

        String[] argForParse = args[0].split("=");

        if (argForParse.length == 1) {
            System.err.println("Missing count value");
            System.exit(-1);
        }

        int count = Integer.parseInt(argForParse[1]);

        Say say = new Say();

        Egg egg = new Egg("Egg", count, say);

        Hen hen = new Hen("Hen", count, say);

        egg.start();

        hen.start();
    }
}