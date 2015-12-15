package pl.edu.agh.iet.to2.employees.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import pl.edu.agh.iet.to2.employees.model.IEmployee;
import pl.edu.agh.iet.to2.employees.presenter.AppPresenter;

public class EmployeeCellController {

    private final IEmployee employee;
    private final AppPresenter presenter;

    public EmployeeCellController(AppPresenter presenter, IEmployee employee) {
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

    private Button button;

    @FXML
    private void initialize() {
        employeeName.setText(employee.toString());
        employeeOccupation.setText(employee.getOccupation());

        showDetails.setOnMouseClicked(event -> {
            presenter.showEmployeeDetails(employee);
        });
    }


}
