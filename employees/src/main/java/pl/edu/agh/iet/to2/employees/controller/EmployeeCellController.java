package pl.edu.agh.iet.to2.employees.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import pl.edu.agh.iet.to2.app.Presenter;
import pl.edu.agh.iet.to2.employees.model.Employee;
import pl.edu.agh.iet.to2.employees.persistence.EmployeeDao;

public class EmployeeCellController {

    private final Employee employee;
    private final Presenter presenter;
    private EmployeeDao employeeDao;

    public EmployeeCellController(Presenter presenter, Employee employee, EmployeeDao employeeDao) {
        this.presenter = presenter;
        this.employee = employee;
        this.employeeDao = employeeDao;
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
    private ImageView employeeAvatar;

    @FXML
    private void initialize() {
        employeeName.textProperty().bind(employee.getNameProperty());
        employeeSurname.textProperty().bind(employee.getSurnameProperty());
        employeeOccupation.textProperty().bind(employee.getOccupationProperty());

        showDetails.setOnMouseClicked(event -> showEmployeeDetails(employee));

        delete.setOnMouseClicked(event -> employeeDao.deleteEmployee(employee.getId()));

        // set employee avatar listener
        updateAvatar(employee.getAvatarName());
        employee.getAvatarPathProperty().addListener((observable, oldValue, newValue) -> {
            updateAvatar(newValue);
        });
    }

    private void showEmployeeDetails(Employee employee) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/employees/fxml/EmployeeDetails.fxml"));
            loader.setController(new EmployeeDetailsController(presenter, employee));
            Pane pane = loader.load();

            presenter.showAndWait("Employee - " + employee, new Scene(pane));

            // update employee
            employeeDao.updateEmployee(employee.getId(), employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateAvatar(String avatarPath) {
        Image image = new Image(getClass().getResourceAsStream(EmployeeCell.INITIAL_DIRECTORY + avatarPath));
        employeeAvatar.setImage(image);
    }


}
