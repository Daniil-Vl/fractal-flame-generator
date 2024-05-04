package ru.tinkoff.image_processing;

import org.junit.jupiter.api.Test;
import ru.tinkoff.image.FractalImage;
import ru.tinkoff.image.Pixel;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class LogGammaCorrectionTest {

    private static final int COLOR_UPPER_BOUND = 256;

    @Test
    void logGammaCorrection() {
        Random random = new Random();
        Pixel initialPixel = new Pixel(
                random.nextInt(COLOR_UPPER_BOUND),
                random.nextInt(COLOR_UPPER_BOUND),
                random.nextInt(COLOR_UPPER_BOUND),
                random.nextInt()
        );
        FractalImage image = new FractalImage(new Pixel[][]{{initialPixel}}, 1, 1);
        LogGammaCorrection correction = new LogGammaCorrection(2.5);

        correction.process(image);

        Pixel processedPixel = image.getPixel(0, 0);

        assertThat(processedPixel.red()).isLessThanOrEqualTo(initialPixel.red());
        assertThat(processedPixel.green()).isLessThanOrEqualTo(initialPixel.green());
        assertThat(processedPixel.blue()).isLessThanOrEqualTo(initialPixel.blue());
    }
}
