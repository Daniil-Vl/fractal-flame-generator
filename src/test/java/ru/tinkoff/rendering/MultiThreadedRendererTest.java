package ru.tinkoff.rendering;

public class MultiThreadedRendererTest extends RendererTest {
    @Override
    Renderer getInstance() {
        return new MultiThreadedRenderer(
                Runtime.getRuntime().availableProcessors()
        );
    }
}
