package ru.tinkoff.rendering;

import lombok.RequiredArgsConstructor;
import ru.tinkoff.image.FractalImage;
import ru.tinkoff.image.Pixel;
import ru.tinkoff.image.Point;
import ru.tinkoff.image.Rectangle;
import ru.tinkoff.transformation.Transformation;
import ru.tinkoff.transformation.linear_transformations.AffineTransformation;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
class RenderingTask implements Runnable {
    private static final double X_MIN = -1.0;
    private static final double X_MAX = 1.0;
    private static final double Y_MIN = -1.0;
    private static final double Y_MAX = 1.0;
    private static final int RENDER_STEP_THRESHOLD = 20;

    private final FractalImage resultImage;
    private final int pointsNumber;
    private final int iterationNumber;
    private final int imageWidth;
    private final int imageHeight;
    private final int symmetry;
    private final List<AffineTransformation> affineTransformations;
    private final Transformation nonLinearTransformation;
    private final boolean multipleThreads;

    @Override
    public void run() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Rectangle biUnitRectangle = new Rectangle(X_MIN, Y_MIN, X_MAX - X_MIN, Y_MAX - Y_MIN);

        for (int num = 0; num < pointsNumber; num++) {
            double newX = random.nextDouble(X_MIN, X_MAX);
            double newY = random.nextDouble(Y_MIN, Y_MAX);
            Point point = new Point(newX, newY);

            for (int step = 0; step < iterationNumber; step++) {
                AffineTransformation affineTransformation = affineTransformations.get(
                        random.nextInt(affineTransformations.size())
                );

                point = affineTransformation.apply(point);
                point = nonLinearTransformation.apply(point);

                if (step <= RENDER_STEP_THRESHOLD || !biUnitRectangle.contains(point)) {
                    continue;
                }

                Point rotatedPoint;
                double angle = 0.0;

                for (int s = 0; s < symmetry; s++, angle += Math.PI * 2 / symmetry) {
                    rotatedPoint = Point.rotate(point, angle);
                    int x1 = imageWidth - (int) (imageWidth * ((X_MAX - rotatedPoint.x()) / (X_MAX - X_MIN)));
                    int y1 = imageHeight - (int) (imageHeight * ((Y_MAX - rotatedPoint.y()) / (Y_MAX - Y_MIN)));

                    if (!resultImage.contains(x1, y1)) {
                        continue;
                    }

                    Pixel pixel = resultImage.getPixel(x1, y1);

                    if (multipleThreads) {
                        synchronized (pixel) {
                            resultImage.setPixel(x1, y1, changePixelColor(pixel, affineTransformation).hit());
                        }
                    } else {
                        resultImage.setPixel(x1, y1, changePixelColor(pixel, affineTransformation).hit());
                    }
                }
            }
        }
    }

    private Pixel changePixelColor(Pixel pixel, AffineTransformation affineTransformation) {
        if (pixel.hitCount() == 0) {
            return pixel.setColor(
                    affineTransformation.getRed(),
                    affineTransformation.getGreen(),
                    affineTransformation.getBlue()
            );
        }

        return pixel.setColor(
                (pixel.red() + affineTransformation.getRed()) / 2,
                (pixel.green() + affineTransformation.getGreen()) / 2,
                (pixel.blue() + affineTransformation.getBlue()) / 2
        );
    }
}