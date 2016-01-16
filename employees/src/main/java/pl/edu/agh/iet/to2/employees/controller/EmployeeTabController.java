package pl.edu.agh.iet.to2.employees.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import pl.edu.agh.iet.to2.app.ModuleManager;
import pl.edu.agh.iet.to2.app.Presenter;
import pl.edu.agh.iet.to2.employees.EmployeesModule;
import pl.edu.agh.iet.to2.employees.IEmployee;
import pl.edu.agh.iet.to2.employees.model.Employee;
import pl.edu.agh.iet.to2.employees.persistence.EmployeeDao;

import java.io.IOException;
import java.util.Comparator;
import java.util.stream.Collectors;

public class EmployeeTabController {

    private EmployeeDao employeeDao;
    private Presenter presenter;
    private ObservableList<Employee> employeeList;


    public EmployeeTabController(Presenter presenter, ModuleManager moduleManager) {
        this.presenter = presenter;

        EmployeesModule employeesModule = (EmployeesModule) moduleManager.getEmployeesModule();
        this.employeeDao = employeesModule.getEmployeeDao();
    }

    @FXML
    private ListView<Employee> employeeListView;

    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<String> filterComboBox;

    @FXML
    private ImageView addButton;

    @FXML
    private void initialize() {
        // create and update employee list
        employeeList = FXCollections.observableArrayList();
        employeeList.addAll(employeeDao.getEmployees());

        // set custom cell factory and bind to employeeList
        employeeListView.setItems(employeeList);
        employeeListView.setCellFactory(param -> new EmployeeCell(presenter, employeeDao));

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

                // save and update employee list
                employeeDao.addEmployee(employee);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // handle add employee event
        employeeDao.addOnEmployeeAddedListener(employee -> employeeList.add((Employee) employee));

        // handle update event
        employeeDao.addOnEmployeeUpdatedListener((oldEmployee, newEmployee) -> {
            Employee employee = (Employee) newEmployee;
            employee.update();
        });

        // handle remove employee event
        employeeDao.addOnEmployeeDeletedListener(iEmployee -> employeeList.removeAll(
                employeeList.stream()
                        .filter(employee -> employee.getId() == iEmployee.getId())
                        .collect(Collectors.toList())));

    }

    private Employee showEmployeeAddDialog() throws IOException {
        // Load the fxml file and create a new stage for the dialog
        EmployeeDetailsController controller = new EmployeeDetailsController(presenter);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/employees/fxml/EmployeeDetails.fxml"));
        loader.setController(controller);

        Pane pane = loader.load();

        // Create the dialog Stage.
        presenter.showAndWait("Add employee", new Scene(pane));

        return controller.getEmployee();
    }

    private void handleSortEvent(String newValue) {
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
