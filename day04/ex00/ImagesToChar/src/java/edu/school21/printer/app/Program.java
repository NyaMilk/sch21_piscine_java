package edu.school21.printer.app;

import edu.school21.printer.logic.Converter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Program {
	public static void main(String[] args) throws IOException {

		if (args.length != 3) {
			System.err.println("Invalid number of arguments");
			System.exit(-1);
		}

		if (!args[0].contains("--white=") || !args[1].contains("--black=")) {
            System.err.println("Missing argument --white or --black");
            System.exit(-1);
        }

        String parseWhite = args[0].substring(args[0].indexOf('=') + 1);

        String parseBlack = args[1].substring(args[1].indexOf('=') + 1);

        if (parseWhite.length() < 1 || parseBlack.length() < 1) {
            System.err.println("Missing value");
            System.exit(-1);
        }

		String symbolForWhite = args[0].split("=")[1];

		String symbolForBlack = args[1].split("=")[1];

		String path = args[2];

		File file = new File(path);

		BufferedImage image = ImageIO.read(file);

		Converter convert = new Converter(image, symbolForWhite, symbolForBlack);

		convert.printConvertImage();
	}
}
