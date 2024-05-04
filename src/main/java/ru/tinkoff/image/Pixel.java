package ru.tinkoff.image;

/**
 * Representation for pixel color and hit count
 */
public record Pixel(int red, int green, int blue, int hitCount) {
    public Pixel setColor(int red, int green, int blue) {
        return new Pixel(red, green, blue, hitCount);
    }

    public Pixel hit() {
        return new Pixel(red, green, blue, hitCount + 1);
    }
}