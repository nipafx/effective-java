package org.codefx.demo.effective_java._01_static_factory_methods;

/*
 * You will find NOTEs on the following topics:
 *  - basics
 *  - named constructors
 *  - instance-control
 *  - type-control
 */

public class Main {

	public static void main(String[] args) {
		namedConstructors();
		instanceControl();
		typeControl();
	}

//	private static void before() {
//		// NOTE basics:
//		//   * the code must read `new $CLASS` - no way to give it a nice name
//		//   * the code will create a new instance - no way to, e.g., cache instances
//		//   * the code will create a `Point` - no way to create a more suitable subtype
//		Point origin = new Point(0, 0);
//		Point somePoint = new Point(42, 63);
//		Rectangle rectangle = new Rectangle(origin, somePoint);
//		System.out.println(rectangle);
//	}

	private static void namedConstructors() {
		Point origin = Point.ofXY(0, 0);
		Point upperRight = Point.ofXY(42, 63);
		Rectangle someRectangle = Rectangle.fromLowerLeftToUpperRight(origin, upperRight);

		Point onXAxis = Point.ofXY(0, 63);
		Point onYAxis = Point.ofXY(42, 0);
		Rectangle otherRectangle = Rectangle.fromUpperLeftToLowerRight(onXAxis, onYAxis);

		System.out.println(someRectangle + " vs " + otherRectangle);
	}

	private static void instanceControl() {
		Point origin = Point.ofXY(0, 0);
		Point otherOrigin = Point.ofXY(0, 0);
		if (origin == otherOrigin)
			System.out.println("There can only be one origin!");

		Point somePoint = Point.ofXY(4, 2);
		Point otherPoint = Point.ofXY(4, 2);
		if (somePoint == otherPoint)
			System.out.println("There can only be one 4/2!");
	}

	private static void typeControl() {
		Point origin = Point.ofXY(0, 0);
		Point upperRight = Point.ofXY(42, 42);
		Rectangle square = Rectangle.fromLowerLeftToUpperRight(origin, upperRight);

		if (square instanceof Square)
			System.out.println(square);
	}

}
