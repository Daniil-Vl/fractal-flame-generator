package ru.tinkoff.transformation.linear_transformations;

import lombok.Getter;
import ru.tinkoff.colors.Color;
import ru.tinkoff.image.Point;
import ru.tinkoff.transformation.Transformation;

import java.util.List;
import java.util.Random;

/**
 * Transforms point using formula given below:
 * x1 = a*x0 + b*y0 + c
 * y1 = d*x0 + e*y0 + f
 */
public class AffineTransformation implements Transformation {
    private static final double LINEAR_TRANSFORMATION_BOTTOM_EDGE = -1.0;
    private static final double LINEAR_TRANSFORMATION_UPPER_EDGE = 1.0;
    private static final double TRANSLATION_TRANSFORMATION_BOTTOM_EDGE = -1.0;
    private static final double TRANSLATION_TRANSFORMATION_UPPER_EDGE = 1.0;

    @Getter
    private final int red;
    @Getter
    private final int green;
    @Getter
    private final int blue;

    private final double c;
    private final double f;
    private double a;
    private double b;
    private double d;
    private double e;

    public AffineTransformation(List<Color> colors) {
        Random random = new Random();

        do {
            a = random.nextDouble(LINEAR_TRANSFORMATION_BOTTOM_EDGE, LINEAR_TRANSFORMATION_UPPER_EDGE);
            b = random.nextDouble(LINEAR_TRANSFORMATION_BOTTOM_EDGE, LINEAR_TRANSFORMATION_UPPER_EDGE);
            d = random.nextDouble(LINEAR_TRANSFORMATION_BOTTOM_EDGE, LINEAR_TRANSFORMATION_UPPER_EDGE);
            e = random.nextDouble(LINEAR_TRANSFORMATION_BOTTOM_EDGE, LINEAR_TRANSFORMATION_UPPER_EDGE);
        } while (!isCoefficientValid(a, b, d, e));

        this.c = random.nextDouble(TRANSLATION_TRANSFORMATION_BOTTOM_EDGE, TRANSLATION_TRANSFORMATION_UPPER_EDGE);
        this.f = random.nextDouble(TRANSLATION_TRANSFORMATION_BOTTOM_EDGE, TRANSLATION_TRANSFORMATION_UPPER_EDGE);

        Color randomColor = colors.get(random.nextInt(colors.size()));
        this.red = randomColor.getRed();
        this.green = randomColor.getGreen();
        this.blue = randomColor.getBlue();
    }

    private boolean isCoefficientValid(double a, double b, double d, double e) {
        return a * a + d * d < 1
                && b * b + e * e < 1
                && a * b + d * e < 1 + (a * e + b * d) * (a * e + b * d);
    }

    @Override
    public Point apply(Point point) {
        double x = a * point.x() + b * point.y() + c;
        double y = d * point.x() + e * point.y() + f;
        return new Point(x, y);
    }
}
