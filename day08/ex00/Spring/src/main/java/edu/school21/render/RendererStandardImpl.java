package edu.school21.render;

import edu.school21.processor.PreProcessor;

public class RendererStandardImpl implements Renderer {
    private PreProcessor preProcessor;

    public RendererStandardImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void printText(String text) {
        System.out.println(preProcessor.preProcess(text));
    }
}
