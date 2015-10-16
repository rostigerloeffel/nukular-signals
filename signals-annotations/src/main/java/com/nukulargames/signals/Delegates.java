package com.nukulargames.signals;

public class Delegates {
	private Delegates() {}
	
	public interface Delegate {};
	
	@FunctionalInterface
	public interface Delegate0 extends Delegate {
		void call();
	}
	
	@FunctionalInterface
	public interface Delegate1 <A1> extends Delegate {
		void call(A1 arg1);
	}
	
	@FunctionalInterface
	public interface Delegate2 <A1, A2> extends Delegate {
		void call(A1 arg1, A2 arg2);
	}

	@FunctionalInterface
	public interface Delegate3 <A1, A2, A3> extends Delegate {
		void call(A1 arg1, A2 arg2, A3 arg3);
	}
	
	@FunctionalInterface
	public interface Delegate4 <A1, A2, A3, A4> extends Delegate {
		void call(A1 arg1, A2 arg2, A3 arg3, A4 arg4);
	}
	
	@FunctionalInterface
	public interface Delegate5 <A1, A2, A3, A4, A5> extends Delegate {
		void call(A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5);
	}
	
	@FunctionalInterface
	public interface Delegate6 <A1, A2, A3, A4, A5, A6> extends Delegate {
		void call(A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5, A6 arg6);
	}
	
	@FunctionalInterface
	public interface Delegate7 <A1, A2, A3, A4, A5, A6, A7> extends Delegate {
		void call(A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5, A6 arg6, A7 arg7);
	}
	
	@FunctionalInterface
	public interface Delegate8 <A1, A2, A3, A4, A5, A6, A7, A8> extends Delegate {
		void call(A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5, A6 arg6, A7 arg7, A8 arg8);
	}
	
	@FunctionalInterface
	public interface Delegate9 <A1, A2, A3, A4, A5, A6, A7, A8, A9> extends Delegate {
		void call(A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5, A6 arg6, A7 arg7, A8 arg8, A9 arg9);
	}
	
	@FunctionalInterface
	public interface Delegate10 <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> extends Delegate {
		void call(A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5, A6 arg6, A7 arg7, A8 arg8, A9 arg9, A10 arg10);
	}
}
