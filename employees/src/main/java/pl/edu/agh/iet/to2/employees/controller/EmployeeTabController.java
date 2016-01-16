package pl.edu.agh.iet.to2.employees.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import pl.edu.agh.iet.to2.app.ModuleManager;
import pl.edu.agh.iet.to2.app.Presenter;
import pl.edu.agh.iet.to2.employees.IEmployee;
import pl.edu.agh.iet.to2.employees.model.Employee;

import java.io.IOException;
import java.util.Comparator;

public class EmployeeTabController {

    private ModuleManager moduleManager;
    private Presenter presenter;
    private ObservableList<Employee> employeeList;


    public EmployeeTabController(Presenter presenter, ModuleManager moduleManager) {
        this.presenter = presenter;
        this.moduleManager = moduleManager;
    }

    @FXML
    private ListView<Employee> employeeListView;

    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<String> filterComboBox;

    @FXML
    private Button addButton;

    @FXML
    private void initialize() {
        // create and update employee list
        employeeList = FXCollections.observableArrayList();
        updateEmployeeList();

        // set custom cell factory and bind to employeeList
        employeeListView.setItems(employeeList);
        employeeListView.setCellFactory(param -> new EmployeeCell(this, presenter));

        // register listeners
        filterComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            handleSortEvent(newValue);
        });
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            handleSearchEvent(newValue);
        });
        addButton.setOnMouseClicked(event -> {
            try {
                Employee employee = showEmployeeAddDialog();

                // persist
                persistAndUpdate(employee);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void persistAndUpdate(Employee employee) {
//        EmployeeDao.saveEmployee(employee);
//        EmployeeUpdater.update();

        updateEmployeeList();
    }

    public void updateEmployeeList() {
        employeeList.clear();
//        employeeList.addAll(EmployeeDao.getEmployees());
    }

    private Employee showEmployeeAddDialog() throws IOException {
        // Load the fxml file and create a new stage for the dialog
        EmployeeDetailsController controller = new EmployeeDetailsController();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/employees/fxml/EmployeeDetails.fxml"));
        loader.setController(controller);

        Pane pane = loader.load();

        // Create the dialog Stage.
        presenter.showAndWait("Add employee", new Scene(pane));

        return controller.getEmployee();
    }

    private void handleSortEvent(String newValue) {
        System.out.println("Selected: " + newValue);
        Comparator<IEmployee> comparator = getComparatorForField(newValue);
        employeeList.sort(comparator);
    }

    private Comparator<IEmployee> getComparatorForField(String field) {
        switch (field) {
            case "Name":
                return (o1, o2) -> o1.getName().compareTo(o2.getName());
            case "Surname":
                return (o1, o2) -> o1.getSurname().compareTo(o2.getSurname());
            case "Occupation":
                return (o1, o2) -> o1.getOccupation().compareTo(o2.getOccupation());
            default:
                return ((o1, o2) -> 0);
        }
    }

    private void handleSearchEvent(String pattern) {
        if ("".equals(pattern)) {
            // search field is empty
            employeeListView.setItems(employeeList);
        } else {
            // filter entry employee list
            FilteredList<Employee> filteredList = employeeList.filtered(new EmployeePredicate(pattern));

            // update listView to filtered items
            employeeListView.setItems(filteredList);
        }
    }


}
