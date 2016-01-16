package pl.edu.agh.iet.to2.employees.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Pane;
import pl.edu.agh.iet.to2.app.Presenter;
import pl.edu.agh.iet.to2.employees.model.Employee;
import pl.edu.agh.iet.to2.employees.persistence.EmployeeDao;

import java.io.IOException;

class EmployeeCell extends ListCell<Employee> {

    private final Presenter presenter;

    private EmployeeDao employeeDao;

    public EmployeeCell(Presenter presenter, EmployeeDao employeeDao) {
        this.presenter = presenter;
        this.employeeDao = employeeDao;
    }

    @Override
    protected void updateItem(Employee employee, boolean empty) {
        super.updateItem(employee, empty);

        if (empty) {
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/employees/fxml/EmployeeCell.fxml"));

            // update employee details for controller
            loader.setController(new EmployeeCellController(presenter, employee, employeeDao));

            try {
                Pane employeeCell = loader.load();
                setGraphic(employeeCell);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}