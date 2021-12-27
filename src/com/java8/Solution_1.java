package com.java8;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution_1 {

	public static void main(String[] args) {
		
		List<Employee> employeeList = new ArrayList<Employee>();
	       
		employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
		employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
		employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
		employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
		employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
		employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
		employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
		employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
		employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
		employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
		employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
		employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
		employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
		employeeList.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
		employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
		employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
		employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));
// entry-> System.out.println(entry.getKey()+"------------"+entry.getValue())
		
		// How many male and female employees are there in org?
		System.out.println("------------------------------------1--------------------------------");
		employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting())).entrySet().forEach(entry-> System.out.println(entry.getKey()+"------------"+entry.getValue()));

		// print the name of all the department in org.
		System.out.println("------------------------------------2--------------------------------");
		List<String> departmentList = employeeList.stream().map(Employee::getDepartment).distinct().collect(Collectors.toList());
		System.out.println(departmentList);
		
		// Count the number of employees in each department
		System.out.println("------------------------------------3--------------------------------");
		employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting())).entrySet().forEach(entry-> System.out.println(entry.getKey()+"------------"+entry.getValue()));;
		
		// what is the average age of male and female employees
		System.out.println("------------------------------------4--------------------------------");
		employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge))).entrySet().forEach(entry-> System.out.println(entry.getKey()+"------------"+entry.getValue()));
		
		// Get the details of highest paying employee of the org
		System.out.println("------------------------------------5--------------------------------");
		Employee emp = employeeList.stream().collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))).orElse(null);
		System.out.println(emp);
		
		// Get the names of employee who have joined after 2015
		System.out.println("------------------------------------6--------------------------------");
		List<String> nameList = employeeList.stream().filter(emp1-> emp1.getYearOfJoining() > 2015).map(Employee::getName).collect(Collectors.toList());
		System.out.println(nameList);
		
		// Get the average salary from each department
		System.out.println("------------------------------------7--------------------------------");
		employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary))).entrySet().forEach(entry-> System.out.println(entry.getKey()+"------------"+entry.getValue()));
		
		// Get the details of youngest male employee in the product development department
		System.out.println("------------------------------------8--------------------------------");
		Employee youngestEmployee = employeeList.stream().filter(employee-> employee.getDepartment().equalsIgnoreCase("Product Development") && employee.getGender().equalsIgnoreCase("male")).sorted(Comparator.comparingInt(Employee::getAge)).findFirst().orElse(null);
		System.out.println(youngestEmployee);
		
		// Who has most working experience in Org
		System.out.println("------------------------------------9--------------------------------");
		Employee mostExpEmployee = employeeList.stream().sorted(Comparator.comparingInt(Employee::getYearOfJoining)).findFirst().orElse(null);
		System.out.println(mostExpEmployee);
		
		// List down all employee names from each department
		System.out.println("------------------------------------10--------------------------------");
		Map<String, List<Employee>> empListDept = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.toList()));
		empListDept.entrySet().forEach(entry-> {
			System.out.println(entry.getKey());
			System.out.println("---------------------------------------");
			entry.getValue().stream().map(Employee::getName).forEach(System.out::println);
		});
		
		// What is the average and total salary in org
		System.out.println("------------------------------------11--------------------------------");
		DoubleSummaryStatistics salary_statistics = employeeList.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
		System.out.println("Average Salary : "+salary_statistics.getAverage());
		System.out.println("Total Salary : "+salary_statistics.getSum());
		
		// Separate all the employees who are younger or equals to 25 and senior than 25 years
		System.out.println("------------------------------------12--------------------------------");
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


		// Merge two sorted or unsorted array and remove duplicate
		System.out.println("------------------------------------13--------------------------------");
		int[] arr1 = new int[]{10,-7,8,12,5};
		int[] arr2 = new int[]{1,3,5,7,9,11,13};
		int[] ints = IntStream.concat(IntStream.of(arr1), IntStream.of(arr2))
				.distinct()
				.sorted()
				.toArray();
		IntStream.of(ints).forEach(System.out::println);

		// Reverse an array
		System.out.println("------------------------------------14--------------------------------");
		int[] arr3 = new int[]{1,3,5,7,9,11,13};
		List<Integer> reverseArray = IntStream.range(0, arr3.length).mapToObj(e -> arr3[arr3.length - 1 - e]).collect(Collectors.toList());
		System.out.println(reverseArray);

		// Most Frequent element in an array
		int[] arr4 = new int[]{4,5,8,7,4,7,6,7};
		HashMap<Integer, Integer> frequencyMap = new HashMap<>();
		for(int i=0; i< arr4.length; i++){
			frequencyMap.put(arr4[i], frequencyMap.containsKey(arr4[i])? frequencyMap.get(arr4[i])+1: 1);
		}
		LinkedHashMap<Integer, Integer> collect = frequencyMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		//Optional<Integer> first = collect.keySet().stream().findFirst().;
		System.out.println(collect);
	}

}
