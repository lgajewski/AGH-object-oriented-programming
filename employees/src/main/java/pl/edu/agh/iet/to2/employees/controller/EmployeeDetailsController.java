package pl.edu.agh.iet.to2.employees.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.converter.BigDecimalStringConverter;
import pl.edu.agh.iet.to2.employees.model.Employee;

public class EmployeeDetailsController {

    private Employee employee;

    public EmployeeDetailsController() {
    }

    public EmployeeDetailsController(Employee employee) {
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
    private void initialize() {
        if (employee == null) {
            employee = new Employee();
        }

        nameField.textProperty().bindBidirectional(employee.getNameProperty());
        surnameField.textProperty().bindBidirectional(employee.getSurnameProperty());
        salaryField.textProperty().bindBidirectional(employee.getSalaryProperty(), new BigDecimalStringConverter());
        occupationField.textProperty().bindBidirectional(employee.getOccupationProperty());
    }

    public Employee getEmployee() {
        return employee;
    }
}
