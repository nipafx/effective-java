package org.codefx.demo.effective_java._03_04_05_singleton_utilities_di.facades;

import org.codefx.demo.effective_java._03_04_05_singleton_utilities_di.Event;
import org.codefx.demo.effective_java._03_04_05_singleton_utilities_di.Event.Tag;

public class EventBusUser {

	private final EventBus bus;

	public EventBusUser(EventBus bus) {
		this.bus = bus;
	}

	public void useBus() {
		bus.send(Event.of("User logged in", Tag.USER));

		// somewhere else
		bus.receive(event -> System.out.println("Event: " + event));
	}

}
