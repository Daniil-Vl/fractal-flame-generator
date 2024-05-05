package ru.tinkoff.command_line;

import picocli.CommandLine;
import picocli.CommandLine.ITypeConverter;
import ru.tinkoff.colors.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ColorListConverter implements ITypeConverter<List<Color>> {

    @Override
    public List<Color> convert(String s) throws Exception {
        List<Color> headers = new ArrayList<>();
        var params = s.split(",");
        Arrays.stream(params).forEach(x -> headers.add(Color.valueOf(x.trim())));
        return headers;
    }
}