package ru.tinkoff.transformation;

import ru.tinkoff.image.Point;

import java.util.function.Function;

public interface Transformation extends Function<Point, Point> {
}
