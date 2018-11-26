package org.codefx.demo.effective_java._03_04_05_singleton_utilities_di.facades;

import org.codefx.demo.effective_java._03_04_05_singleton_utilities_di.Event;

import java.util.function.Consumer;

/**
 * A facade for {@link EventBusUtility} that implements {@link EventBus}.
 */
public class EventBusUtilityFacade implements EventBus {

	@Override
	public void send(Event event) {
		EventBusUtility.send(event);
	}

	@Override
	public void receive(Consumer<Event> event) {
		EventBusUtility.receive(event);
	}

}
