package org.codefx.demo.effective_java._06_unnecessary_objects;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;

import java.util.ArrayList;

public class StringSplit extends ObjectCreationBenchmarks {

	@Param({ "foobar", "foo:bar", "foo::bar" })
	private String string = "foobar";

	@Benchmark
	public String[] singleColonSplit() {
		// NOTE unnecessary object:
		//   For a single-character, `split` employs an optimization
		//   and does not create a regex pattern
		return string.split(":");
	}

	@Benchmark
	public String[] singleColonCode() {
		// this code is unlikely to be 100% correct (it is basically untested),
		// so I assume a proper implementation would potentially be a little slower,
		// but surely in the same order of magnitude
		ArrayList<String> matches = new ArrayList<>();
		int lastIndex = 0;
		int nextIndex = string.indexOf(':');
		while (nextIndex > -1) {
			if (nextIndex > lastIndex + 1)
				matches.add(string.substring(lastIndex, nextIndex));
			lastIndex = nextIndex + 1;
			nextIndex = string.indexOf(':', nextIndex + 1);
		}
		if (string.length() > lastIndex)
			matches.add(string.substring(lastIndex));

		return matches.toArray(String[]::new);
	}

	@Benchmark
	public String[] doubleColonSplit() {
		// NOTE unnecessary object:
		//   Beyond a single character, `split` will create a create a regex pattern,
		//   which makes it rather slow
		return string.split("::");
	}

	@Benchmark
	public String[] doubleColonCode() {
		// this code is unlikely to be 100% correct (it is basically untested),
		// so I assume a proper implementation would potentially be a little slower,
		// but surely in the same order of magnitude
		ArrayList<String> matches = new ArrayList<>();
		int lastIndex = 0;
		int nextIndex = string.indexOf("::");
		while (nextIndex > -1) {
			if (nextIndex > lastIndex + 2)
				matches.add(string.substring(lastIndex, nextIndex));
			lastIndex = nextIndex + 2;
			nextIndex = string.indexOf("::", nextIndex + 2);
		}
		if (string.length() > lastIndex)
			matches.add(string.substring(lastIndex));

		return matches.toArray(String[]::new);
	}



}
