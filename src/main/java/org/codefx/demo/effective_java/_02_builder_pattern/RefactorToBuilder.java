package org.codefx.demo.effective_java._02_builder_pattern;

// NOTE refactoring: Your IDE should help you refactor from constructor to builder.
public class RefactorToBuilder {

	public static void main(String[] args) {
		Person p = new Person("Jane Doe");
		System.out.println(p);
	}

	public static class Person {

		private final String name;

		public Person(String name) {
			this.name = name;
		}

	}

}
