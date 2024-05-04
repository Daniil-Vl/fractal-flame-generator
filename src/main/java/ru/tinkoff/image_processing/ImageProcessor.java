package ru.tinkoff.image_processing;

import ru.tinkoff.image.FractalImage;

@FunctionalInterface
public interface ImageProcessor {
    /**
     * In-place image post-processing
     *
     * @param image to process
     */
    void process(FractalImage image);
}
