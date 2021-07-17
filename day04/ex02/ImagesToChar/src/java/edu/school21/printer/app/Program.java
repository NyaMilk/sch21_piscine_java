package edu.school21.printer.app;

import edu.school21.printer.logic.Converter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.beust.jcommander.*;

@Parameters(separators = "=")
public class Program {
    @Parameter(names = {"--white", "-w"})
    String symbolForWhite;
    @Parameter(names = {"--black", "-b"})
    String symbolForBlack;

    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            System.err.println("Invalid number of arguments");
            System.exit(-1);
        }

        Program program = new Program();

        JCommander.newBuilder()
                .addObject(program)
                .build()
                .parse(args);

        program.run();
    }

    public void run() throws IOException {
        File file = new File("target/resources/image.bmp");

        BufferedImage image = ImageIO.read(file);

        Converter convert = new Converter(image, symbolForWhite, symbolForBlack);

        convert.printConvertImage();
    }
}
