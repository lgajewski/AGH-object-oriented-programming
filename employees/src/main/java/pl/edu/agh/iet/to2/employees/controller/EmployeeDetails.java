package pl.edu.agh.iet.to2.employees.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import pl.edu.agh.iet.to2.employees.IEmployee;
import pl.edu.agh.iet.to2.employees.model.Employee;

import java.math.BigDecimal;

public class EmployeeDetails {

    private IEmployee employee;

    public EmployeeDetails(IEmployee employee) {
        this.employee = employee;
    }

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

        saveImage.setOnMouseClicked(event -> save());
    }

    private void save() {
        Employee mutableEmployee = (Employee) employee;

        mutableEmployee.setName(nameField.getText());
        mutableEmployee.setSurname(surnameField.getText());
        mutableEmployee.setOccupation(occupationField.getText());
        mutableEmployee.setSalary(new BigDecimal(salaryField.getText()));
    }

}
