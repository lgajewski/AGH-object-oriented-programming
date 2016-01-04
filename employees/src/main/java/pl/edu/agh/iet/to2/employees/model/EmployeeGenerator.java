package pl.edu.agh.iet.to2.employees.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EmployeeGenerator {

    private Random random = new Random();

    private static String[] names = {
            "Anne", "Arthur", "Lawrence", "Mildred", "Joseph", "Susan", "Louise", "Jean", "Terry", "Peter", "Fred", "Steve", "Todd", "Billy", "Kevin"
    };

    private static String[] surnames = {
            "Alexander", "Cruz", "Wood", "Gilbert", "Andrews", "Stevens", "Jenkins", "Cunningham", "Jackson", "Bennett", "Burns", "Ward", "Day", "Armstrong", "Crawford"
    };

    public List<Employee> generate(int amount) {
        List<Employee> employeeList = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            String name = getRandomValue(names);
            String surname = getRandomValue(surnames);

            // create and add employee to the result list
            Employee employee = new Employee();
            employee.setName(name);
            employee.setSurname(surname);
            employeeList.add(employee);
        }

        return employeeList;
    }

    private String getRandomValue(String[] array) {
        int index = random.nextInt(array.length);
        return array[index];
    }


}
