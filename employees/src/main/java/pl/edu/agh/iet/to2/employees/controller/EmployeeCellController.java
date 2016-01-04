package pl.edu.agh.iet.to2.employees.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import pl.edu.agh.iet.to2.Presenter;
import pl.edu.agh.iet.to2.employees.IEmployee;
import pl.edu.agh.iet.to2.employees.model.Employee;

public class EmployeeCellController {

    private final Employee employee;
    private final Presenter presenter;

    public EmployeeCellController(Presenter presenter, Employee employee) {
        this.presenter = presenter;
        this.employee = employee;
    }

    @FXML
    private Label employeeName;

    @FXML
    private Label employeeOccupation;

    @FXML
    private CheckBox employeeCheckBox;

    @FXML
    private ImageView showDetails;

    @FXML
    private void initialize() {
        employeeName.textProperty().bind(employee.getNameProperty());
        employeeOccupation.textProperty().bind(employee.getOccupationProperty());

        showDetails.setOnMouseClicked(event -> showEmployeeDetails(employee));
    }

    private void showEmployeeDetails(Employee employee) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/employees/fxml/EmployeeDetails.fxml"));
            loader.setController(new EmployeeDetailsController(employee));
            Pane pane = loader.load();

            presenter.showAndWait("Employee - " + employee, new Scene(pane));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
