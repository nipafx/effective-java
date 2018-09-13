package org.codefx.demo.effective_java._02_builder_pattern.avoid_optionality;

public interface NutritionFact {

	int amount();

	Unit unit();

	enum Unit {

		PIECE, GRAM, MILILITER

	}

}
