package org.codefx.demo.effective_java._02_builder_pattern;

import org.codefx.demo.effective_java._02_builder_pattern.SelfTypes.Employee;
import org.codefx.demo.effective_java._02_builder_pattern.SelfTypes.EmployeeBuilder;
import org.codefx.demo.effective_java._02_builder_pattern.SelfTypes.Freelancer;
import org.codefx.demo.effective_java._02_builder_pattern.SelfTypes.FreelancerBuilder;
import org.codefx.demo.effective_java._02_builder_pattern.TowardsDsl.Body;
import org.codefx.demo.effective_java._02_builder_pattern.TowardsDsl.Car;
import org.codefx.demo.effective_java._02_builder_pattern.TowardsDsl.CarFactory;
import org.codefx.demo.effective_java._02_builder_pattern.TowardsDsl.ConvertibleBody;
import org.codefx.demo.effective_java._02_builder_pattern.TowardsDsl.Decal;
import org.codefx.demo.effective_java._02_builder_pattern.TowardsDsl.Paint;
import org.codefx.demo.effective_java._02_builder_pattern.TowardsDsl.RearSpoiler;
import org.codefx.demo.effective_java._02_builder_pattern.TowardsDsl.RoofSpoiler;
import org.codefx.demo.effective_java._02_builder_pattern.TowardsDsl.Tires;

/*
 * You will find NOTEs on the following topics:
 *  - alternatives
 *  - DSL
 *  - self type
 *  - refactoring
 */

public class Main {

	public static void main(String[] args) {
		towardsDsl();
		selfType();
	}

	private static void towardsDsl() {
		Car car = new CarFactory()
				.body(new Body())
				.spoiler(new RoofSpoiler())
				.paint(new Paint())
				.decal(new Decal())
				.tires(new Tires())
				.build();

		Car convertible = new CarFactory()
				.body(new ConvertibleBody())
				// can't add a roof spoiler!
				.spoiler(new RearSpoiler())
				.paint(new Paint())
				.decal(new Decal())
				.tires(new Tires())
				.build();
	}

	private static void selfType() {
		Employee john = new EmployeeBuilder()
				.name("John Doe")
				.address("Paris")
				.build();
		Freelancer jane = new FreelancerBuilder()
				.name("Jane Doe")
				.address("Paris")
				.build();
	}

}
