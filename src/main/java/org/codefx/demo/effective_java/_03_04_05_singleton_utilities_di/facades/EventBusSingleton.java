package org.codefx.demo.effective_java._03_04_05_singleton_utilities_di.facades;

import org.codefx.demo.effective_java._03_04_05_singleton_utilities_di.Event;

import java.util.function.Consumer;

public class EventBusSingleton {

	private static final EventBusSingleton INSTANCE = new EventBusSingleton("Netherlands");

	private EventBusSingleton(String state) {
		if (INSTANCE != null)
			throw new AssertionError("Don't instantiate singleton more than once!");
	}

	public static EventBusSingleton getInstance() {
		return INSTANCE;
	}

	public void send(Event event) {
		// do something
	}

	public void receive(Consumer<Event> event) {
		// do something
	}

}
