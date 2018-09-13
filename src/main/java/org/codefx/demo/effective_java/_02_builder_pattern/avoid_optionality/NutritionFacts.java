package org.codefx.demo.effective_java._02_builder_pattern.avoid_optionality;

public class NutritionFacts {

	public static class ServingSize extends AbstractNutritionFact {

		public ServingSize(int amount) {
			super(amount, Unit.GRAM);
		}

	}

	public static class Servings extends AbstractNutritionFact {

		public Servings(int amount) {
			super(amount, Unit.PIECE);
		}

	}

	public static class Fat extends AbstractNutritionFact {

		public Fat(int amount) {
			super(amount, Unit.GRAM);
		}

	}

	public static class Sodium extends AbstractNutritionFact {

		public Sodium(int amount) {
			super(amount, Unit.GRAM);
		}

	}

	public static class Carbohydrate extends AbstractNutritionFact {

		public Carbohydrate(int amount) {
			super(amount, Unit.GRAM);
		}

	}

}
