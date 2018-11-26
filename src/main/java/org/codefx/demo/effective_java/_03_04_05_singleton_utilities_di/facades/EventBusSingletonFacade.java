package org.codefx.demo.effective_java._03_04_05_singleton_utilities_di.facades;

import org.codefx.demo.effective_java._03_04_05_singleton_utilities_di.Event;

import java.util.function.Consumer;

/**
 * A facade for {@link EventBusSingleton} that implements {@link EventBus}.
 *
 * (Only required if {@code EventBusSingleton} can not be edited to implement
 *  the interface directly, which is otherwise preferable.)
 */
public class EventBusSingletonFacade implements EventBus {

	@Override
	public void send(Event event) {
		EventBusSingleton.getInstance().send(event);
	}

	@Override
	public void receive(Consumer<Event> event) {
		EventBusSingleton.getInstance().receive(event);
	}

}
