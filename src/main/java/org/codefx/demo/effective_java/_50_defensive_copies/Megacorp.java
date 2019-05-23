package org.codefx.demo.effective_java._50_defensive_copies;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Megacorp {

	private final String name;
	private int totalRevenue;
	private final List<Subsidiary> subsidiaries;

	public Megacorp(String name, List<Subsidiary> subsidiaries) {
		this.name = name;
		this.totalRevenue = subsidiaries.stream()
				.mapToInt(Subsidiary::revenue)
				.sum();
		this.subsidiaries = new ArrayList<>(subsidiaries);

		if (this.subsidiaries.isEmpty())
			throw new IllegalArgumentException(name + "  needs at least one subsidiary.");
	}

	public String name() {
		return name;
	}

	public int totalRevenue() {
		return totalRevenue;
	}

	public List<Subsidiary> subsidiaries() {
		return List.copyOf(subsidiaries);
	}

	public void acquire(Subsidiary subsidiary) {
		subsidiaries.add(subsidiary);
		totalRevenue += subsidiary.revenue();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Megacorp megacorp = (Megacorp) o;
		return Objects.equals(name, megacorp.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public String toString() {
		return name + "(" + totalRevenue + " M€)";
	}

}
