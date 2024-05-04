package ru.tinkoff.utils;

import ru.tinkoff.image.FractalImage;
import ru.tinkoff.image.Pixel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

public final class ImageUtils {
    private ImageUtils() {
    }

    public static void save(FractalImage image, Path filename, ImageFormat format) throws IOException {
        BufferedImage imageToSave = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        // Set colors for all pixels on the image
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Pixel pixel = image.getPixel(x, y);
                Color color = new Color(pixel.red(), pixel.green(), pixel.blue());
                imageToSave.setRGB(x, y, color.getRGB());
            }
        }

        ImageIO.write(imageToSave, format.getStringFormat(), filename.toFile());
    }
}
