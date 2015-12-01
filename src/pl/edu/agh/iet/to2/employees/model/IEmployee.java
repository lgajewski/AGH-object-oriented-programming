package pl.edu.agh.iet.to2.employees.model;

/**
 * Created by Patrycja on 01.12.2015.
 */
public interface IEmployee {

    public long getId();

    public String getName();

    public String getSurname();

    public EmployeeDetails getDetails();
}
