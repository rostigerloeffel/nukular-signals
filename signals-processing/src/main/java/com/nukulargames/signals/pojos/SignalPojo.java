package com.nukulargames.signals.pojos;

import java.util.Set;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;

public class SignalPojo {
	public String signalName;
	public String signature;
	public String superCall;
	public String wrappeeCall;
	public String returnValue;
	public String delegateType;
	public String argLine;
	public String defaultValue;
	public String enumName;
	public String index;

	public static SignalPojo fromExecutableElement(ExecutableElement element) {
		Set<Modifier> modifiers = element.getModifiers();
		SignalPojo pojo = new SignalPojo();
		pojo.signalName = element.getSimpleName().toString();
		StringBuilder signatureBuilder = new StringBuilder();
		signatureBuilder.append(modifiers.contains(Modifier.PUBLIC) ? "public " : "");
		signatureBuilder.append(modifiers.contains(Modifier.PROTECTED) ? "protected " : "");
		signatureBuilder.append(modifiers.contains(Modifier.PRIVATE) ? "private " : "");
		signatureBuilder.append(modifiers.contains(Modifier.STATIC) ? "static " : "");
		signatureBuilder.append(modifiers.contains(Modifier.FINAL) ? "final " : "");
		signatureBuilder.append(element.getReturnType().toString());
		signatureBuilder.append(" ");
		signatureBuilder.append(element.getSimpleName());
		signatureBuilder.append("(");
		StringBuilder argLineBuilder = new StringBuilder();
		for (int i = 0; i < element.getParameters().size(); ++i) {
			if (i != 0) {
				signatureBuilder.append(", ");
				argLineBuilder.append(", ");
			}
			VariableElement variable = element.getParameters().get(i);
			signatureBuilder.append(variable.asType().toString());
			signatureBuilder.append(" ");
			signatureBuilder.append(variable.toString());
			argLineBuilder.append(variable.toString());
		}
		signatureBuilder.append(")");
		pojo.signature = signatureBuilder.toString();
		pojo.returnValue = returnValue(element);
		pojo.delegateType = "Delegate" + element.getParameters().size();
		pojo.argLine = argLineBuilder.toString();
		pojo.superCall = targetCall(element, "super", pojo.argLine);
		pojo.wrappeeCall = targetCall(element, "wrappee", pojo.argLine);
		pojo.defaultValue = defaultValueByKind(element.getReturnType().getKind());
		pojo.enumName = enumName(element, pojo);
		return pojo;
	}

	public static String returnValue(ExecutableElement element) {
		if (element.getModifiers().contains(Modifier.ABSTRACT)) {
			return "";
		}
		if (element.getReturnType().getKind() == TypeKind.VOID) {
			return "";
		}
		return " value";
	}

	public static String targetCall(ExecutableElement element, String target, String argLine) {
		// Don't call abstract super methods
		if (element.getModifiers().contains(Modifier.ABSTRACT)) {
			return "";
		}
		if (element.getReturnType().getKind() == TypeKind.VOID) {
			return target + "." + element.getSimpleName() + "(" + argLine + ")";
		}
		return element.getReturnType().toString() + " value = " + target + "." + element.getSimpleName() + "(" + argLine
				+ ")";
	}

	private static String defaultValueByKind(TypeKind kind) {
		switch (kind) {
		case ARRAY:
			return "null";
		case BOOLEAN:
			return "false";
		case BYTE:
			return "0";
		case DOUBLE:
			return "0.0";
		case FLOAT:
			return "0.0f";
		case INT:
			return "0";
		case LONG:
			return "0L";
		case SHORT:
			return "0";
		case VOID:
			return "";
		default:
			return "null";
		}
	}

	private static String enumName(ExecutableElement element, SignalPojo pojo) {
		String name = pojo.signalName;
		for (VariableElement parameter : element.getParameters()) {
			name += "_$" + parameter.asType().toString();
		}
		return name.replaceAll("(\\p{javaLowerCase})(\\p{javaUpperCase})", "$1_$2").toUpperCase();
	}
}
