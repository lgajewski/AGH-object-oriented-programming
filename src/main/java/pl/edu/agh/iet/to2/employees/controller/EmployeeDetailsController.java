package pl.edu.agh.iet.to2.employees.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import pl.edu.agh.iet.to2.employees.model.Employee;
import pl.edu.agh.iet.to2.employees.model.IEmployee;

import java.math.BigDecimal;

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
    private ImageView saveImage;

    @FXML
    private void initialize() {
        nameField.setText(employee.getName());
        surnameField.setText(employee.getSurname());

        salaryField.setText(employee.getSalary().toString());
        occupationField.setText(employee.getOccupation());

        // set listeners
        editDetails.setOnMouseClicked(event -> updateFieldsAvailability());

        saveImage.setOnMouseClicked(event -> save());

        updateFieldsAvailability();
    }

    private void save() {
        Employee mutableEmployee = (Employee) employee;

        mutableEmployee.setName(nameField.getText());
        mutableEmployee.setSurname(surnameField.getText());
        mutableEmployee.setOccupation(occupationField.getText());
        mutableEmployee.setSalary(new BigDecimal(salaryField.getText()));
    }

    private void updateFieldsAvailability() {
        if (editDetails.isSelected()) {
            nameField.setEditable(true);
            surnameField.setEditable(true);
            salaryField.setEditable(true);
            occupationField.setEditable(true);
        } else {
            nameField.setEditable(false);
            surnameField.setEditable(false);
            salaryField.setEditable(false);
            occupationField.setEditable(false);
        }
    }

}
