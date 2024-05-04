package ru.tinkoff.rendering;

public class SingleThreadedRendererTest extends RendererTest {
    @Override
    Renderer getInstance() {
        return new SingleThreadedRenderer();
    }
}
