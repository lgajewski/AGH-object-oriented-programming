package pl.edu.agh.iet.to2.employees.model;

/**
 * Created by Patrycja on 01.12.2015.
 */
public interface IEmployee {
    long getId();

    String getName();

    String getSurname();

    EmployeeDetails getDetails();
}
