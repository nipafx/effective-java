package org.codefx.demo.effective_java._02_builder_pattern;

public class SelfTypes {

	public abstract static class Person {

		private final String name;
		private final String address;

		protected Person(PersonBuilder<?, ?> builder) {
			this.name = builder.name;
			this.address = builder.address;
		}

		public String name() {
			return name;
		}

		public String address() {
			return address;
		}

	}

	public static class Employee extends Person {

		private Employee(EmployeeBuilder builder) {
			super(builder);
		}

	}

	public static class Freelancer extends Person {

		private Freelancer(FreelancerBuilder builder) {
			super(builder);
		}

	}

	// NOTE self type:
	//   With a recursive generic type `SELF extends PersonBuilder<SELF>` (ignoring `P` for a moment,
	//   which does not pertain to this), it is possible to  capture "type of this instance".
	public static abstract class PersonBuilder<P extends Person, SELF extends PersonBuilder<P, SELF>> {

		protected String name;
		protected String address;

		// NOTE self type:
		//   Thanks to `SELF`, `name(String)` can express that it returns not just a `PersonBuilder`,
		//   but an instance of the concrete builder it was called on.
		public SELF name(String name) {
			this.name = name;
			// NOTE self type:
			//   This cast is safe, so I find it tolerable. See below for an alternative.
			return (SELF) this;
		}

		// NOTE self type:
		//   To avoid the casts `(SELF) this`, call this method instead.
//		protected abstract SELF self();

		public SELF address(String address) {
			this.address = address;
			return (SELF) this;
		}

		public abstract P build();

	}

	public static class EmployeeBuilder extends PersonBuilder<Employee, EmployeeBuilder> {

		@Override
		public Employee build() {
			return new Employee(this);
		}

		// NOTE self type:
		//   Here's how to implement `self()` in a concrete builder.
//		@Override
//		protected EmployeeBuilder self() {
//			return this;
//		}

	}

	public static class FreelancerBuilder extends PersonBuilder<Freelancer, FreelancerBuilder> {

		@Override
		public Freelancer build() {
			return new Freelancer(this);
		}

	}

}
