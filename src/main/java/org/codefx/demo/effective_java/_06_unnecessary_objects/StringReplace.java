package org.codefx.demo.effective_java._06_unnecessary_objects;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;

public class StringReplace extends ObjectCreationBenchmarks {

	@Param({ "foobar", "foo:bar", "foo::bar" })
	private String string = "foobar";

	@Benchmark
	public String replaceAll() {
		// NOTE unnecessary object:
		//   `replaceAll` accepts a regex and hence has to compile it into a pattern
		return string.replaceAll(":", " ");
	}

	@Benchmark
	public String replace() {
		// use `replace` instead if you don't need to replace a regex
		return string.replace(":", " ");
	}

}
