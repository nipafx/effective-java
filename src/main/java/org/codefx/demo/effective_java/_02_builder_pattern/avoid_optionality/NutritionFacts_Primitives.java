package org.codefx.demo.effective_java._02_builder_pattern.avoid_optionality;

// NOTE alternatives:
//   This class benefits from a builder for two reasons:
//    * it has a bunch of optional fields
//    * it has a bunch of fields of the same type (all, actually)
//
//   Put together, this makes common construction unredable
//   ("What are all these ints?!") and complex ("Which constructor
//   to call for a specific combination of optional fields?!").
//
//   The builder fixes these symptoms, but does not tackle the
//   underlying problems: optionality and poor typing.
public class NutritionFacts_Primitives {

	// required
	private final int servingSize;
	private final int servings;

	// optional
	private final int fat;
	private final int sodium;
	private final int carbohydrates;

	private NutritionFacts_Primitives(NutritionFactsBuilder builder) {
		this.servingSize = builder.servingSize;
		this.servings = builder.servings;
		this.fat = builder.fat;
		this.sodium = builder.sodium;
		this.carbohydrates = builder.carbohydrates;
	}

	public int servingSize() {
		return servingSize;
	}

	public int servings() {
		return servings;
	}

	public int fat() {
		return fat;
	}

	public int sodium() {
		return sodium;
	}

	public int carbohydrates() {
		return carbohydrates;
	}

	public static class NutritionFactsBuilder {

		// required fields
		private final int servingSize;
		private final int servings;

		// optional fields
		private int fat;
		private int sodium;
		private int carbohydrates;

		public NutritionFactsBuilder(int servingSize, int servings) {
			this.servingSize = servingSize;
			this.servings = servings;
		}

		public NutritionFactsBuilder fat(int fat) {
			this.fat = fat;
			return this;
		}

		public NutritionFactsBuilder sodium(int sodium) {
			this.sodium = sodium;
			return this;
		}

		public NutritionFactsBuilder carbohydrates(int carbohydrates) {
			this.carbohydrates = carbohydrates;
			return this;
		}

		public NutritionFacts_Primitives build() {
			return new NutritionFacts_Primitives(this);
		}

	}

}
