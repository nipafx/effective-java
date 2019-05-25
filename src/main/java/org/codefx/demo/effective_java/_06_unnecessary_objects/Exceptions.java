package org.codefx.demo.effective_java._06_unnecessary_objects;

import org.openjdk.jmh.annotations.Benchmark;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exceptions extends ObjectCreationBenchmarks {

	@Benchmark
	public Exception createException() {
		// NOTE expensive operation:
		//   Exceptions capture the entire call stack, which takes some time.
		return new IllegalArgumentException();
	}

	@Benchmark
	public Map<String, Integer> createMap() {
		var map = new HashMap<String, Integer>();
		map.put("1", 1);
		map.put("2", 1);
		map.put("3", 1);
		return map;
	}

}
