# Java8_Program_Practice
This repo contains lot of Java 8 related practical problem statement and solution

### You are given a list of String. You need to find the frequency of each word
```Java
Map<String, Long> stringFrequency = list.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    stringFrequency.forEach((k,v) -> System.out.println(k+"--> "+v));
```
### Fetch the 5th element from a List using Streams
```java
List<Integer> intList = IntStream.range(1, 11).boxed().collect(Collectors.toList());
intList.stream().skip(14).findFirst().ifPresent(System.out::println);
```

### Print pattern like abbcccdddd from "abcd" using Streams
```java
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
```

### Separate a list into duplicate and unique
```java
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
```

### Print max occurring character from a string
```java
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
```

### Print the top 3 longest Strings using Java 8 Streams
```java
		List<String> strList = Arrays.asList("abc", "defg", "hijklmno", "pqr", "stu", "vwxyz");
        strList.stream()
                .sorted(Comparator.comparingInt(String::length).reversed())
                .limit(3)
                .forEach(System.out::println);
```

### Find the second-highest occurring character in a String
```java
String str = "abbcdeebbsd";
Optional<Map.Entry<Character, Long>> secondMostFrequent = str.chars()
        .mapToObj(i -> (char) i) // convert int to Character
        .collect(Collectors.groupingBy(c -> c, Collectors.counting())) // grouping
        .entrySet()
        .stream()
        .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue())) // sorting in descending order
        .skip(1)
        .findFirst();

secondMostFrequent.ifPresent(entry -> System.out.println("Second most frequent character: " + entry.getKey() + ", Count: " + entry.getValue()));
```



## Object Related Problem
### How many male and female employees are there in org?
```Java
employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
```
### print the name of all the department in org
```Java
employeeList.stream().map(Employee::getDepartment).distinct().collect(Collectors.toList());
```

### Count the number of employees in each department
```Java
employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting())).entrySet().forEach(entry-> System.out.println(entry.getKey()+"------------"+entry.getValue()));;
```

### what is the average age of male and female employees
```Java
employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
```

### Get the details of highest paying employee of the org
```Java
Employee emp = employeeList.stream().collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))).orElse(null);
```
### Get the names of employee who have joined after 2015
```Java
List<String> nameList = employeeList.stream().filter(emp1-> emp1.getYearOfJoining() > 2015).map(Employee::getName).collect(Collectors.toList());
```

### Get the average salary from each department
```Java
employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
```

### Get the details of youngest male employee in the product development department
```Java
Employee youngestEmployee = employeeList.stream().filter(employee-> employee.getDepartment().equalsIgnoreCase("Product Development") && employee.getGender().equalsIgnoreCase("male")).sorted(Comparator.comparingInt(Employee::getAge)).findFirst().orElse(null);
```

### Who has most working experience in Org
```Java
Employee mostExpEmployee = employeeList.stream().sorted(Comparator.comparingInt(Employee::getYearOfJoining)).findFirst().orElse(null);
```

### List down all employee names from each department
```Java
Map<String, List<Employee>> empListDept = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.toList()));
		empListDept.entrySet().forEach(entry-> {
			System.out.println(entry.getKey());
			System.out.println("---------------------------------------");
			entry.getValue().stream().map(Employee::getName).forEach(System.out::println);
		});
```

### What is the average and total salary in org
```Java
DoubleSummaryStatistics salary_statistics = employeeList.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
		System.out.println("Average Salary : "+salary_statistics.getAverage());
		System.out.println("Total Salary : "+salary_statistics.getSum());
```

### Separate all the employees who are younger or equals to 25 and senior than 25 years
```Java
Map<Boolean, List<Employee>> partition_employee = employeeList.stream().collect(Collectors.partitioningBy(e-> e.getAge()> 25));
		partition_employee.entrySet().forEach(entry-> {
			if(entry.getKey()) {
				System.out.println("Persons above 25 years");
				System.out.println("----------------------------------------");
			}
			else {
				System.out.println("Persons below 25 years");
				System.out.println("----------------------------------------");
			}
			entry.getValue().stream().map(Employee::getName).forEach(System.out::println);
		});
```

## Array related problem statement
### Merge two sorted or unsorted array and remove duplicate
```java
int[] ints = IntStream.concat(IntStream.of(arr1), IntStream.of(arr2))
				.distinct()
				.sorted()
				.toArray();
```

### Reverse an array
```java
List<Integer> reverseArray = IntStream.range(0, arr3.length).mapToObj(e -> arr3[arr3.length - 1 - e]).collect(Collectors.toList());
```

## Map related problem statement
### Sort map based on key
```Java
LinkedHashMap<String, Integer> keySortedMap = map.entrySet()
        .stream().sorted(Map.Entry.comparingByKey())
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
```

### Sort map based on Value
```Java
Map<String, Integer> valueSortedMap = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue)-> newValue, LinkedHashMap::new));
```

### Merge two maps: consider highest value if keys are same
```Java
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
```

