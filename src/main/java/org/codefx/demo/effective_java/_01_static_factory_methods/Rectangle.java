package org.codefx.demo.effective_java._01_static_factory_methods;

import static org.codefx.demo.effective_java._01_static_factory_methods.Point.ofXY;

public class Rectangle {

	protected final Point lowerLeft, upperRight;

	// NOTE basics: Constructor is protected to make accidental use less likely while allowing subclassing
	protected Rectangle(Point lowerLeft, Point upperRight) {
		this.lowerLeft = lowerLeft;
		this.upperRight = upperRight;
	}

	public static Rectangle fromLowerLeftToUpperRight(Point lowerLeft, Point upperRight) {
		// NOTE type-control: the code determines at run time what type to return
		if (upperRight.x() - lowerLeft.x() == upperRight.y() - lowerLeft.y())
			return new Square(lowerLeft, upperRight.x() - lowerLeft.x());
		return new Rectangle(lowerLeft, upperRight);
	}

	// NOTE named constructors:
	//   Thanks to static factories, it is easy to distinguish the two ways to construct a `Rectangle`
	public static Rectangle fromUpperLeftToLowerRight(Point upperLeft, Point lowerRight) {
		Point lowerLeft = ofXY(upperLeft.x(), lowerRight.y());
		Point upperRight = ofXY(lowerRight.x(), upperLeft.y());
		return fromLowerLeftToUpperRight(lowerLeft, upperRight
		);
	}

	@Override
	public String toString() {
		return "[↙" + lowerLeft + " & ↗" + upperRight + "]";
	}

}
