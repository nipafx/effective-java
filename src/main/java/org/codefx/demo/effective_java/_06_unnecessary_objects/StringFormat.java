package org.codefx.demo.effective_java._06_unnecessary_objects;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.TimeUnit;

public class StringFormat extends ObjectCreationBenchmarks {

	@Param({ "Motoko", "Batou" })
	private String name = "_";

	@Benchmark
	public String format() {
		// NOTE unnecessary object:
		//   The JVM has to create an array for `name` and `9`
		//   and box the latter into an `Integer`
		return String.format("%s(Section %d)", name, 9);
	}

	@Benchmark
	public String concatenate() {
		return name + "(Section " + 9 + ")";
	}

}
