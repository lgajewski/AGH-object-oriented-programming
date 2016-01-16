package pl.edu.agh.iet.to2.employees.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import pl.edu.agh.iet.to2.employees.IEmployee;

import java.math.BigDecimal;

public class Employee implements IEmployee {

    private long id;

    private StringProperty name;
    private StringProperty surname;

    private EmployeeDetails details;

    public Employee() {
        this.name = new SimpleStringProperty("");
        this.surname = new SimpleStringProperty("");
        this.details = new EmployeeDetails("", BigDecimal.ZERO);
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
        return details.getOccupation();
    }

    @Override
    public BigDecimal getSalary() {
        return details.getSalary();
    }

    @Override
    public String toString() {
        return getName() + " " + getSurname();
    }

    public void setSalary(BigDecimal salary) {
        details.setSalary(salary);
    }

    public void setOccupation(String occupation) {
        this.details.setOccupation(occupation);
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
        return details.getOccupationProperty();
    }

    public ObjectProperty<BigDecimal> getSalaryProperty() {
        return details.getSalaryProperty();
    }

    public ObservableList<Operation<String>> getOccupationHistory() {
        return details.getOccupationHistory();
    }

    public ObservableList<Operation<BigDecimal>> getSalaryHistory() {
        return details.getSalaryHistory();
    }

    public void update() {
        details.update();
    }

}
