package pl.edu.agh.iet.to2.employees.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import pl.edu.agh.iet.to2.Presenter;
import pl.edu.agh.iet.to2.employees.IEmployee;

public class EmployeeCellController {

    private final IEmployee employee;
    private final Presenter presenter;

    public EmployeeCellController(Presenter presenter, IEmployee employee) {
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
        employeeName.setText(employee.toString());
        employeeOccupation.setText(employee.getOccupation());

        showDetails.setOnMouseClicked(event -> {
            showEmployeeDetails(employee);
        });
    }

    private void showEmployeeDetails(IEmployee employee) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/employees/fxml/EmployeeDetails.fxml"));
            loader.setController(new EmployeeDetailsController(employee));
            AnchorPane pane = loader.load();

            presenter.showAndWait("Employee - " + employee, new Scene(pane));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
