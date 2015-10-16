package com.nukulargames.signals;

import java.util.ArrayList;
import java.util.List;

public class DelegateStore<T> {
	public List<T> prependedDelegates = new ArrayList<>();
	public List<T> appendedDelegates = new ArrayList<>();
}
