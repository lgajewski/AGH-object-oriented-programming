package pl.edu.agh.iet.to2.employees.controller;


import pl.edu.agh.iet.to2.employees.model.Employee;

import java.util.function.Predicate;

class EmployeePredicate implements Predicate<Employee> {

    private String pattern;

    public EmployeePredicate(String pattern) {
        this.pattern = pattern.toLowerCase();
    }

    @Override
    public boolean test(Employee iEmployee) {
        return testOccupation(iEmployee.getOccupation()) || testName(iEmployee.getName(), iEmployee.getSurname());
    }

    private boolean testOccupation(String occupation) {
        return occupation.toLowerCase().contains(pattern);
    }

    private boolean testName(String name, String surname) {
        return (name + " " + surname).toLowerCase().contains(pattern);
    }
}