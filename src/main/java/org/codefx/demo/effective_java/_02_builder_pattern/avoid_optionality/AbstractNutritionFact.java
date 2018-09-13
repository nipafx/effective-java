package org.codefx.demo.effective_java._02_builder_pattern.avoid_optionality;

import static java.util.Objects.hash;

abstract class AbstractNutritionFact implements NutritionFact {

	private final int amount;
	private final Unit unit;

	public AbstractNutritionFact(int amount, Unit unit) {
		this.amount = amount;
		this.unit = unit;
	}

	@Override
	public int amount() {
		return amount;
	}

	@Override
	public Unit unit() {
		return unit;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null || getClass() != other.getClass())
			return false;
		AbstractNutritionFact that = (AbstractNutritionFact) other;
		return amount == that.amount && unit == that.unit;
	}

	@Override
	public int hashCode() {
		return hash(amount, unit);
	}

	@Override
	public String toString() {
		return amount + " " + unit;
	}

}
