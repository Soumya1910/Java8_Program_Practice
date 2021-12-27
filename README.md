# Java8_Program_Practice
This repo contains lot of Java 8 related practical problem statement and solution

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