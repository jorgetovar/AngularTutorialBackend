package co.com.jetprogramming.utils;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamUtils {
	
	private StreamUtils(){}

	public static <T> Stream<T> stream(Iterable<T> in) {
		return StreamSupport.stream(in.spliterator(), false);
	}

	public static <T> Stream<T> parallelStream(Iterable<T> in) {
		return StreamSupport.stream(in.spliterator(), true);
	}
	
}
