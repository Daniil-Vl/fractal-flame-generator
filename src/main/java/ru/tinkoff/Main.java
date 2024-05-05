package ru.tinkoff;

import lombok.extern.log4j.Log4j2;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import ru.tinkoff.colors.Color;
import ru.tinkoff.command_line.ColorListConverter;
import ru.tinkoff.transformation.nonlinear_transformations.factory.NonLinearTransformationFactory;
import ru.tinkoff.transformation.nonlinear_transformations.factory.NonLinearTransformationType;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.Callable;

@Log4j2
@CommandLine.Command(name = "fractal-flame-generator", description = "Generate fractal flames using several threads")
public class Main implements Callable<Integer> {

    @Option(names = {"--threads-number"}, description = "Number of threads will be used", required = true)
    private int threadsNumber;

    @Option(names = {"--points-number"}, description = "Number of points", required = true)
    private int pointsNumber;

    @Option(names = {"--iterations-number"}, description = "Number of point transformation iterations", required = true)
    private int iterationsNumber;

    @Option(names = {"--affines-number"}, description = "Number of random affine transformation", required = true)
    private int affinesNumber;

    @Option(names = {"--gamma"}, description = "Gamma parameter for log gamma correction", required = true)
    private double gamma;

    @Option(names = {"--symmetry"}, description = "Number of symmetric copies", required = true)
    private int symmetry;

    @Option(names = {"--path"}, description = "Path and file name for file with generated fractal flame", required = true)
    private Path filepath;

    @Option(names = {"--colors"}, description = "Comma separated list of colors will be used"
            + "%nCandidates: ${COMPLETION-CANDIDATES}", converter = ColorListConverter.class)
    private List<Color> colors;

    @Option(names = {"--nonlinear"}, description = "Non linear transformation which will be used to generate flames"
            + "%nCandidates: ${COMPLETION-CANDIDATES}")
    private NonLinearTransformationType nonLinearTransformationType;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        FractalFlameGenerator.generateFractalFlame(
                threadsNumber,
                pointsNumber,
                iterationsNumber,
                affinesNumber,
                gamma,
                symmetry,
                colors,
                NonLinearTransformationFactory.createTransformation(nonLinearTransformationType),
                filepath
        );
        return 0;
    }
}
