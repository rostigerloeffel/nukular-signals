package com.nukulargames.signals;

public interface SignallerFactory<T> {
	SignallerWrapper<T> createWrapper();
	Class<T> handles();
}
