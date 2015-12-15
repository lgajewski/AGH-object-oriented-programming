package pl.edu.agh.iet.to2.employees.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.math.BigDecimal;

public class Employee implements IEmployee {

    private long id;

    private StringProperty name;
    private StringProperty surname;

    private final ObjectProperty<EmployeeDetails> details;

    public Employee(String name, String surname) {
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.details = new SimpleObjectProperty<>(new EmployeeDetails());
    }

    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    @Override
    public String getSurname() {
        return surname.get();
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    @Override
    public String getOccupation() {
        return details.get().getOccupation() == null ? "Occupation" : details.get().getOccupation();
    }

    public void setDetails(EmployeeDetails details) {
        this.details.set(details);
    }

    @Override
    public BigDecimal getSalary() {
        return null;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }

}
