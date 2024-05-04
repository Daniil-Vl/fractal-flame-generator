package ru.tinkoff.image;

import lombok.Getter;

public final class FractalImage {
    private final Pixel[][] field;

    @Getter
    private final int width;

    @Getter
    private final int height;

    public FractalImage(Pixel[][] field, int width, int height) {
        this.field = field;
        this.width = width;
        this.height = height;
    }

    /**
     * Create FractalImage with a black background
     *
     * @param width  - image's width
     * @param height - image's height
     * @return empty fractal image
     */
    public static FractalImage create(int width, int height) {
        Pixel[][] tempField = new Pixel[height][width];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tempField[y][x] = new Pixel(0, 0, 0, 0);
            }
        }

        return new FractalImage(tempField, width, height);
    }

    public boolean contains(int x, int y) {
        return x >= 0 && x < this.width && y >= 0 && y < this.height;
    }

    public Pixel getPixel(int x, int y) {
        return this.field[y][x];
    }

    public void setPixel(int x, int y, Pixel pixel) {
        this.field[y][x] = pixel;
    }
}
