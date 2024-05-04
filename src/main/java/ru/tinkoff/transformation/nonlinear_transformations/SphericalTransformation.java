package ru.tinkoff.transformation.nonlinear_transformations;

import ru.tinkoff.image.Point;
import ru.tinkoff.transformation.Transformation;

public class SphericalTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());

        return new Point(
                point.x() / (r * r),
                point.y() / (r * r)
        );
    }
}
