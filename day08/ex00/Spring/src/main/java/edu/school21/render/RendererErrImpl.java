package edu.school21.render;

import edu.school21.processor.PreProcessor;

public class RendererErrImpl implements Renderer {
    private PreProcessor preProcessor;

    public RendererErrImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void printText(String text) {
        System.err.println(preProcessor.preProcess(text));
    }
}
