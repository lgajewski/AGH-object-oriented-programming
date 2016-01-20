package pl.edu.agh.iet.to2.employees.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.edu.agh.iet.to2.employees.model.operation.IOperation;
import pl.edu.agh.iet.to2.employees.model.operation.OccupationOperation;
import pl.edu.agh.iet.to2.employees.model.operation.SalaryOperation;

import java.math.BigDecimal;
import java.util.Date;

public class EmployeeDetails {

    private ObservableList<OccupationOperation> occupationHistory;
    private ObservableList<SalaryOperation> salaryHistory;

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

    public ObservableList<OccupationOperation> getOccupationHistory() {
        return occupationHistory;
    }

    public ObservableList<SalaryOperation> getSalaryHistory() {
        return salaryHistory;
    }

    private <T> boolean isUpdateNeeded(ObservableList<? extends IOperation> operations, Object actualValue) {
        if (operations.isEmpty()) {
            return true;
        } else {
            IOperation lastOp = operations.get(operations.size() - 1);
            if (!lastOp.getValue().equals(actualValue)) {
                return true;
            }
        }
        return false;
    }

    public void update() {
        if (isUpdateNeeded(occupationHistory, getOccupation())) {
            occupationHistory.add(new OccupationOperation<>(getOccupation(), new Date()));
        }

        if (isUpdateNeeded(salaryHistory, getSalary())) {
            salaryHistory.add(new SalaryOperation<>(getSalary(), new Date()));
        }
    }
}
