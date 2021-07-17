package edu.school21.processor;

public class PreProcessorToUpperImpl implements PreProcessor {
    @Override
    public String preProcess(String text) {
        return text.toUpperCase();
    }
}
