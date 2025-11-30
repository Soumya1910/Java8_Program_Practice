package com.java8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Miscellaneous {
	public static void main(String[] args) {
		// Fetch the 5th element from a List using Streams
		List<Integer> intList = IntStream.range(1, 11).boxed().collect(Collectors.toList());
		intList.stream().skip(14).findFirst().ifPresent(System.out::println);

		// Find the second-highest occurring character in a String
		String str = "abbcdeebbsd";
		Optional<Map.Entry<Character, Long>> secondMostFrequent = str.chars()
				.mapToObj(i -> (char) i)
				.collect(Collectors.groupingBy(c -> c, Collectors.counting()))
				.entrySet()
				.stream()
				.sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
				.skip(1)
				.findFirst();

		secondMostFrequent.ifPresent(entry -> System.out.println("Second most frequent character: " + entry.getKey() + ", Count: " + entry.getValue()));

		// Print pattern like abbcccdddd from "abcd" using Streams
		String pattern = "abcd";
		IntStream.range(0, pattern.length())// Creates a stream of integers: `[0, 1, 2, 3]` for "abcd".
				.flatMap(i -> IntStream.range(0, i+1).map(x -> pattern.charAt(i)))
				.mapToObj(c -> String.valueOf((char)c))
				.collect(Collectors.joining());

		/*
		* .flatMap(i -> IntStream.range(0, i+1).map(x -> pattern.charAt(i)))
		*
		* For each index `i`, create another IntStream that repeats the corresponding character `i+1` times.
		* IntStream.range(0, i + 1) generates:
			i = 0 → range(0,1) → 0 → one value → `a`
			i = 1 → range(0,2) → 0, 1 → two values → `b b`
			i = 2 → range(0,3) → 0, 1, 2 → three values → `c c c`
			i = 3 → range(0,4) → 0, 1, 2, 3 → four values → `d d d d`

		* .mapToObj(c -> String.valueOf((char) c))
		* Converts each int (character code) back into a String form so we can join them.
		* ["a", "b", "b", "c", "c", "c", "d", "d", "d", "d"]
		*
		* .collect(Collectors.joining())
		* Joins all elements in the stream without space into a single string.
		* */


		// Separate a List into duplicates and unique elements
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 5, 6, 7, 8, 9, 9, 10);
		Map<Integer, Long> frequencyMap = list
				.stream()
				.collect(Collectors.groupingBy(i -> i, Collectors.counting()));
		List<Integer> uniqueList = frequencyMap
				.entrySet()
				.stream()
				.filter(entry -> entry.getValue() == 1)
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());

		List<Integer> duplicatesList = frequencyMap
				.entrySet()
				.stream()
				.filter(entry -> entry.getValue() > 1)
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());

		// Find common elements between two sorted lists
		List<Integer> list1 = Arrays.asList(1,3,5,7,9,11,13,15,17,19,21);
		List<Integer> list2 = Arrays.asList(5,10,15,20,25);
		List<Integer> commonElements = IntStream.range(0, list1.size())
				.filter(i -> list2.contains(list1.get(i)))
				.boxed()
				.collect(Collectors.toList());

		// Print max occurring character from a string
		System.out.println("--------Max occurring character------");
		String str1 = "aaabcdddddddddedsasdf";
		str1.chars()
				.boxed()
				.collect(Collectors.groupingBy(c -> c, Collectors.counting()))
				.entrySet()
				.stream()
				.sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
				.findFirst()
				.ifPresent(entry ->
						System.out.println((char)(int)entry.getKey() + " occurs " + entry.getValue() + " times")
				);

		// Print the top 3 longest Strings using Java 8 Streams
		List<String> strList = Arrays.asList("abc", "defg", "hijklmno", "pqr", "stu", "vwxyz");
		strList.stream()
				.sorted(Comparator.comparingInt(String::length).reversed())
				.limit(3)
				.forEach(System.out::println);


	}
}