package org.codefx.demo.effective_java._03_04_05_singleton_utilities_di.facades;

import org.codefx.demo.effective_java._03_04_05_singleton_utilities_di.Event;

import java.util.function.Consumer;

public interface EventBus {

	void send(Event event);

	void receive(Consumer<Event> event);

}
