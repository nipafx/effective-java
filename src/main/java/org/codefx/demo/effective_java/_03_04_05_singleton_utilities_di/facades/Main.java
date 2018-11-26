package org.codefx.demo.effective_java._03_04_05_singleton_utilities_di.facades;

import org.codefx.demo.effective_java._03_04_05_singleton_utilities_di.Event;
import org.codefx.demo.effective_java._03_04_05_singleton_utilities_di.Event.Tag;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.function.Supplier;

public class Main {

	public static void main(String[] args) throws Exception {
		// NOTE dependency injection facades:
		//   With facades around singletons and utility classes, the assumptions about
		//   their plurality can be cordoned off into small parts of the code base.
		//   All other classes simply get an implementation of `EventBus` injected and
		//   don't care about how many event bus singletons there are supposed to exist.
		new EventBusUser(new EventBusUtilityFacade()).useBus();
		new EventBusUser(new EventBusSingletonFacade()).useBus();
		new EventBusUser(EventBusEnum.INSTANCE).useBus();
	}

}
