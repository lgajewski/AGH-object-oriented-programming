package pl.edu.agh.iet.to2.employees.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import pl.edu.agh.iet.to2.employees.controller.generator.EmployeeGenerator;
import pl.edu.agh.iet.to2.employees.model.IEmployee;

import java.io.IOException;

public class AppOverviewController {

    private EmployeeGenerator generator;

    @FXML
    private ListView<IEmployee> employeeListView;

    @FXML
    public TextField searchField;

    @FXML
    public ComboBox filterComboBox;

    @FXML
    private void initialize() {
        generator = new EmployeeGenerator();

        employeeListView.setCellFactory(new Callback<ListView<IEmployee>, ListCell<IEmployee>>() {
            @Override
            public ListCell<IEmployee> call(ListView<IEmployee> param) {
                return new ListCell<IEmployee>() {
                    @Override
                    protected void updateItem(IEmployee item, boolean empty) {
                        super.updateItem(item, empty);

                        if (!empty) {
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/fxml/employees/view/EmployeeCell.fxml"));

                            // update employee details for controller
                            loader.setController(new EmployeeCellController(item));


                            try {
                                AnchorPane employeeCell = loader.load();

                                setGraphic(employeeCell);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
            }
        });
    }

    public void handleSearchEvent(Event event) {
        IEmployee employee = generator.generate(1).get(0);
        employeeListView.getItems().add(employee);
    }
}