package ru.tinkoff.transformation.nonlinear_transformations;

import ru.tinkoff.image.Point;
import ru.tinkoff.transformation.Transformation;

public class DiskTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = Math.sqrt(x * x + y * y);
        double teta = Math.atan(x / y);

        return new Point(
                teta / Math.PI * Math.sin(Math.PI * r),
                teta / Math.PI * Math.cos(Math.PI * r)
        );
    }
}
