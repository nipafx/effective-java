package org.codefx.demo.effective_java._03_04_05_singleton_utilities_di;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.function.Consumer;

public class EventBusSingleton implements Serializable {

	// NOTE singleton access:
	//   This incarnation of the singleton pattern uses a private instance field and a public
	//   accessor method as opposed to a public instance field. Some advantage are the ease of
	//   use as a method reference and the uncomplicated refactoring.
	private static final EventBusSingleton INSTANCE = new EventBusSingleton("Netherlands");
	private static final long serialVersionUID = 8255266253353087669L;

	// NOTE singletons and serialization:
	//   We always deserialize to `INSTANCE` (see `deasResolve()`) and so there is no need to
	//   actually serialize the singleton's state. Marking fields as `transient` achieves that.
	private transient final String state;

	private EventBusSingleton(String state) {
		this.state = state;
		// NOTE singleton plurality:
		//   This check and assertion error make sure that even reflection or programming
		//   mistakes within this source file can not lead to suplicate singletons.
		if (INSTANCE != null) {
			throw new AssertionError("Don't instantiate singleton more than once!");
		}
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

	// NOTE singletons and serialization:
	//   This method is absolutely critical for every serializable singleton!
	//   Without it, it is possible to deserialize as many "singleton" instances
	//   as we want.
	//   The Serialization API calls `readResolve()` on the deserialized instance
	//   and then discards it in favor of the returned instance. We this replace
	//   whatever is deserialized with `INSTANCE`.
	private Object readResolve() throws ObjectStreamException {
		return INSTANCE;
	}

}
