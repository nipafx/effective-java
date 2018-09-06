package org.codefx.demo.effective_java._01_static_factory_methods.not_factory_pattern;

import org.codefx.demo.effective_java._01_static_factory_methods.Point;
import org.codefx.demo.effective_java._01_static_factory_methods.Rectangle;

// NOTE not factory pattern:
//   This is an implementation of the abstract factory pattern
//   (https://en.wikipedia.org/wiki/Abstract_factory_pattern)
public class GrowingRectangleFactory implements ShapeFactory {

	private Point lowerLeft;
	private Point upperRight;

	public GrowingRectangleFactory(Point lowerLeft, Point upperRight) {
		this.lowerLeft = lowerLeft;
		this.upperRight = upperRight;
	}

	@Override
	public Shape createShape() {
		upperRight = Point.ofXY(upperRight.x() + 1, upperRight.y() + 1);
		// NOTE not factory pattern:
		//   Static factory methods are no replacement for abstract factory pattern or factory method pattern.
		//   In fact, they don't interact with it at all except that you call a static factory method instead
		//   of a constructor.
		return Rectangle.fromLowerLeftToUpperRight(lowerLeft, upperRight);
	}

}
