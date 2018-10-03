package org.codefx.demo.effective_java._03_04_05_singleton_utilities_di;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.function.Consumer;

public class EventBus {

	private final String state;

	public EventBus(String state) {
		this.state = state;
	}

	public void send(Event event) {
		// do something
	}

	public void receive(Consumer<Event> event) {
		// do something
	}

}
