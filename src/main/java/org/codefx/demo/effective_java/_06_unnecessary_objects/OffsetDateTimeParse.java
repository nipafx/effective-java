package org.codefx.demo.effective_java._06_unnecessary_objects;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Warmup;

//@Fork(value = 2, jvmArgsAppend = "-Djmh.stack.lines=3")
//@Warmup(iterations = 5, time = 2)
//@Measurement(iterations = 7, time = 2)
public class OffsetDateTimeParse extends ObjectCreationBenchmarks {

	@Benchmark
	public Object parse() {
		// NOTE unnecessary object:
		//   The `OffsetDateTime` parser uses a map to store parsed values,
		//   which produces a measurable amount of garbage.
		//   See https://bugs.openjdk.java.net/browse/JDK-8213243 for details.
		return OffsetDateTime.parse("2001-02-03T04:05:06.789+01:00");
	}

	@Benchmark
	public Object handwrittenParser() {
		return OffsetDateTimeParser.offsetDateTime("2001-02-03T04:05:06.789+01:00");
	}

	/**
	 * This class was written by Lukas Eder and attached to JDK-8213243
	 * (https://bugs.openjdk.java.net/browse/JDK-8213243)
	 * to demonstrate a faster parser than {@link OffsetDateTime#parse(CharSequence)}.
	 */
	static final class OffsetDateTimeParser {

		static final OffsetTime offsetTime(String string) {
			if (string == null)
				return null;

			// Out parameter emulation
			int[] position = { 0 };
			return OffsetTime.of(parseLocalTime(string, position), parseOffset(string, position));
		}

		static final OffsetDateTime offsetDateTime(String string) {
			if (string == null)
				return null;

			// Out parameter emulation
			int[] position = { 0 };

			LocalDate d = parseLocalDate(string, position);

			// [#4338] SQL supports the alternative ISO 8601 date format, where a
			// whitespace character separates date and time. java.time does not
			parseAnyChar(string, position, " T");
			LocalTime t = parseLocalTime(string, position);

			return OffsetDateTime.of(d, t, parseOffset(string, position));
		}

		static final LocalDate parseLocalDate(String string, int[] position) {
			int year = parseInt(string, position, 4);

			parseChar(string, position, '-');
			int month = parseInt(string, position, 2);

			parseChar(string, position, '-');
			int day = parseInt(string, position, 2);

			return LocalDate.of(year, month, day);
		}

		static final LocalTime parseLocalTime(String string, int[] position) {
			int hour = parseInt(string, position, 2);

			// [#5895] HSQLDB seems to confuse 00:00:00+02:00 with 24:00:00+02:00
			// https://sourceforge.net/p/hsqldb/bugs/1523/
			if (hour == 24)
				hour = hour % 24;

			parseChar(string, position, ':');
			int minute = parseInt(string, position, 2);
			int second = 0;
			int nano = 0;

			if (parseCharIf(string, position, ':')) {
				second = parseInt(string, position, 2);

				if (parseCharIf(string, position, '.')) {
					nano = 1000000 * parseInt(string, position, 3);

					if (Character.isDigit(string.charAt(position[0]))) {
						nano = nano + 1000 * parseInt(string, position, 3);

						if (Character.isDigit(string.charAt(position[0]))) {
							nano = nano + parseInt(string, position, 3);
						}
					}
				}
			}

			return LocalTime.of(hour, minute, second, nano);
		}

		private static final ZoneOffset parseOffset(String string, int[] position) {
			int offsetHours = 0;
			int offsetMinutes = 0;

			if (!parseCharIf(string, position, 'Z')) {

				// [#4965] Oracle might return some spare space
				while (parseCharIf(string, position, ' '))
					;

				boolean minus = parseCharIf(string, position, '-');
				boolean plus = !minus && parseCharIf(string, position, '+');

				if (minus || plus) {
					offsetHours = parseInt(string, position, 1);

					// [#4965] Oracle might return a single-digit hour offset
					if (Character.isDigit(string.charAt(position[0])))
						offsetHours = offsetHours * 10 + parseInt(string, position, 1);

					// [#4338] [#5180] [#5776] PostgreSQL is more lenient regarding the offset format
					if (parseCharIf(string, position, ':'))
						offsetMinutes = parseInt(string, position, 2);
				}
			}

			return ZoneOffset.ofHoursMinutes(offsetHours, offsetMinutes);
		}

		private static final void parseAnyChar(String string, int[] position, String expected) {
			for (int i = 0; i < expected.length(); i++) {
				if (string.charAt(position[0]) == expected.charAt(i)) {
					position[0] = position[0] + 1;
					return;
				}
			}

			throw new IllegalArgumentException("Expected any of \"" + expected + "\" at position " + position[0] + " in " + string);
		}

		private static final boolean parseCharIf(String string, int[] position, char expected) {
			boolean result = string.length() > position[0] && string.charAt(position[0]) == expected;
			if (result)
				position[0] = position[0] + 1;
			return result;
		}

		private static final void parseChar(String string, int[] position, char expected) {
			if (!parseCharIf(string, position, expected))
				throw new IllegalArgumentException("Expected '" + expected + "' at position " + position[0] + " in " + string);
		}

		private static final int parseInt(String string, int[] position, int length) {
			int result = 0;

			for (int i = position[0] + length - 1, dec = 1; i >= position[0]; i--, dec = dec * 10) {
				int digit = string.charAt(i) - '0';

				if (digit >= 0 && digit < 10)
					result = result + dec * digit;
				else
					throw new NumberFormatException("Not a number: " + string);
			}

			position[0] = position[0] + length;
			return result;
		}
	}
}
