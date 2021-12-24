package net.intelie.challenges;

import java.util.ArrayList;
import java.util.Iterator;

// Using an iterator seemed to match the requirements perfectly mainly considering the 
// interface description for the remove method (throws exceptions when called before any
// next() and when the iterator has reached the end of the array).

public class EventBrowser implements EventIterator {
	// The "curr" variable stores the last event returned by next() (iterator library method).
	// reachedEnd is important for the current() and remove() functions logic. It allows it to throw the exceptions
	// required correctly.
	private Event curr; 
	private boolean reachedEnd;
	private Iterator<Event> iterator;
	
	// This class has a constructor that receives an ArrayList and stores its iterator.
	// It also receives a boolean variable that indicates whether the array is empty or not
	public EventBrowser(ArrayList<Event> events, boolean state) {
		iterator = events.iterator();
		reachedEnd = state;
		curr = null;
	}
	
	// The moveNext() method updates the rachedEnd variable based on the hasNext() method 
	// from the iterator library. This method returns false when there is not a next element
	// move to and true if there is.
	public boolean moveNext() {
		if ( !iterator.hasNext() ) {
			reachedEnd = true;
			return !reachedEnd;
		}
		curr = iterator.next();
		reachedEnd = false;
		return !reachedEnd;
	}
	
	public Event current() throws IllegalStateException {
		if (reachedEnd) {
			throw new IllegalStateException();
		} else {
			return curr;
		}
	}
	
	public void remove() throws IllegalStateException {
		if (reachedEnd) {
			throw new IllegalStateException();
		} else {
			iterator.remove();
		}
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
