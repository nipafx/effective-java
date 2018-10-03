package org.codefx.demo.effective_java._03_04_05_singleton_utilities_di;

import org.codefx.demo.effective_java._03_04_05_singleton_utilities_di.Event.Tag;

public class EventBusUser {

	// NOTE dependency injection:
	//   This is classic, constructor-based dependency injection:
	//    * final fields
	//    * instances are provided during construction
	//   This class makes no assumption about the event bus's plurality.
	//   Is it a singleton? Is it a facade for a utility class? Is it
	//   a regular class? `EventBusUser` doesn't care!

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
