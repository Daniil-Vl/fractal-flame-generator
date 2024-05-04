package ru.tinkoff;

import lombok.extern.log4j.Log4j2;
import ru.tinkoff.colors.Color;
import ru.tinkoff.transformation.Transformation;
import ru.tinkoff.transformation.nonlinear_transformations.factory.NonLinearTransformationFactory;
import ru.tinkoff.transformation.nonlinear_transformations.factory.NonLinearTransformationType;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Log4j2
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Path file_path = Path.of("src", "main", "resources", "samples", "sample.png");
        List<Color> colors = List.of(Color.WHITE, Color.ORANGE);
        Transformation nonLinearTransformation = NonLinearTransformationFactory.createTransformation(NonLinearTransformationType.EXPONENTIAL);

        FractalFlameGenerator.generateFractalFlame(
                4,
                5000,
                30000,
                5,
                2.0,
                1,
                colors,
                nonLinearTransformation,
                file_path
        );
    }
}
