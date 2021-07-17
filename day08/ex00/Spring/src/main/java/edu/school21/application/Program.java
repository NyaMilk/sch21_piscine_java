package edu.school21.application;

import edu.school21.printer.Printer;
import edu.school21.printer.PrinterWithDateTimeImpl;
import edu.school21.printer.PrinterWithPrefixImpl;
import edu.school21.processor.PreProcessor;
import edu.school21.processor.PreProcessorToUpperImpl;
import edu.school21.render.Renderer;
import edu.school21.render.RendererStandardImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Printer printer = context.getBean("printerWithPrefix", Printer.class);
        Printer printerTime = context.getBean("printerWithDateTimeImpl", Printer.class);
        printer.print("Hello!");
        printerTime.print("Hello!");

        PrinterWithPrefixImpl printerWithPrefix = context.getBean("printerWithPrefix", PrinterWithPrefixImpl.class);
        printerWithPrefix.setPrefix("NEW PREFIX TEST");
        printerWithPrefix.print("Ola");

        PreProcessor preProcessor = new PreProcessorToUpperImpl();
        Renderer renderer = new RendererStandardImpl(preProcessor);
        PrinterWithDateTimeImpl printerWithDateTime = new PrinterWithDateTimeImpl(renderer);
        printerWithDateTime.print("End");
    }
}
