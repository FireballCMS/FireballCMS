package com.smac89.annotation.processor;

import com.smac89.annotation.Logged;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// http://www.baeldung.com/java-annotation-processing-builder
// http://hannesdorfmann.com/annotation-processing/annotationprocessing101
public class LoggedProcessor extends AbstractProcessor {

    private static final Set<String> supportedAnnotationTypes = Arrays.stream(new String[] {
            Logged.class.getCanonicalName()
    }).collect(Collectors.toSet());

    private Messager messager;
    private Elements elementUtils;
    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
        elementUtils = processingEnv.getElementUtils();
        filer = processingEnv.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        annotations.stream()
//                .filter((Predicate<TypeElement>) typeElement -> typeElement.getKind() == ElementKind.CLASS)
                .forEach(element -> messager.printMessage(Diagnostic.Kind.NOTE, "Found class: " + element.getQualifiedName(), element));
        return true;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return supportedAnnotationTypes;
    }
}
