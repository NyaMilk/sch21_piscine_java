package edu.school21.printer.logic;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Converter {
    private BufferedImage image;
    private String symbolForBlack;
    private String symbolForWhite;

    public Converter(BufferedImage image, String symbolForWhite, String symbolForBlack) {
        this.image = image;
        this.symbolForWhite = symbolForWhite;
        this.symbolForBlack = symbolForBlack;
    }

    public void printConvertImage() {
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getRGB(x, y));

                int blue = color.getBlue();

                int red = color.getRed();

                int green = color.getGreen();

                int grey = (int) (red * 0.299 + green * 0.587 + blue * 0.114);

                if (grey == 255) {
                    System.out.print(symbolForWhite);
                } else {
                    System.out.print(symbolForBlack);
                }
            }

            System.out.println();
        }
    }
}
