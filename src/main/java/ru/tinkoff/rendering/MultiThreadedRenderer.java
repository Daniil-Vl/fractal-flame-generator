package ru.tinkoff.rendering;

import ru.tinkoff.image.FractalImage;
import ru.tinkoff.transformation.Transformation;
import ru.tinkoff.transformation.linear_transformations.AffineTransformation;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThreadedRenderer extends Renderer {
    private static final int TIMEOUT = 5;

    private final ExecutorService executorService;
    private final int nThreads;

    public MultiThreadedRenderer(int nThreads) {
        this.nThreads = nThreads;
        this.executorService = Executors.newFixedThreadPool(nThreads);
    }

    @Override
    public FractalImage render(
            int pointsNumber,
            int iterationNumber,
            int imageWidth,
            int imageHeight,
            int symmetry,
            List<AffineTransformation> affineTransformations,
            Transformation nonLinearTransformation
    ) throws InterruptedException {
        FractalImage resultImage = FractalImage.create(imageWidth, imageHeight);
        int pointsPerThread = pointsNumber / nThreads;

        for (int i = 0; i < nThreads; i++) {
            RenderingTask task = new RenderingTask(
                    resultImage,
                    pointsPerThread,
                    iterationNumber,
                    imageWidth,
                    imageHeight,
                    symmetry,
                    affineTransformations,
                    nonLinearTransformation,
                    true
            );
            executorService.submit(task);
        }

        executorService.shutdown();
        executorService.awaitTermination(TIMEOUT, TimeUnit.MINUTES);

        return resultImage;
    }
}
