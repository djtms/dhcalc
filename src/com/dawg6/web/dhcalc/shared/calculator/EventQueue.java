/*******************************************************************************
 * Copyright (c) 2014, 2015 Scott Clarke (scott@dawg6.com).
 *
 * This file is part of Dawg6's Demon Hunter DPS Calculator.
 *
 * Dawg6's Demon Hunter DPS Calculator is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Dawg6's Demon Hunter DPS Calculator is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package com.dawg6.web.dhcalc.shared.calculator;

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
