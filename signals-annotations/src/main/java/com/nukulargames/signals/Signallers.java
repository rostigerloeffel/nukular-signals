package com.nukulargames.signals;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

public class Signallers {
	public static Signallers INSTANCE = new Signallers();
	
	@SuppressWarnings("rawtypes")
	private Map<Class, SignallerFactory> factories = new HashMap<>();
	
	@SuppressWarnings("rawtypes")
	private Signallers() {
		ServiceLoader<SignallerFactory> loader = ServiceLoader.<SignallerFactory> load(SignallerFactory.class);
		for (SignallerFactory factory : loader) {
			factories.put(factory.handles(), factory);
		}
	}
		
	@SuppressWarnings("unchecked")
	public static <T> T wrap(T signaller) {
		SignallerWrapper<T> wrapper = INSTANCE.factories.get(signaller.getClass()).createWrapper();
		return wrapper.wrap(signaller);
	}
	
	<T> void registerFactory(Class<T> clazz, SignallerFactory<T> factory) {
		factories.put(clazz, factory);
	}
}
