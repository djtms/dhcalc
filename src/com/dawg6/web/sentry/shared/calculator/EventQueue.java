package com.dawg6.web.sentry.shared.calculator;

import java.util.TreeSet;

public class EventQueue {

	private final TreeSet<Event> events = new TreeSet<Event>();
	
	public EventQueue() { }
	
	public Event pop() {
		
		if (events.isEmpty())
			return null;
		else {
			Event e = events.first();
			events.remove(e);
			
			return e;
		}
	}

	public boolean isEmpty() {
		return events.isEmpty();
	}
	
	public void push(Event event) {
		events.add(event);
	}
	
	public void flush() {
		events.clear();
	}

	public double nextTime(double after) {
		for (Event e : events) {
			if (e.getTime() > after)
				return e.getTime();
		}
		
		return after + 0.1;
	}

	public void remove(Event e) {
		events.remove(e);
	}
}
