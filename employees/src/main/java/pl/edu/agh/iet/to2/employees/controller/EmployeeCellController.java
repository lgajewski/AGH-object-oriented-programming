package pl.edu.agh.iet.to2.employees.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import pl.edu.agh.iet.to2.Presenter;
import pl.edu.agh.iet.to2.employees.model.Employee;
import pl.edu.agh.iet.to2.employees.persistence.EmployeeDao;
import pl.edu.agh.iet.to2.employees.persistence.EmployeeUpdater;

public class EmployeeCellController {

    private final Employee employee;
    private final Presenter presenter;
    private final EmployeeTabController controller;

    public EmployeeCellController(EmployeeTabController controller, Presenter presenter, Employee employee) {
        this.controller = controller;
        this.presenter = presenter;
        this.employee = employee;
    }

    @FXML
    private Label employeeName;

    @FXML
    private Label employeeSurname;

    @FXML
    private Label employeeOccupation;

    @FXML
    private CheckBox employeeCheckBox;

    @FXML
    private ImageView showDetails;

    @FXML
    private ImageView delete;

    @FXML
    private void initialize() {
        employeeName.textProperty().bind(employee.getNameProperty());
        employeeSurname.textProperty().bind(employee.getSurnameProperty());
        employeeOccupation.textProperty().bind(employee.getOccupationProperty());

        showDetails.setOnMouseClicked(event -> showEmployeeDetails(employee));

        delete.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                EmployeeDao.deleteEmployee(employee);
                EmployeeUpdater.update();
                controller.updateEmployeeList();
            }
        });
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
