package ru.tinkoff.rendering;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.tinkoff.colors.Color;
import ru.tinkoff.image.FractalImage;
import ru.tinkoff.image.Pixel;
import ru.tinkoff.transformation.linear_transformations.AffineTransformation;
import ru.tinkoff.transformation.nonlinear_transformations.factory.NonLinearTransformationFactory;
import ru.tinkoff.transformation.nonlinear_transformations.factory.NonLinearTransformationType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class RendererTest {

    private Renderer renderer;

    @BeforeEach
    void init() {
        this.renderer = getInstance();
    }

    abstract Renderer getInstance();

    @Test
    void rendererChangeSomePixelColor() throws InterruptedException {
        int pointsNumber = 100;
        int iterationNumber = 100;
        Color color = Color.BLUE;
        FractalImage image = renderer.render(pointsNumber, iterationNumber, 10, 10, 1,
                List.of(new AffineTransformation(List.of(color))),
                NonLinearTransformationFactory.createTransformation(NonLinearTransformationType.EXPONENTIAL)
        );

        int blueCount = countBlue(image);

        assertThat(blueCount).isGreaterThan(0);
    }

    private int countBlue(FractalImage image) {
        int count = 0;

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                if (isBlue(image.getPixel(x, y))) {
                    count++;
                }
            }
        }

        return count;
    }

    private boolean isBlue(Pixel pixel) {
        Color blue = Color.BLUE;
        return pixel.red() == blue.getRed() && pixel.green() == blue.getGreen() && pixel.blue() == blue.getBlue();
    }

}
