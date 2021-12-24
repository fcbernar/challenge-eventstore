package net.intelie.challenges;

import java.util.ArrayList;

// I chose ArrayList to store the events since its a structure that allows the user to add new elements on the go
// while a regular array does not

public class EventHandler implements EventStore {
	private static ArrayList<Event> events;
	private static EventHandler handler = null;
	
	// I decided to make the EventStore implementation a singleton since 
	// it does not seem to be reasonable to exist more than one event store,
	// thus every event is stored in the "events" static variable
	
	private EventHandler() {
		
	}
	
	
	public static EventHandler getEventHandler() {
		if (handler == null) {
			handler = new EventHandler();
			events = new ArrayList<>();
		}
		return handler;
	}
	
	// I figured there should be a way to get the iterator of the events ArrayList
	public EventBrowser getIterator() {
		boolean emptyStore;
		if (events.isEmpty()) {
			emptyStore = true;
		} else {
			emptyStore = false;
		}
		return new EventBrowser(events, emptyStore);
	}

	public void insert(Event event) {
		events.add(event);
	}

	// Since the ArrayList library has a method that allows the user to remove elements
	// from the array based on a condition I decided to use it to implement this method
	public void removeAll(String type) {
		events.removeIf( e -> (e.type().equals(type)) );
	}

	// There was a similar method for this case in the ArrayList library, but since there were quite a few
	// conditions I figured it would be way too messy to understand. So instead I just iterated through the
	// array with a for loop and stored the events that matched the conditions in a recently created one
	// returning a EventBrowser (that implements EventIterator) object that iterates over the new array
	public EventBrowser query(String type, long startTime, long endTime) {
		ArrayList<Event> matches = new ArrayList<>();
		String eventType;
		long eventTimestamp;
		boolean empty;
		
		for (Event e : events) {
			eventType = e.type();
			eventTimestamp = e.timestamp();
			
			if (eventType.equals(type) && startTime <= eventTimestamp && eventTimestamp < endTime) {
				matches.add(e);
			}
		}
		
		if (matches.isEmpty()) {
			empty = true;
		} else {
			empty = false;
		}
		
		return new EventBrowser(matches, empty);
	}
	
}
