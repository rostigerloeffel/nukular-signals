package com.nukulargames.signals.processors;

import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementScanner8;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic.Kind;

import com.nukulargames.signals.annotations.Signaller;
import com.nukulargames.signals.mustache.MustacheEngine;
import com.nukulargames.signals.pojos.ServiceDescPojo;
import com.nukulargames.signals.pojos.SignallerPojo;

public class SignallerVisitor extends ElementScanner8<Void, Void> {
	private final static String IMPLEMENTATION_SUFFIX = "Signals";
	private final static String WRAPPER_SUFFIX = "Wrapper";
	private final static String FACTORY_SUFFIX = "Factory";
	private static final String QUALIFIER = ".";

	private Elements elementUtils;
	private Filer filerUtils;
	private Messager messagerUtils;
	private ServiceDescPojo serviceDescPojo;

	private Set<String> visited = new HashSet<>();

	public SignallerVisitor(Elements elementUtils, Filer filerUtils, Messager messagerUtils,
			ServiceDescPojo serviceDescPojo) {
		this.elementUtils = elementUtils;
		this.filerUtils = filerUtils;
		this.messagerUtils = messagerUtils;
		this.serviceDescPojo = serviceDescPojo;
	}

	public ServiceDescPojo getServiceDescPojo() {
		return serviceDescPojo;
	}

	private void writeSignaller(SignallerPojo pojo) {
		try {
			Writer entityFactoryWriter = filerUtils
					.createSourceFile(pojo.packageName + QUALIFIER + pojo.name + FACTORY_SUFFIX).openWriter();
			MustacheEngine.INSTANCE.getFactoryMustache().execute(entityFactoryWriter, pojo).flush();
			entityFactoryWriter.close();

			Writer entitySignalsWriter = filerUtils
					.createSourceFile(pojo.packageName + QUALIFIER + pojo.name + IMPLEMENTATION_SUFFIX).openWriter();
			MustacheEngine.INSTANCE.getImplementationMustache().execute(entitySignalsWriter, pojo).flush();
			entitySignalsWriter.close();

			Writer wrapperWriter = filerUtils
					.createSourceFile(pojo.packageName + QUALIFIER + pojo.name + WRAPPER_SUFFIX).openWriter();
			MustacheEngine.INSTANCE.getWrapperMustache().execute(wrapperWriter, pojo).flush();
			wrapperWriter.close();
		} catch (IOException e) {
			messagerUtils.printMessage(Kind.ERROR, e.getMessage());
		}
	}

	@Override
	public Void visitType(TypeElement e, Void p) {
		if (e.getAnnotation(Signaller.class) != null) {
			handleTypeElement(e);
		}
		return super.visitType(e, p);
	}

	@Override
	public Void visitVariable(VariableElement e, Void p) {
		if (e.getAnnotation(Signaller.class) != null) {
			TypeElement typeElement = elementUtils.getTypeElement(e.asType().toString());
			if (typeElement != null) {
				handleTypeElement(typeElement);
			}
		}
		return super.visitVariable(e, p);
	}

	private void handleTypeElement(TypeElement element) {
		if (element.getModifiers().contains(Modifier.FINAL)) {
			messagerUtils.printMessage(Kind.NOTE, "cannot generate signal wrapper for final class", element);
			return;
		}
		
		if (!visited.contains(element.toString())) {
			visited.add(element.toString());
			SignallerPojo pojo = SignallerPojo.fromTypeElement(element);
			serviceDescPojo.signallers.add(pojo);
			writeSignaller(pojo);
		}
	}
}
