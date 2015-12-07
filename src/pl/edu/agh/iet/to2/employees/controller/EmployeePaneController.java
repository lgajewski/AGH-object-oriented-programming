package pl.edu.agh.iet.to2.employees.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import pl.edu.agh.iet.to2.Main;
import pl.edu.agh.iet.to2.employees.controller.generator.EmployeeGenerator;
import pl.edu.agh.iet.to2.employees.model.EmployeeDetails;
import pl.edu.agh.iet.to2.employees.model.IEmployee;

import java.io.IOException;
import java.util.List;

public class EmployeePaneController {

    private static final int AMOUNT_OF_EMPLOYEES = 5;

    @FXML
    public TextField searchField;

    @FXML
    private ListView<EmployeeItem> employeeListView;

    private EmployeeGenerator employeeGenerator = new EmployeeGenerator();

    @FXML
    private void initialize() throws IOException {
        ObservableList<EmployeeItem> observableList = createObservableEmployeeItemList(AMOUNT_OF_EMPLOYEES);

        employeeListView.setCellFactory(new Callback<ListView<EmployeeItem>, ListCell<EmployeeItem>>() {
            @Override
            public ListCell<EmployeeItem> call(ListView<EmployeeItem> param) {
                return new ListCell<EmployeeItem>() {
                    @Override
                    protected void updateItem(EmployeeItem item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item != null) {
                            setGraphic(item.getPane());
                        }
                    }
                };
            }
        });

        employeeListView.setItems(observableList);
    }

    private ObservableList<EmployeeItem> createObservableEmployeeItemList(int amount) throws IOException {
        ObservableList<EmployeeItem> observableList = FXCollections.observableArrayList();
        List<IEmployee> employeeList = employeeGenerator.generate(amount);

        for (IEmployee employee : employeeList) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("employees/view/EmployeeCell.fxml"));
            AnchorPane employeeCell = loader.load();

            EmployeeCellController controller = loader.getController();
            observableList.add(new EmployeeItem(employee, employeeCell, controller));
        }
        return observableList;
    }

    public void handleSearchEvent(Event event) {

    }

    class EmployeeItem {
        private IEmployee employee;
        private AnchorPane pane;
        private EmployeeCellController controller;

        public EmployeeItem(IEmployee employee, AnchorPane pane, EmployeeCellController controller) {
            this.employee = employee;
            this.pane = pane;
            this.controller = controller;

            updateDetails();
        }

        private void updateDetails() {
            controller.setEmployeeName(employee.toString());

            EmployeeDetails details = employee.getDetails();

            if(details != null) {
                controller.setEmployeeOccupation(details.getOccupation().getName());
            }
        }

        public AnchorPane getPane() {
            return pane;
        }

        @Override
        public String toString() {
            return employee.toString();
        }
    }
}