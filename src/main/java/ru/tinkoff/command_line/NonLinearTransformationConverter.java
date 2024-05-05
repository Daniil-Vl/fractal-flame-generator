package ru.tinkoff.command_line;

import picocli.CommandLine.ITypeConverter;
import ru.tinkoff.transformation.nonlinear_transformations.factory.NonLinearTransformationType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NonLinearTransformationConverter implements ITypeConverter<List<NonLinearTransformationType>> {
    @Override
    public List<NonLinearTransformationType> convert(String s) throws Exception {
        List<NonLinearTransformationType> headers = new ArrayList<>();
        var params = s.split(",");
        Arrays.stream(params).forEach(x -> headers.add(NonLinearTransformationType.valueOf(x.trim())));
        return headers;
    }
}
