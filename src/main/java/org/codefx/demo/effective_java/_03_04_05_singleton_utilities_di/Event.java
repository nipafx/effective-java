package org.codefx.demo.effective_java._03_04_05_singleton_utilities_di;

import java.util.List;

public class Event {

	private final String message;
	private final List<Tag> tags;

	private Event(String message, Tag... tags) {
		this.message = message;
		this.tags = List.of(tags);
	}

	public static Event of(String message, Tag... tags) {
		// this is a value-based class and so
		// it needs a static factory method
		return new Event(message, tags);
	}

	// accessors, equals, hashCode, toString

	public enum Tag {

		USER, ORDER, FINANCIAL

	}

}
