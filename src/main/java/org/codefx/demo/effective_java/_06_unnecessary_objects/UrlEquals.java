package org.codefx.demo.effective_java._06_unnecessary_objects;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlEquals extends ObjectCreationBenchmarks {

	private String blog = "https://codefx.org";

	private String youtube = "https://youtube.com/codefx";

	@Benchmark
	public boolean urlEqual() throws MalformedURLException {
		// NOTE expensive operation:
		//   There isn't anything unnecessary here. It's just that URL::equals
		//   resolves the host name, which can take a while...
		return new URL(blog).equals(new URL(youtube));
	}

	@Benchmark
	public boolean stringEqual() throws MalformedURLException {
		return blog.equals(youtube);
	}

}
