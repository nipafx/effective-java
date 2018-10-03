package org.codefx.demo.effective_java._03_04_05_singleton_utilities_di;

import java.util.function.Consumer;

public enum EventBusEnum {

	// NOTE singleton enum:
	//   Using an enum, makes it unnecessary to manually enforce plurality
	//   during construction and deserialization - the JVM does it for us.
	//   That keeps the class simple and guaranteed to be correct.
	INSTANCE;

	public void send(Event event) {
		// do something
	}

	public void receive(Consumer<Event> event) {
		// do something
	}

}
