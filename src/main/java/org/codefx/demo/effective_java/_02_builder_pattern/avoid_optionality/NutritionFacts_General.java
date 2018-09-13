package org.codefx.demo.effective_java._02_builder_pattern.avoid_optionality;

import java.util.Map;
import java.util.Optional;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;

// NOTE alternatives:
//   To combat optionality, this class uses a collection to collect optional
//   information. To find it again, it uses the enum `NutritionFactType` as
//   a key in a map. This additional enum may be a problem under certain
//   circumstances (e.g. when users can add nutrition facts, which the enum
//   wouldn't know about).
//
//   Thanks to `NutritionFact`, this class contains more information than
//   the primitive variant.
public class NutritionFacts_General {

	// NOTE alternatives:
	//   I realized too late that `servingSize` and `servings` may also be
	//   managed by the map.

	// required
	private final NutritionFact servingSize;
	private final NutritionFact servings;

	// optional
	private final Map<NutritionFactType, NutritionFact> nutritionFacts;

	public NutritionFacts_General(
			NutritionFact servingSize,
			NutritionFact servings,
			Map<NutritionFactType, NutritionFact> nutritionFacts) {
		this.servingSize = servingSize;
		this.servings = servings;
		// may wanna make sure that `servingsSize` and `servings` is not duplicated
		this.nutritionFacts = Map.copyOf(nutritionFacts);
	}

	public NutritionFact servingSize() {
		return servingSize;
	}

	public NutritionFact servings() {
		return servings;
	}

	public Optional<NutritionFact> fact(NutritionFactType type) {
		switch (type) {
			case SERVINGS_SIZE:
				return of(servingSize);
			case SERVINGS:
				return of(servings);
			default:
				return ofNullable(nutritionFacts.get(type));
		}
	}

	public Optional<NutritionFact> fat() {
		return fact(NutritionFactType.FAT);
	}

	public Optional<NutritionFact> sodium() {
		return fact(NutritionFactType.SODIUM);
	}

	public Optional<NutritionFact> carbohydrates() {
		return fact(NutritionFactType.CARBOHYDRATES);
	}

}
