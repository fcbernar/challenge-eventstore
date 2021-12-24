package net.intelie.challenges;

@SuppressWarnings("serial")
public class IllegalStateException extends Exception {
	public IllegalStateException() {
		super("Either moveNext has not been called or there are no events.");
	}
}
