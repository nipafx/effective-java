package org.codefx.demo.effective_java._02_builder_pattern;

// NOTE DSL:
//   The common approach to a builder is to have a single class, on which `build()` can be called
//   at any time. If the provided information was incomplete, a `RuntimeException` is the result.
//
//   The `CarFactory` in this class follows a more powerful/complex approach where each required
//   "setter" transitions to a new type of builder and only the last one has a `build()` method.
//   This way, other constructors/methods can use types for their parameters that represent a
//   specific "phase" of the build process. The progression from empty to complete builder is thus
//   encoded in the type system, which means the compiler can check errors that would otherwise
//   only be discovered at run time.
public class TowardsDsl {

	public static class Car {

		private final Body body;
		private final Spoiler spoiler;
		private final Paint paint;
		private final Decal decal;
		private final Tires tires;

		private Car(WithPaintedBodyAndTires builder) {
			this.body = builder.body;
			this.spoiler = builder.spoiler;
			this.paint = builder.paint;
			this.decal = builder.decal;
			this.tires = builder.tires;
		}

	}

	public static class Body { }
	public static class ConvertibleBody extends Body { }

	public static class Spoiler { }
	public static class RoofSpoiler extends Spoiler { }
	public static class RearSpoiler extends Spoiler { }

	public static class Paint { }

	public static class Decal { }

	public static class Tires { }

	public static class CarFactory {

		// NOTE DSL: This method returns `WithBody` instead of a `CarFactory`.
		public WithBody body(Body body) {
			return new WithBody(body);
		}

		public WithConvertibleBody body(ConvertibleBody body) {
			return new WithConvertibleBody(body);
		}

		// NOTE self type:
		//   There is no `build()` method here, because without body, the car is incomplete.

	}

	public static class WithBody {

		private final Body body;
		private Spoiler spoiler;

		public WithBody(Body body) {
			this.body = body;
		}

		public WithBody spoiler(Spoiler spoiler) {
			this.spoiler = spoiler;
			return this;
		}

		public WithPaintedBody paint(Paint paint) {
			return new WithPaintedBody(body, spoiler, paint);
		}

	}

	public static class WithConvertibleBody {

		private final ConvertibleBody body;
		private RearSpoiler spoiler;

		public WithConvertibleBody(ConvertibleBody body) {
			this.body = body;
		}

		public WithConvertibleBody spoiler(RearSpoiler spoiler) {
			this.spoiler = spoiler;
			return this;
		}

		public WithPaintedBody paint(Paint paint) {
			return new WithPaintedBody(body, spoiler, paint);
		}

	}

	public static class WithPaintedBody {

		private final Body body;
		private final Spoiler spoiler;
		private final Paint paint;
		private Decal decal;

		public WithPaintedBody(Body body, Spoiler spoiler, Paint paint) {
			this.body = body;
			this.spoiler = spoiler;
			this.paint = paint;
		}

		public WithPaintedBody decal(Decal decal) {
			this.decal = decal;
			return this;
		}

		public WithPaintedBodyAndTires tires(Tires tires) {
			return new WithPaintedBodyAndTires(body, spoiler, paint, decal, tires);
		}

	}

	public static class WithPaintedBodyAndTires {

		private final Body body;
		private final Spoiler spoiler;
		private final Paint paint;
		private final Decal decal;
		private final Tires tires;

		public WithPaintedBodyAndTires(Body body, Spoiler spoiler, Paint paint, Decal decal, Tires tires) {
			this.body = body;
			this.spoiler = spoiler;
			this.paint = paint;
			this.tires = tires;
			this.decal = decal;
		}

		public Car build() {
			return new Car(this);
		}

	}

}
