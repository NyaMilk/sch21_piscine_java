package edu.school21.processor;

public class PreProcessorToLowerImpl implements PreProcessor {
    @Override
    public String preProcess(String text) {
        return text.toLowerCase();
    }
}
