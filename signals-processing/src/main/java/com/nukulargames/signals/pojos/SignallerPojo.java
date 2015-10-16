package com.nukulargames.signals.pojos;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;

public class SignallerPojo {
	public String packageName;
	public String name;
	public List<SignalPojo> signals = new ArrayList<>();

	public static SignallerPojo fromTypeElement(TypeElement element) {
		SignallerPojo pojo = new SignallerPojo();
		pojo.name = element.getSimpleName().toString();
		// Obtain package name
		Element enclosingElement = element.getEnclosingElement();
		if (enclosingElement instanceof PackageElement) {
			PackageElement packageElement = (PackageElement) enclosingElement;
			pojo.packageName = packageElement.getQualifiedName().toString();
		}
		// Generate signals
		for (Element child : element.getEnclosedElements()) {
			if (child instanceof ExecutableElement && child.getKind() == ElementKind.METHOD
					&& !((ExecutableElement) child).getModifiers().contains(Modifier.STATIC)) {
				SignalPojo signal = SignalPojo.fromExecutableElement((ExecutableElement) child);
				signal.index = String.valueOf(pojo.signals.size());
				pojo.signals.add(signal);
			}
		}
		return pojo;
	}
}
