package ru.tinkoff.transformation.nonlinear_transformations;

import ru.tinkoff.image.Point;
import ru.tinkoff.transformation.Transformation;

public class SwirlTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = Math.sqrt(x * x + y * y);

        return new Point(
                x * Math.sin(r * r) - y * Math.cos(r * r),
                x * Math.cos(r * r) + y * Math.sin(r * r)
        );
    }
}
