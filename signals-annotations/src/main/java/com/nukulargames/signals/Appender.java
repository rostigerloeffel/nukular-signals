package com.nukulargames.signals;

import com.nukulargames.signals.Delegates.Delegate0;
import com.nukulargames.signals.Delegates.Delegate1;
import com.nukulargames.signals.Delegates.Delegate2;
import com.nukulargames.signals.Delegates.Delegate3;
import com.nukulargames.signals.Delegates.Delegate4;
import com.nukulargames.signals.Delegates.Delegate5;

public class Appender <D> {
	private D delegate;
	
	public Appender(D delegate) {
		this.delegate = delegate;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T to(T signaller) {
		return (signaller instanceof Signaller) ? (T) (((Signaller<T>) signaller).appendSignals(delegate)) : null;
	}
	
	public static Appender<Delegate0> append(Delegate0 delegate) {
		return new Appender<Delegate0>(delegate);
	}
	
	public static <A1> Appender<Delegate1<A1>> append(Delegate1<A1> delegate) {
		return new Appender<Delegate1<A1>>(delegate);
	}
	
	public static <A1, A2> Appender<Delegate2<A1, A2>> append(Delegate2<A1, A2> delegate) {
		return new Appender<Delegate2<A1, A2>>(delegate);
	}
	
	public static <A1, A2, A3> Appender<Delegate3<A1, A2, A3>> append(Delegate3<A1, A2, A3> delegate) {
		return new Appender<Delegate3<A1, A2, A3>>(delegate);
	}
	
	public static <A1, A2, A3, A4> Appender<Delegate4<A1, A2, A3, A4>> append(Delegate4<A1, A2, A3, A4> delegate) {
		return new Appender<Delegate4<A1, A2, A3, A4>>(delegate);
	}
	
	public static <A1, A2, A3, A4, A5> Appender<Delegate5<A1, A2, A3, A4, A5>> prepend(Delegate5<A1, A2, A3, A4, A5> delegate) {
		return new Appender<Delegate5<A1, A2, A3, A4, A5>>(delegate);
	}
}
