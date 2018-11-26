package org.codefx.demo.effective_java._03_04_05_singleton_utilities_di;

import org.codefx.demo.effective_java._03_04_05_singleton_utilities_di.Event.Tag;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.function.Supplier;

/*
 * You will find NOTEs on the following topics:
 *  - utility classes
 *  - singletons (access, plurality, serialization, enum)
 *  - dependency injection
 *  - facades (see package of the same name)
 */

public class Main {

	public static void main(String[] args) throws Exception {
		useUtility();

		useSingleton();
		use(EventBusSingleton::getInstance);
		serializeSingleton();
		useEnum();

		useDependencyInjection();
	}

	static void useUtility() {
		EventBusUtility.send(Event.of("User logged in", Tag.USER));

		// somewhere else
		EventBusUtility.receive(event -> System.out.println("Event: " + event));
	}

	static void useSingleton() {
		EventBusSingleton.getInstance().send(Event.of("User logged in", Tag.USER));

		// somewhere else
		EventBusSingleton.getInstance().receive(event -> System.out.println("Event: " + event));
	}

	static void use(Supplier<EventBusSingleton> eventBus) {
		eventBus.get().send(Event.of("User logged in", Tag.USER));
	}

	private static void serializeSingleton() {
		try {
			byte[] asBytes = serializeBus();
			var firstBus = deserializeBus(asBytes);
			var secondBus = deserializeBus(asBytes);
			System.out.println("Same bus: " + (firstBus == secondBus));
		} catch (ClassNotFoundException | IOException ex) {
			ex.printStackTrace();
		}
	}

	private static byte[] serializeBus() throws IOException {
		ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(outBytes);
		out.writeObject(EventBusSingleton.getInstance());
		return outBytes.toByteArray();
	}

	private static EventBusSingleton deserializeBus(byte[] asBytes) throws IOException, ClassNotFoundException {
		ByteArrayInputStream inBytes = new ByteArrayInputStream(asBytes);
		ObjectInputStream in = new ObjectInputStream(inBytes);
		return (EventBusSingleton) in.readObject();
	}

	private static void useEnum() {
		EventBusEnum.INSTANCE.send(Event.of("User logged in", Tag.USER));

		// somewhere else
		EventBusEnum.INSTANCE.receive(event -> System.out.println("Event: " + event));
	}

	static void useDependencyInjection() {
		// excert instance control here,
		// e.g. to have singleton-behavior
		EventBus bus = new EventBus("Belgium");
		// inject the bus into the user
		EventBusUser busUser = new EventBusUser(bus);

		busUser.useBus();
	}

}
