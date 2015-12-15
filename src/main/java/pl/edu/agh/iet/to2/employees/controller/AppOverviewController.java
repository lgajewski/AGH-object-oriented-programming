package pl.edu.agh.iet.to2.employees.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import pl.edu.agh.iet.to2.employees.controller.generator.EmployeeGenerator;
import pl.edu.agh.iet.to2.employees.model.IEmployee;
import pl.edu.agh.iet.to2.employees.presenter.AppPresenter;

import java.io.IOException;
import java.util.Comparator;
import java.util.function.Predicate;

public class AppOverviewController {

    private static final int AMOUNT_OF_EMPLOYEES = 5;

    private final EmployeeGenerator generator;
    private final AppPresenter presenter;

    private ObservableList<IEmployee> employeeList;

    @FXML
    private ListView<IEmployee> employeeListView;

    @FXML
    public TextField searchField;

    @FXML
    public ComboBox<String> filterComboBox;

    public AppOverviewController(AppPresenter appPresenter, EmployeeGenerator generator) {
        this.presenter = appPresenter;
        this.generator = generator;
    }

    @FXML
    private void initialize() {
        // fill list with custom data
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


    class EmployeeCell extends ListCell<IEmployee> {
        @Override
        protected void updateItem(IEmployee item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setGraphic(null);
            } else {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/employees/view/EmployeeCell.fxml"));

                // update employee details for controller
                loader.setController(new EmployeeCellController(presenter, item));

                try {
                    AnchorPane employeeCell = loader.load();
                    setGraphic(employeeCell);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}