package pl.edu.agh.iet.to2.employees.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.converter.BigDecimalStringConverter;
import pl.edu.agh.iet.to2.app.Presenter;
import pl.edu.agh.iet.to2.employees.model.Employee;

public class EmployeeDetailsController {

    private final Employee employee;
    private final Presenter presenter;

    public EmployeeDetailsController(Presenter presenter) {
        this(presenter, new Employee());
    }

    public EmployeeDetailsController(Presenter presenter, Employee employee) {
        this.presenter = presenter;
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
    private ImageView submitButton;

    @FXML
    private void initialize() {
        nameField.textProperty().bindBidirectional(employee.getNameProperty());
        surnameField.textProperty().bindBidirectional(employee.getSurnameProperty());
        salaryField.textProperty().bindBidirectional(employee.getSalaryProperty(), new BigDecimalStringConverter());
        occupationField.textProperty().bindBidirectional(employee.getOccupationProperty());

        submitButton.setOnMouseClicked(event -> presenter.closeCurrentStage() );
    }

    public Employee getEmployee() {
        return employee;
    }
}
