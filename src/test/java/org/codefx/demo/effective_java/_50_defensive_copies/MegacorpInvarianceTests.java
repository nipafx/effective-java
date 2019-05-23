package org.codefx.demo.effective_java._50_defensive_copies;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MegacorpInvarianceTests {

	private final List<Subsidiary> mutableSubsidiaries;
	private final Subsidiary orbitalDynamix = new Subsidiary("Orbital Dynamix", 693_264);

	MegacorpInvarianceTests() {
		mutableSubsidiaries = new ArrayList<>();
		mutableSubsidiaries.add(new Subsidiary("Arianespace", 1_023_684));
		mutableSubsidiaries.add(new Subsidiary("Krupp Group", 847_793));
		mutableSubsidiaries.add(new Subsidiary("Ruhr Nuclear", 295_203));
	}

	private static void assertReportedAndActualTotalRevenueEqual(Megacorp corp) {
		int actualTotalRevenue = corp.subsidiaries().stream()
				.mapToInt(Subsidiary::revenue)
				.sum();
		assertThat(corp.totalRevenue()).isEqualTo(actualTotalRevenue);
	}

	@Nested
	class EstablishingInvariants {

		@Test
		void nonEmptySubsidiaries() {
			assertThatThrownBy(() -> new Megacorp("Saeder-Krupp", new ArrayList<>()))
					.isInstanceOf(IllegalArgumentException.class);
		}

		@Test
		void revenueAfterConstruction() {
			var saederKrupp = new Megacorp("Saeder-Krupp", mutableSubsidiaries);

			assertReportedAndActualTotalRevenueEqual(saederKrupp);
		}

		@Test
		void revenueAfterAcquisition() {
			var saederKrupp = new Megacorp("Saeder-Krupp", mutableSubsidiaries);
			saederKrupp.acquire(orbitalDynamix);

			assertReportedAndActualTotalRevenueEqual(saederKrupp);
		}

	}

	@Nested
	class AttackingInvariants {

		@Test
		void makesDefensiveCopy() {
			var saederKrupp = new Megacorp("Saeder-Krupp", mutableSubsidiaries);
			mutableSubsidiaries.add(orbitalDynamix);

			assertReportedAndActualTotalRevenueEqual(saederKrupp);
		}

		@Test
		void copyBeforeTest() throws InterruptedException {
			var withoutSubsidiaries = new LongAdder();
			var tasks = Executors.newFixedThreadPool(2);

			for (int i = 0; i < 100; i++) {
				var subsidiaries = new ArrayList<Subsidiary>(mutableSubsidiaries);
				var taskLatch = new CountDownLatch(2);
				tasks.submit(() -> createMegacorpFromSubsidiaries(withoutSubsidiaries, subsidiaries, taskLatch));
				tasks.submit(() -> clearSubsidiaries(subsidiaries, taskLatch));
				taskLatch.await();
			}

			assertThat(withoutSubsidiaries.sum()).isZero();
		}

		private void clearSubsidiaries(ArrayList<Subsidiary> subsidiaries, CountDownLatch latch) {
			subsidiaries.clear();
			latch.countDown();
		}

		private void createMegacorpFromSubsidiaries(
				LongAdder withoutSubsidiaries, ArrayList<Subsidiary> subsidiaries, CountDownLatch latch) {
			try {
				var saederKrupp = new Megacorp("Saeder-Krupp", subsidiaries);
				if (saederKrupp.subsidiaries().isEmpty())
					// this should never happen!
					// ~> invariant broken!
					withoutSubsidiaries.increment();
			} catch (IllegalArgumentException ex) {
				// indicates that the construction failed because
				// the list of subsidiaries is empty
				// ~> invariant upheld
			} catch (RuntimeException ex) {
				// indicates that the stream pipeline in `Megacorp::new` failed
				// because of concurrent modification of the underlying collection
				// ~> invariant upheld
			}
			latch.countDown();
		}

		@Test
		void returnsDefensiveCopy() {
			try {
				var saederKrupp = new Megacorp("Saeder-Krupp", mutableSubsidiaries);
				saederKrupp.subsidiaries().add(orbitalDynamix);

				assertReportedAndActualTotalRevenueEqual(saederKrupp);
			} catch (RuntimeException ex) {
				// indicates that the returned collection was immutable
				// ~> invariant upheld
			}
		}

	}

}
