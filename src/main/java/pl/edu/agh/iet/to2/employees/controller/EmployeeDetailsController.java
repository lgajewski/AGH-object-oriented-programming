package pl.edu.agh.iet.to2.employees.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import pl.edu.agh.iet.to2.employees.model.IEmployee;

public class EmployeeDetailsController {

    private IEmployee employee;

    public EmployeeDetailsController(IEmployee employee) {
        this.employee = employee;
    }

    @FXML
    private ToggleButton editDetails;

    @FXML
    private ImageView avatarImageView;

    @FXML
    private Button changeAvatar;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField salaryField;

    @FXML
    private ImageView salaryImage;

    @FXML
    private TextField occupationField;

    @FXML
    private ImageView occupationImage;

    @FXML
    private void initialize() {
        nameField.setText(employee.getName());
        surnameField.setText(employee.getSurname());

        salaryField.setText(employee.getSalary() + " $");
        occupationField.setText(employee.getOccupation());
    }


    public void handler(ActionEvent actionEvent) {

    }
}
