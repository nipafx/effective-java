package org.codefx.demo.effective_java._01_static_factory_methods;

import static org.codefx.demo.effective_java._01_static_factory_methods.Point.ofXY;

// NOTE type-control: `Square` is package-visible, i.e. not part of the public API
class Square extends Rectangle {

	protected final int edgeLength;

	protected Square(Point lowerLeft, int edgeLength) {
		super(lowerLeft, computeUpperRight(lowerLeft, edgeLength));
		this.edgeLength = edgeLength;
	}

	private static Point computeUpperRight(Point lowerLeft, int edgeLength) {
		return ofXY(lowerLeft.x() + edgeLength, lowerLeft.y() + edgeLength);
	}

	public static Square ofLowerLeftAndEdgeLength(Point lowerLeft, int edgeLength) {
		return new Square(lowerLeft, edgeLength);
	}

	@Override
	public String toString() {
		return "[↙" + lowerLeft + " & ⇿" + edgeLength + "]";
	}

}
