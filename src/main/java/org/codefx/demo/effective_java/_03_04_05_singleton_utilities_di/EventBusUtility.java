package org.codefx.demo.effective_java._03_04_05_singleton_utilities_di;

import java.util.function.Consumer;

public class EventBusUtility {

	// NOTE utility:
	//   We're preventing instantiation by regular means with a private
	//   constructor. To prevent reflection or implementation errors within
	//   this source file, we throw an assertion error if the constructor
	//   gets called.
	private EventBusUtility() {
		throw new AssertionError("Don't instantiate utility class!");
	}

	// NOTE utility:
	//   This isn't a good utility class because it depends on the context
	//   of the application. A good utility class offers its funtionality
	//   regardless of context, like Math.sin.

	public static void send(Event event) {
		// do something
	}

	public static void send(Event event, boolean audit) {
		// do something
	}

	public static void receive(Consumer<Event> event) {
		// do something
	}

	public static void receive(Consumer<Event> event, boolean audit) {
		// do something
	}

}
