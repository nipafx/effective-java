package org.codefx.demo.effective_java._03_04_05_singleton_utilities_di.facades;

import org.codefx.demo.effective_java._03_04_05_singleton_utilities_di.Event;

import java.util.function.Consumer;

public class EventBusUtility {

	private EventBusUtility() {
		throw new AssertionError("Don't instantiate utility class!");
	}

	public static void send(Event event) {
		// do something
	}

	public static void receive(Consumer<Event> event) {
		// do something
	}

}
