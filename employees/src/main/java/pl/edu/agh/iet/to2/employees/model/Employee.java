package pl.edu.agh.iet.to2.employees.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import pl.edu.agh.iet.to2.employees.IEmployee;

import java.math.BigDecimal;

public class Employee implements IEmployee {

    private long id;

    private StringProperty name;
    private StringProperty surname;

    private StringProperty occupation;
    private ObjectProperty<BigDecimal> salary;

    public Employee() {
        this.name = new SimpleStringProperty("");
        this.surname = new SimpleStringProperty("");
        this.occupation = new SimpleStringProperty("");
        this.salary = new SimpleObjectProperty<>(BigDecimal.ZERO);
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        return occupation.get();
    }

    @Override
    public BigDecimal getSalary() {
        return salary.get();
    }

    @Override
    public String toString() {
        return getName() + " " + getSurname();
    }

    public void setSalary(BigDecimal salary) {
        this.salary.set(salary);
    }

    public void setOccupation(String occupation) {
        this.occupation.set(occupation);
    }

    public String getFullName() {
        return getName() + " " + getSurname();
    }

    public StringProperty getNameProperty() {
        return name;
    }

    public StringProperty getSurnameProperty() {
        return surname;
    }

    public StringProperty getOccupationProperty() {
        return occupation;
    }

    public ObjectProperty<BigDecimal> getSalaryProperty() {
        return salary;
    }

}
