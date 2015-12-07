package pl.edu.agh.iet.to2.employees.controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class EmployeeCellController {

    @FXML
    private Label employeeName;

    @FXML
    private Label employeeOccupation;

    @FXML
    private CheckBox employeeCheckBox;

    public String getEmployeeName() {
        return employeeName.getText();
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName.setText(employeeName);
    }

    public String getEmployeeOccupation() {
        return employeeOccupation.getText();
    }

    public void setEmployeeOccupation(String employeeOccupation) {
        this.employeeOccupation.setText(employeeOccupation);
    }

    public boolean isSelected() {
        return employeeCheckBox.isSelected();
    }

}
