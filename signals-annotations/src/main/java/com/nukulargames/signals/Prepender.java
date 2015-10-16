package com.nukulargames.signals;

import com.nukulargames.signals.Delegates.Delegate0;
import com.nukulargames.signals.Delegates.Delegate1;
import com.nukulargames.signals.Delegates.Delegate2;
import com.nukulargames.signals.Delegates.Delegate3;
import com.nukulargames.signals.Delegates.Delegate4;
import com.nukulargames.signals.Delegates.Delegate5;

public class Prepender<D> {
	private D delegate;

	public Prepender(D delegate) {
		this.delegate = delegate;
	}

	@SuppressWarnings("unchecked")
	public <T> T to(T signaller) {
		return (signaller instanceof Signaller) ? (T) (((Signaller<T>) signaller).prependSignals(delegate)) : null;
	}

	public static Prepender<Delegate0> prepend(Delegate0 delegate) {
		return new Prepender<Delegate0>(delegate);
	}

	public static <A1> Prepender<Delegate1<A1>> prepend(Delegate1<A1> delegate) {
		return new Prepender<Delegate1<A1>>(delegate);
	}

	public static <A1, A2> Prepender<Delegate2<A1, A2>> prepend(Delegate2<A1, A2> delegate) {
		return new Prepender<Delegate2<A1, A2>>(delegate);
	}

	public static <A1, A2, A3> Prepender<Delegate3<A1, A2, A3>> prepend(Delegate3<A1, A2, A3> delegate) {
		return new Prepender<Delegate3<A1, A2, A3>>(delegate);
	}

	public static <A1, A2, A3, A4> Prepender<Delegate4<A1, A2, A3, A4>> prepend(Delegate4<A1, A2, A3, A4> delegate) {
		return new Prepender<Delegate4<A1, A2, A3, A4>>(delegate);
	}

	public static <A1, A2, A3, A4, A5> Prepender<Delegate5<A1, A2, A3, A4, A5>> prepend(Delegate5<A1, A2, A3, A4, A5> delegate) {
		return new Prepender<Delegate5<A1, A2, A3, A4, A5>>(delegate);
	}
}
