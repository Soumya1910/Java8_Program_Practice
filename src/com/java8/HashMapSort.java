package com.java8;

import java.util.*;
import java.util.stream.Collectors;

public class HashMapSort {

    private static int max=1000;
    private static int min = 1;

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        Random rand = new Random();
        for(int i=0;i < 10; i++){
            map.put(UUID.randomUUID().toString(), rand.nextInt((max - min) + 1) + min);
        }

        System.out.println(map);
        System.out.println("-----------------");

        // Sort map based on key
        LinkedHashMap<String, Integer> keySortedMap = map.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        System.out.println(keySortedMap);

        // Sort map based on value
        Map<String, Integer> valueSortedMap = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue)-> newValue, LinkedHashMap::new));

        System.out.println(valueSortedMap);

        // Merge two Maps

        HashMap<String, Integer> subjectMarks1 = new HashMap<>();
        subjectMarks1.put("Bengali", 75);
        subjectMarks1.put("English", 80);
        subjectMarks1.put("Math", 95);

        HashMap<String, Integer> subjectMarks2 = new HashMap<>();
        subjectMarks2.put("Economics", 75);
        subjectMarks2.put("English", 80);
        subjectMarks2.put("Math", 90);
        subjectMarks2.put("Science", 95);

        subjectMarks1.forEach((key, value)-> subjectMarks2.merge(key, value, (v1, v2)-> v1>v2? v1: v2));
        System.out.println(subjectMarks2);




    }
}
