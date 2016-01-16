package pl.edu.agh.iet.to2.employees.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Pane;
import pl.edu.agh.iet.to2.app.Presenter;
import pl.edu.agh.iet.to2.employees.model.Employee;

import java.io.IOException;

class EmployeeCell extends ListCell<Employee> {

    private final Presenter presenter;

    private final EmployeeTabController controller;

    public EmployeeCell(EmployeeTabController controller, Presenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
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
            loader.setController(new EmployeeCellController(controller, presenter, employee));

            try {
                Pane employeeCell = loader.load();
                setGraphic(employeeCell);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}