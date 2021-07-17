package edu.school21.annotations;

import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.*;
import java.util.Set;
import java.util.StringJoiner;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("edu.school21.annotations.HtmlForm")
@AutoService(Processor.class)
public class HtmlProcessor extends AbstractProcessor {
    private void printError(String message) {
        Messager messager = processingEnv.getMessager();
        messager.printMessage(Diagnostic.Kind.ERROR, message);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        if (annotations == null || annotations.isEmpty()) {
            return false;
        }

        for (Element field : roundEnvironment.getElementsAnnotatedWith(HtmlForm.class)) {
            if (field.getKind() == ElementKind.CLASS) {
                HtmlForm htmlForm = field.getAnnotation(HtmlForm.class);

                StringJoiner body = new StringJoiner("\n");

                body.add(String.format("<form action = \"%s\" method = \"%s\">", htmlForm.action(), htmlForm.method()));

                for (Element element : field.getEnclosedElements()) {
                    HtmlInput htmlInput = element.getAnnotation(HtmlInput.class);

                    if (element.getKind() == ElementKind.FIELD && htmlInput != null) {
                        body.add(String.format("<input type = \"%s\" name = \"%s\" placeholder = \"%s\">", htmlInput.type(), htmlInput.name(), htmlInput.placeholder()));
                    }
                }

                body.add("<input type = \"submit\" value = \"Send\">\n</form>\n");

                try {
                    File file = new File("target/classes/" + htmlForm.fileName());
                    file.createNewFile();
                    FileWriter fileWriter = new FileWriter(file);
                    fileWriter.write(body.toString());
                    fileWriter.close();
                } catch (IOException e) {
                    printError("Cannot write file!");
                }
            } else {
                printError("Not found annotation for html processor!");
            }

            return true;
        }

        return false;
    }
}
