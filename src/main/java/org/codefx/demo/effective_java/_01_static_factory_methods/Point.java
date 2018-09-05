package org.codefx.demo.effective_java._01_static_factory_methods;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.ExecutionException;

public class Point {

	// NOTE instance-control: ORIGIN is a constant
	private static final Point ORIGIN = new Point(0, 0);
	// NOTE instance-control: Points can be cached
	private static final Cache<Integer, Cache<Integer, Point>> POINTS_BY_X_Y = CacheBuilder.newBuilder().build();

	private final int x, y;

	// NOTE basics: The constructor is private to avoid accidental use; this also prevents subclassing
	private Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// NOTE instance-control: The method's contract clarifies that there is no guarantee regarding identity
	/**
	 * @return A {@code Point} instance with the specified x/y coordinates (not necessarily a new instance)
	 */
	// NOTE named constructors:
	//   * `ofXY` explains very well what the parameters mean;
	//   * `Rectangle`'s static factories are even more informative
	// NOTE basics: A static factory method is `static` and returns an instance of the type (Duh!)
	public static Point ofXY(int x, int y) {
		if (x == 0 && y == 0)
			return ORIGIN;
		// NOTE instance-control: Only points with specific properties (in the 20x20 box around origin) care cached
		if (Math.abs(x) <= 10 && Math.abs(y) <= 10)
			try {
				return POINTS_BY_X_Y
						.get(x, () -> CacheBuilder.newBuilder().build())
						.get(y, () -> new Point(x, y));
			} catch (ExecutionException e) {
				// do nothing, ignore the cache, and construct a new point
			}
		return new Point(x, y);
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	@Override
	public String toString() {
		return "(" + x + "/" + y + ")";
	}

}
