package ru.tinkoff.rendering;

import ru.tinkoff.colors.Color;
import ru.tinkoff.image.FractalImage;
import ru.tinkoff.transformation.Transformation;
import ru.tinkoff.transformation.linear_transformations.AffineTransformation;

import java.util.List;
import java.util.stream.Stream;

public abstract class Renderer {

    public abstract FractalImage render(
            int pointsNumber,
            int iterationNumber,
            int imageWidth,
            int imageHeight,
            int symmetry,
            List<AffineTransformation> affineTransformations,
            Transformation nonLinearTransformation
    ) throws InterruptedException;

    public List<AffineTransformation> generateAffineTransformations(int n, List<Color> colors) {
        return Stream.generate(() -> new AffineTransformation(colors)).limit(n).toList();
    }
}
