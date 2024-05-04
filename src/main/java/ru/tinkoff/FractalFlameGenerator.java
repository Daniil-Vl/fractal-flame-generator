package ru.tinkoff;

import lombok.extern.log4j.Log4j2;
import ru.tinkoff.colors.Color;
import ru.tinkoff.image.FractalImage;
import ru.tinkoff.image_processing.LogGammaCorrection;
import ru.tinkoff.rendering.MultiThreadedRenderer;
import ru.tinkoff.rendering.Renderer;
import ru.tinkoff.rendering.SingleThreadedRenderer;
import ru.tinkoff.transformation.Transformation;
import ru.tinkoff.transformation.linear_transformations.AffineTransformation;
import ru.tinkoff.utils.ImageFormat;
import ru.tinkoff.utils.ImageUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Log4j2
public final class FractalFlameGenerator {

    static void generateFractalFlame(
            int threadsNumber,
            int pointsNumber,
            int iterationsNumber,
            int affinesNumber,
            double gamma,
            int symmetry,
            List<Color> colors,
            Transformation nonLinearTransformation,
            Path filename
    ) throws IOException, InterruptedException {
        if (threadsNumber <= 0) {
            throw new IllegalArgumentException("Number of threads must be positive");
        }

        if (symmetry <= 0) {
            throw new IllegalArgumentException("Symmetry must be positive");
        }

        Renderer renderer = threadsNumber == 1 ? new SingleThreadedRenderer() : new MultiThreadedRenderer(threadsNumber);
        List<AffineTransformation> affineTransformationList =
                renderer.generateAffineTransformations(affinesNumber, colors);

        long startTime = System.nanoTime();
        FractalImage image = renderer.render(
                pointsNumber,
                iterationsNumber,
                1920,
                1080,
                symmetry,
                affineTransformationList,
                nonLinearTransformation
        );
        long endTime = System.nanoTime();

        log.info("Rendering time: {} seconds", TimeUnit.NANOSECONDS.toSeconds(endTime - startTime));

        LogGammaCorrection correction = new LogGammaCorrection(gamma);
        correction.process(image);

        ImageUtils.save(image, filename, ImageFormat.PNG);
    }

}
