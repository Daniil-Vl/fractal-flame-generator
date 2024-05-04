package ru.tinkoff.transformation.nonlinear_transformations.factory;

import ru.tinkoff.transformation.Transformation;
import ru.tinkoff.transformation.nonlinear_transformations.*;

public class NonLinearTransformationFactory {
    private NonLinearTransformationFactory() {
    }

    public static Transformation createTransformation(NonLinearTransformationType type) {
        return switch (type) {
            case DISK -> new DiskTransformation();
            case CROSS -> new CrossTransformation();
            case EXPONENTIAL -> new ExponentialTransformation();
            case HEART -> new HeartTransformation();
            case POLAR -> new PolarTransformation();
            case SWIRL -> new SwirlTransformation();
            case SINUSOIDAL -> new SinusoidalTransformation();
            case SPHERICAL -> new SphericalTransformation();
            case TANGENT -> new TangentTransformation();
        };
    }
}