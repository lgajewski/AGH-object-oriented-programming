package pl.edu.agh.iet.to2.employees.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.edu.agh.iet.to2.employees.model.Employee;
import pl.edu.agh.iet.to2.employees.model.EmployeeDetails;

import java.math.BigDecimal;

public class EmployeeAddDialogController {

    private Employee employee;

    private EmployeeDetails details;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField surnameTextField;

    @FXML
    private TextField nicknameTextField;

    @FXML
    private TextField occupationTextField;

    private boolean approved;

    public boolean isApproved() {
        return approved;
    }

    @FXML
    private void initialize() {

    }

    @FXML
    private void handleOkEvent(ActionEvent event) {
        if (isInputValid()) {
            updateModel();
            approved = true;
            Stage stage = (Stage) nameTextField.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void handleCancelEvent(ActionEvent event) {
        Stage stage = (Stage) nameTextField.getScene().getWindow();
        stage.close();
    }

    private boolean isInputValid() {
        // TODO: implement

        return true;
    }

    private void updateModel() {
//        employee.setName(nameTextField.getText());
//        employee.setSurname(surnameTextField.getText());
        employee = new Employee(nameTextField.getText(), surnameTextField.getText());
        details = new EmployeeDetails("developer", new BigDecimal(1000));
        details.setOccupation(occupationTextField.getText());
        employee.setDetails(details);
    }

}

