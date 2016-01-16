package pl.edu.agh.iet.to2.employees.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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
    private TextField occupationField;

    @FXML
    private ImageView submitButton;

    @FXML
    private ImageView historyButton;


    @FXML
    private void initialize() {
        nameField.textProperty().bindBidirectional(employee.getNameProperty());
        surnameField.textProperty().bindBidirectional(employee.getSurnameProperty());
        salaryField.textProperty().bindBidirectional(employee.getSalaryProperty(), new BigDecimalStringConverter());
        occupationField.textProperty().bindBidirectional(employee.getOccupationProperty());

        historyButton.setOnMouseClicked(event -> showHistoryStage());

        submitButton.setOnMouseClicked(event -> presenter.closeCurrentStage());
    }

    private void showHistoryStage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/employees/fxml/EmployeeHistory.fxml"));
            loader.setController(new EmployeeHistoryController(presenter, employee));
            Pane pane = loader.load();

            presenter.showAndWait("Employee History - " + employee, new Scene(pane));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Employee getEmployee() {
        return employee;
    }
}
