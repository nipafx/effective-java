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
import java.util.regex.Pattern;

public class StringMatches extends ObjectCreationBenchmarks {

	private static final String ROMAN_REG_EX = "^(?=.)M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$";
	private static final Pattern ROMAN_PATTERN = Pattern.compile(ROMAN_REG_EX);

	@Param({ "I", "XXXIV", "_" })
	private String numeral = "I";

	@Benchmark
	public boolean recreatePatternOnEachMatch() {
		// NOTE unnecessary object:
		//   `matches` will compile the regex to a finite state machine,
		//   which takes "a lot" of time
		return numeral.matches(ROMAN_REG_EX);
	}

	@Benchmark
	public boolean reusePattern() {
		return ROMAN_PATTERN.matcher(numeral).matches();
	}

}
