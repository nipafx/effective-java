package org.codefx.demo.effective_java._50_defensive_copies;

import java.util.Objects;

public class Subsidiary {

	private final String name;
	private final int revenue;

	public Subsidiary(String name, int revenue) {
		this.name = name;
		this.revenue = revenue;
	}

	public String name() {
		return name;
	}

	public int revenue() {
		return revenue;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Subsidiary that = (Subsidiary) o;
		return Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public String toString() {
		return name + "(" + revenue + " M€)";
	}

}
