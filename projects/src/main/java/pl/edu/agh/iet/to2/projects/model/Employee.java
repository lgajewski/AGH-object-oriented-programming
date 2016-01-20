package pl.edu.agh.iet.to2.projects.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pl.edu.agh.iet.to2.employees.IEmployee;
import pl.edu.agh.iet.to2.employees.ISalary;

import java.math.BigDecimal;

public class Employee implements IEmployee {

    private long id;
    private String firstName;
    private String lastName;
    private ISalary salary;
    private String occupation;

    public Employee(){

    }

    public Employee(long id, String firstName, String lastName, ISalary salary, String occupation) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.occupation = occupation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return firstName;
    }

    public void setName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return lastName;
    }

    public void setSurname(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return salary.getSalary();
    }

    public void setSalary(ISalary salary) {
        this.salary = salary;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public StringProperty getFullNameProperty() {
        return new SimpleStringProperty(firstName + " " + lastName);
    }

    public ObjectProperty getSalaryProperty() {
        return new SimpleObjectProperty<BigDecimal>(salary.getSalary());
    }

}

