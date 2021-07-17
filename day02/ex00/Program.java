import java.io.*;
import java.util.*;

public class Program {
    public static String decimalToHex(int decimal) {
        String digits = "0123456789ABCDEF";

        if (decimal <= 0) {
            return "0";
        }

        int base = 16;

        String hex = "";

        while (decimal > 0) {
            int digit = decimal % base;
            hex = digits.charAt(digit) + hex;
            decimal = decimal / base;
        }

        return hex;
    }

    public static Map<String, String> getMapSignatures() throws IOException {
        Map<String, String> signatures = new LinkedHashMap<>();

        File file = new File("signatures.txt");

        String absolutePath = file.getAbsolutePath();

        try {
            FileInputStream fileInputStream = new FileInputStream(absolutePath);

            String line = "";

            int i = 0;

            while ((i = fileInputStream.read()) != -1) {
                line += (char) i;

                if ((char) i == '\n') {
                    String[] tmp = line.split(",");
                    signatures.put(tmp[1].trim().substring(0, 11), tmp[0]);
                    line = "";
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return signatures;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        Map<String, String> signatures = getMapSignatures();

        for (String fileName = scanner.nextLine(); !fileName.equals("42"); fileName = scanner.nextLine()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(fileName);

                String fileType = "";

                String resultType = "";

                for (int i = 0; i < 4; i++) {
                    fileType += decimalToHex(fileInputStream.read()) + " ";
                }

                fileInputStream.close();

                resultType = signatures.get(fileType.trim());

                if (resultType != null) {
                    try {
                        File file = new File("result.txt");

                        FileOutputStream fileOutputStream = new FileOutputStream(file, true);

                        fileOutputStream.write((resultType + "\n").getBytes());

                        fileOutputStream.close();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                    System.out.println("PROCESSED");
                } else {
                    System.out.println("UNDEFINED");
                }

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}