package com.nukulargames.signals.mustache;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

public class MustacheEngine {
	public static MustacheEngine INSTANCE = new MustacheEngine();

	private MustacheFactory factory;
	private Mustache factoryMustache;
	private Mustache factoriesMustache;
	private Mustache implementationMustache;
	private Mustache wrapperMustache;
	
	private MustacheEngine() {
		factory = new DefaultMustacheFactory();
		factoryMustache = factory.compile("Factory.mustache");
		factoriesMustache = factory.compile("Factories.mustache");
		implementationMustache = factory.compile("Implementation.mustache");
		wrapperMustache = factory.compile("Wrapper.mustache");
	}
	
	public Mustache getFactoryMustache() {
		return factoryMustache;
	}
	
	public Mustache getFactoriesMustache() {
		return factoriesMustache;
	}
	
	public Mustache getImplementationMustache() {
		return implementationMustache;
	}
	
	public Mustache getWrapperMustache() {
		return wrapperMustache;
	}
}
