package ru.tinkoff.rendering;

import ru.tinkoff.image.FractalImage;
import ru.tinkoff.transformation.Transformation;
import ru.tinkoff.transformation.linear_transformations.AffineTransformation;

import java.util.List;

public class SingleThreadedRenderer extends Renderer {

    @Override
    public FractalImage render(int pointsNumber, int iterationNumber, int imageWidth, int imageHeight, int symmetry, List<AffineTransformation> affineTransformations, Transformation nonLinearTransformation) throws InterruptedException {
        FractalImage resultImage = FractalImage.create(imageWidth, imageHeight);

        RenderingTask task = new RenderingTask(
                resultImage,
                pointsNumber,
                iterationNumber,
                imageWidth,
                imageHeight,
                symmetry,
                affineTransformations,
                nonLinearTransformation,
                false
        );
        task.run();

        return resultImage;
    }

}
