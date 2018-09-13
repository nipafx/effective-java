package org.codefx.demo.effective_java._02_builder_pattern.avoid_optionality;

import org.codefx.demo.effective_java._02_builder_pattern.avoid_optionality.NutritionFacts.Carbohydrate;
import org.codefx.demo.effective_java._02_builder_pattern.avoid_optionality.NutritionFacts.Fat;
import org.codefx.demo.effective_java._02_builder_pattern.avoid_optionality.NutritionFacts.ServingSize;
import org.codefx.demo.effective_java._02_builder_pattern.avoid_optionality.NutritionFacts.Servings;
import org.codefx.demo.effective_java._02_builder_pattern.avoid_optionality.NutritionFacts.Sodium;

import java.util.Map;
import java.util.Optional;

import static java.util.Arrays.stream;
import static java.util.Optional.of;
import static java.util.Optional.ofNullable;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

// NOTE alternatives:
//   To combat optionality, this class uses a collection to collect optional
//   information. Furthermore, it uses specific subtypes of `NutritionFact`,
//   e.g. `Servings` and `Fat`, to identify facts. It uses these types as
//   keys in the map and also offers much richer information thanks to them.
public class NutritionFacts_Specific {

	// NOTE optionality:
	//   I realized too late that `servingSize` and `servings` may also be
	//   managed by the map.

	// required
	private final ServingSize servingSize;
	private final Servings servings;

	// optional
	private final Map<Class<? extends NutritionFact>, NutritionFact> nutritionFacts;

	public NutritionFacts_Specific(
			ServingSize servingSize,
			Servings servings,
			NutritionFact... nutritionFacts) {
		this.servingSize = servingSize;
		this.servings = servings;
		// throws an `IllegalStateException` if there are duplicate facts with the same type;
		// may also wanna make sure that `servingsSize` and `servings` is not duplicated
		this.nutritionFacts = stream(nutritionFacts).collect(toMap(NutritionFact::getClass, identity()));
	}

	public ServingSize servingSize() {
		return servingSize;
	}

	public Servings servings() {
		return servings;
	}

	public <T extends NutritionFact> Optional<T> fact(Class<T> factType) {
		if (factType == ServingSize.class)
			return of((T) servingSize);
		if (factType == Servings.class)
			return of((T) servings);
		return ofNullable(factType.cast(nutritionFacts.get(factType)));
	}

	public Optional<Fat> fat() {
		return fact(Fat.class);
	}

	public Optional<Sodium> sodium() {
		return fact(Sodium.class);
	}

	public Optional<Carbohydrate> carbohydrates() {
		return fact(Carbohydrate.class);
	}

}
