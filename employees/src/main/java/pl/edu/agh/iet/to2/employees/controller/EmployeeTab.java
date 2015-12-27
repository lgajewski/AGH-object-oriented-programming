package pl.edu.agh.iet.to2.employees.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import pl.edu.agh.iet.to2.Presenter;
import pl.edu.agh.iet.to2.employees.IEmployee;
import pl.edu.agh.iet.to2.employees.model.EmployeeGenerator;

import java.io.IOException;
import java.util.Comparator;
import java.util.function.Predicate;

public class EmployeeTab {

    private static final int AMOUNT_OF_EMPLOYEES = 1;
    private Presenter presenter;
    private ObservableList<IEmployee> employeeList;

    public EmployeeTab(Presenter presenter) {
        this.presenter = presenter;
    }

    @FXML
    private ListView<IEmployee> employeeListView;

    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<String> filterComboBox;

    @FXML
    private Button addButton;

    @FXML
    private void initialize() {
        // fill list with custom data
        EmployeeGenerator generator = new EmployeeGenerator();
        employeeList = FXCollections.observableArrayList();
        employeeList.addAll(generator.generate(AMOUNT_OF_EMPLOYEES));

        // set custom cell factory and bind to employeeList
        employeeListView.setItems(employeeList);
        employeeListView.setCellFactory(param -> new EmployeeCell());

        // register listeners
        filterComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            handleSortEvent(newValue);
        });
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            handleSearchEvent(newValue);
        });
        addButton.setOnMouseClicked(event -> showEmployeeAddDialog());
    }

    private void showEmployeeAddDialog() {
        try {
            // Load the fxml file and create a new stage for the dialog
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/employees/fxml/EmployeeDetails.fxml"));
            Pane pane = loader.load();

            // Create the dialog Stage.
            presenter.showAndWait("Add employee", new Scene(pane));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            FilteredList<IEmployee> filteredList = employeeList.filtered(new EmployeePredicate(pattern));

            // update listView to filtered items
            employeeListView.setItems(filteredList);
        }
    }


    class EmployeePredicate implements Predicate<IEmployee> {

        private String pattern;

        public EmployeePredicate(String pattern) {
            this.pattern = pattern.toLowerCase();
        }

        @Override
        public boolean test(IEmployee iEmployee) {
            return testOccupation(iEmployee.getOccupation()) || testName(iEmployee.getName(), iEmployee.getSurname());
        }

        private boolean testOccupation(String occupation) {
            return occupation.toLowerCase().contains(pattern);
        }

        private boolean testName(String name, String surname) {
            return (name + " " + surname).toLowerCase().contains(pattern);
        }
    }

    // TODO create bindings
    class EmployeeCell extends ListCell<IEmployee> {
        @Override
        protected void updateItem(IEmployee item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setGraphic(null);
            } else {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/employees/fxml/EmployeeCell.fxml"));

                // update employee details for controller
                loader.setController(new pl.edu.agh.iet.to2.employees.controller.EmployeeCell(presenter, item));

                try {
                    Pane employeeCell = loader.load();
                    setGraphic(employeeCell);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
