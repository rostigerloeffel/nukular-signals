package com.nukulargames.signals.processors;

import java.io.IOException;
import java.io.Writer;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.StandardLocation;

import com.nukulargames.signals.mustache.MustacheEngine;
import com.nukulargames.signals.pojos.ServiceDescPojo;

@SupportedAnnotationTypes("com.nukulargames.signals.annotations.Signaller")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class SignallerProcessor extends AbstractProcessor {
	private static final String SIGNALLER_FACTORIES = "META-INF/services/com.nukulargames.signals.SignallerFactory";

	private ServiceDescPojo serviceDescPojo = new ServiceDescPojo();
	
	public SignallerProcessor() {
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		SignallerVisitor visitor = new SignallerVisitor(processingEnv.getElementUtils(), processingEnv.getFiler(),
				processingEnv.getMessager(), serviceDescPojo);

		try {
			for (Element element : roundEnv.getRootElements()) {
				visitor.visit(element);
			}

			if (roundEnv.processingOver()) {
				Writer entityFactoryServiceDescWriter = processingEnv.getFiler()
						.createResource(StandardLocation.SOURCE_OUTPUT, "", SIGNALLER_FACTORIES).openWriter();
				MustacheEngine.INSTANCE.getFactoriesMustache()
						.execute(entityFactoryServiceDescWriter, serviceDescPojo).flush();
				entityFactoryServiceDescWriter.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
