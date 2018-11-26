package org.codefx.demo.effective_java._03_04_05_singleton_utilities_di.facades;

import org.codefx.demo.effective_java._03_04_05_singleton_utilities_di.Event;

import java.util.function.Consumer;

public enum EventBusEnum implements EventBus {

	INSTANCE;

	@Override
	public void send(Event event) {
		// do something
	}

	@Override
	public void receive(Consumer<Event> event) {
		// do something
	}

}
