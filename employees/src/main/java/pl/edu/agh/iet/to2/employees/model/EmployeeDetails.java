package pl.edu.agh.iet.to2.employees.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class EmployeeDetails {

    private ObservableList<Operation<String>> occupationHistory;
    private ObservableList<Operation<BigDecimal>> salaryHistory;

    private StringProperty occupation;
    private ObjectProperty<BigDecimal> salary;

    public EmployeeDetails(String occupation, BigDecimal salary) {
        this.occupation = new SimpleStringProperty(occupation);
        this.salary = new SimpleObjectProperty<>(salary);

        this.occupationHistory = FXCollections.observableArrayList();
        this.salaryHistory = FXCollections.observableArrayList();
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

    public ObjectProperty<BigDecimal> getSalaryProperty() {
        return salary;
    }

    public StringProperty getOccupationProperty() {
        return occupation;
    }

    public ObservableList<Operation<String>> getOccupationHistory() {
        return occupationHistory;
    }

    public ObservableList<Operation<BigDecimal>> getSalaryHistory() {
        return salaryHistory;
    }

    private <T> void update(List<Operation<T>> operations, T actualValue) {
        if (operations.size() > 0) {
            Operation<T> lastOp = operations.get(operations.size() - 1);
            if (!lastOp.getValue().equals(actualValue)) {
                operations.add(new Operation<>(actualValue, new Date()));
            }
        } else {
            operations.add(new Operation<>(actualValue, new Date()));
        }
    }

    public void update() {
        update(occupationHistory, getOccupation());
        update(salaryHistory, getSalary());
    }
}
