package pl.edu.agh.iet.to2.employees.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.math.BigDecimal;

public class EmployeeDetails {

    private StringProperty occupation;
    private ObjectProperty<BigDecimal> salary;

    public EmployeeDetails(String occupation, BigDecimal salary) {
        this.occupation = new SimpleStringProperty(occupation);
        this.salary = new SimpleObjectProperty<>(salary);
    }

    public BigDecimal getSalary() {
        return salary.get();
    }

    public String getOccupation() {
        return occupation.get();
    }

    public void setOccupation(String occupation) {
        this.occupation.setValue(occupation);
    }

    public void setSalary(BigDecimal salary) {
        this.salary.set(salary);
    }
}
