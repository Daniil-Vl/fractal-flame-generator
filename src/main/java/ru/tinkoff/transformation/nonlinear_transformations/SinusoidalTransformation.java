package ru.tinkoff.transformation.nonlinear_transformations;

import ru.tinkoff.image.Point;
import ru.tinkoff.transformation.Transformation;

public class SinusoidalTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        return new Point(
                Math.sin(point.x()),
                Math.sin(point.y())
        );
    }
}
