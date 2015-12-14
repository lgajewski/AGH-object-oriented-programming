package pl.edu.agh.iet.to2.employees.controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import pl.edu.agh.iet.to2.employees.model.IEmployee;

public class EmployeeCellController {

    private final IEmployee employee;

    public EmployeeCellController(IEmployee employee) {
        this.employee = employee;
    }

    @FXML
    private Label employeeName;

    @FXML
    private Label employeeOccupation;

    @FXML
    private CheckBox employeeCheckBox;

    @FXML
    private void initialize() {
        employeeName.setText(employee.getName() + " " + employee.getSurname());
        employeeOccupation.setText(employee.getOccupation());
    }


}
