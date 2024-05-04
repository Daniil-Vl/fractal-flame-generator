package ru.tinkoff.transformation.nonlinear_transformations;

import ru.tinkoff.image.Point;
import ru.tinkoff.transformation.Transformation;

public class CrossTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double coeff = Math.sqrt(1 / (x * x - y * y));

        return new Point(
                coeff * x,
                coeff * y
        );
    }
}
