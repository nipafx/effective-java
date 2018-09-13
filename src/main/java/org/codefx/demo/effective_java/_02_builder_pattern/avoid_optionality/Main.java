package org.codefx.demo.effective_java._02_builder_pattern.avoid_optionality;

import org.codefx.demo.effective_java._02_builder_pattern.avoid_optionality.NutritionFacts.Carbohydrate;
import org.codefx.demo.effective_java._02_builder_pattern.avoid_optionality.NutritionFacts.Fat;
import org.codefx.demo.effective_java._02_builder_pattern.avoid_optionality.NutritionFacts.ServingSize;
import org.codefx.demo.effective_java._02_builder_pattern.avoid_optionality.NutritionFacts.Servings;
import org.codefx.demo.effective_java._02_builder_pattern.avoid_optionality.NutritionFacts_Primitives.NutritionFactsBuilder;

import java.util.Map;

import static org.codefx.demo.effective_java._02_builder_pattern.avoid_optionality.NutritionFact.Unit.GRAM;
import static org.codefx.demo.effective_java._02_builder_pattern.avoid_optionality.NutritionFact.Unit.PIECE;
import static org.codefx.demo.effective_java._02_builder_pattern.avoid_optionality.NutritionFactType.CARBOHYDRATES;
import static org.codefx.demo.effective_java._02_builder_pattern.avoid_optionality.NutritionFactType.FAT;

public class Main {

	public static void main(String[] args) {
		usePrimitiveFacts();
		useGeneralFacts();
		useSpecificFacts();
	}

	private static void usePrimitiveFacts() {
//		NOTE alternatives: The builder pattern is very readable
		NutritionFacts_Primitives facts = new NutritionFactsBuilder(150, 4)
				.fat(20)
				.carbohydrates(25)
				.build();
		System.out.println(facts.servingSize() + " gram");
		System.out.println(facts.fat() + " gram");
		System.out.println("----");
	}

	private static void useGeneralFacts() {
//		NOTE alternatives:
//		  Constructing these facts is a little more technical than with the builder,
//		  but we don't have the added weight of the builder itself.
		NutritionFacts_General facts = new NutritionFacts_General(
				new SimpleNutritionFact(150, GRAM),
				new SimpleNutritionFact(4, PIECE),
				Map.of(
						FAT, new SimpleNutritionFact(20, GRAM),
						CARBOHYDRATES, new SimpleNutritionFact(25, GRAM)
				));
		System.out.println(facts.servingSize());
		facts.fat().ifPresent(System.out::println);
		System.out.println("----");
	}

	private static void useSpecificFacts() {
//		NOTE alternatives:
//		  Thanks to types with fitting names and a varargs constructor, this
// 		  code reads almost like with named arguments - it's very readable.
		NutritionFacts_Specific facts = new NutritionFacts_Specific(
				new ServingSize(150),
				new Servings(4),
				new Fat(20),
				new Carbohydrate(25));
		System.out.println(facts.servingSize());
		facts.fat().ifPresent(System.out::println);
		System.out.println("----");
	}

}
