# Java8_Program_Practice
This repo contains lot of Java 8 related practical problem statement and solution

## How many male and female employees are there in org?
```Java
employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
```
## print the name of all the department in org
```Java
employeeList.stream().map(Employee::getDepartment).distinct().collect(Collectors.toList());
```

## what is the average age of male and female employees
```Java
employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
```

## Get the details of highest paying employee of the org
```Java
Employee emp = employeeList.stream().collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))).orElse(null);
```
## Get the names of employee who have joined after 2015
```Java
List<String> nameList = employeeList.stream().filter(emp1-> emp1.getYearOfJoining() > 2015).map(Employee::getName).collect(Collectors.toList());
```