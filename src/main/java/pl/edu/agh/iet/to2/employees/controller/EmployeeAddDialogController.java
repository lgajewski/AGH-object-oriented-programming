package pl.edu.agh.iet.to2.employees.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.edu.agh.iet.to2.employees.model.Employee;
import pl.edu.agh.iet.to2.employees.model.EmployeeDetails;

/**
 * Created by Patrycja on 15.12.2015.
 */
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

    private Stage dialogStage;

    private boolean approved;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isApproved() {
        return approved;
    }

    @FXML
    private void handleOkEvent(ActionEvent event) {
        if (isInputValid()) {
            updateModel();
            approved = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancelEvent(ActionEvent event) {
        dialogStage.close();
    }

    private boolean isInputValid() {
        // TODO: implement

        return true;
    }

    private void updateModel() {
//        employee.setName(nameTextField.getText());
//        employee.setSurname(surnameTextField.getText());
        employee = new Employee(nameTextField.getText(), surnameTextField.getText());
        details = new EmployeeDetails();
        details.setOccupation(occupationTextField.getText());
        employee.setDetails(details);
    }

}

