import java.io.*;
import java.util.*;

import static java.lang.Math.sqrt;

public class Program {
    public static ArrayList<String> getWords(String fileName) throws IOException {
        ArrayList<String> words = new ArrayList<>();

        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            while ((line = reader.readLine()) != null) {
                String[] tmpWords = line.split("\\s+");

                for (String tmpWord : tmpWords) {
                    words.add(tmpWord.toLowerCase());
                }
            }

            reader.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


        return words;
    }

    public static ArrayList<Integer> getVector(ArrayList<String> words, LinkedHashSet<String> dictionary) {
        ArrayList<Integer> vector = new ArrayList<>();

        dictionary.forEach((word) -> {
            int count = (int) words.stream().filter(rawWord -> rawWord.equals(word)).count();

            vector.add(count);
        });

        return vector;
    }

    public static double countNumerator(ArrayList<Integer> firstVector, ArrayList<Integer> secondVector) {
        double numerator = 0;

        for (int i = 0; i < firstVector.size(); i++) {
            numerator = numerator + (firstVector.get(i) * secondVector.get(i));
        }

        return numerator;
    }

    public static double rootOfSumOfSquare(ArrayList<Integer> v) {
        int sum = v.stream().mapToInt(number -> number * number).sum();

        return sqrt(sum);
    }

    public static double countDenominator(ArrayList<Integer> firstVector, ArrayList<Integer> secondVector) {
        double firstRoot = rootOfSumOfSquare(firstVector);

        double secondRoot = rootOfSumOfSquare(secondVector);

        return firstRoot * secondRoot;
    }

    public static void writeDictionaryInFile(LinkedHashSet<String> dictionary, String fileName) throws IOException {
        File file = new File(fileName);

        FileOutputStream fileOutputStream = new FileOutputStream(file, true);

        dictionary.forEach((word) -> {
            try {
                fileOutputStream.write((word + "\n").getBytes());
            } catch (IOException e) {
                System.err.println("Can't write word in file");
                System.exit(-1);
            }
        });

        fileOutputStream.close();
    }

    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            System.out.println("Invalid argument numbers");
            System.exit(-1);
        }

        double similarity = 0;

        LinkedHashSet<String> dictionary = new LinkedHashSet<>();

        String firstPath = args[0];

        String secondPath = args[1];

        ArrayList<String> firstListWords = getWords(firstPath);

        ArrayList<String> secondListWords = getWords(secondPath);

        if (firstListWords.size() == 0 || secondListWords.size() == 0) {
            System.out.printf("Similarity = %.2f\n", similarity);
            return;
        }

        dictionary.addAll(firstListWords);

        dictionary.addAll(secondListWords);

        ArrayList<Integer> firstVector = getVector(firstListWords, dictionary);

        ArrayList<Integer> secondVector = getVector(secondListWords, dictionary);

        double numerator = countNumerator(firstVector, secondVector);

        double denominator = countDenominator(firstVector, secondVector);

        similarity = numerator / denominator;

        System.out.printf("Similarity = %.2f\n", similarity);

        writeDictionaryInFile(dictionary, "dictionary.txt");
    }
}