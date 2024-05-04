package ru.tinkoff.image_processing;

import ru.tinkoff.image.FractalImage;
import ru.tinkoff.image.Pixel;

public class LogGammaCorrection implements ImageProcessor {
    private final double gamma;

    public LogGammaCorrection(double gamma) {
        this.gamma = gamma;
    }

    @Override
    public void process(FractalImage image) {
        double max = 0.0;
        double[][] normals = new double[image.getHeight()][image.getWidth()];

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Pixel pixel = image.getPixel(x, y);
                if (pixel.hitCount() != 0) {
                    normals[y][x] = Math.log10(pixel.hitCount());
                    max = Math.max(max, normals[y][x]);
                }
            }
        }

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Pixel pixel = image.getPixel(x, y);
                normals[y][x] /= max;

                pixel = pixel.setColor(
                        (int) (pixel.red() * Math.pow(normals[y][x], 1.0 / gamma)),
                        (int) (pixel.green() * Math.pow(normals[y][x], 1.0 / gamma)),
                        (int) (pixel.blue() * Math.pow(normals[y][x], 1.0 / gamma))
                );

                image.setPixel(x, y, pixel);
            }
        }
    }
}
