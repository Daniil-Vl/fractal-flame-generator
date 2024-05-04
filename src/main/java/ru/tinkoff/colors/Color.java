package ru.tinkoff.colors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Color {
    BLACK(0, 0, 0),
    WHITE(255, 255, 255),
    RED(255, 0, 0),
    GREEN(0, 255, 0),
    BLUE(0, 0, 255),
    PURPLE(158, 103, 210),
    ORANGE(227, 101, 29);

    private final int red;
    private final int green;
    private final int blue;
}
