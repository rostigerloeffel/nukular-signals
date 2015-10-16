package com.nukulargames.signals;

import com.nukulargames.signals.Delegates.Delegate;

public interface Signaller<T> {
	public <D extends Delegate> void prependDelegate(int signal, D delegate);
	public <D extends Delegate> void appendDelegate(int signal, D delegate);
	public <D> T appendSignals(D delegate);
	public <D> T prependSignals(D delegate);
}
