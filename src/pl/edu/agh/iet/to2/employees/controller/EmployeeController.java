package pl.edu.agh.iet.to2.employees.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import pl.edu.agh.iet.to2.employees.Main;
import pl.edu.agh.iet.to2.employees.model.Employee;

import java.io.IOException;

public class EmployeeController {

    @FXML
    public TextField searchField;

    @FXML
    private ListView<String> employeeListView;

    private ObservableList<String> observableList;

    @FXML
    private void initialize() throws IOException {
        observableList = FXCollections.observableArrayList();

        AnchorPane employeeCell = FXMLLoader.load(Main.class.getResource("view/EmployeeCell.fxml"));

        observableList.add("123");

        // cell factory
        employeeListView.setCellFactory(param -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                AnchorPane a = employeeCell;
                if (item != null) {
                    setGraphic(employeeCell);
                }
            }
        });

        employeeListView.setItems(observableList);
    }

    public void handleSearchEvent(Event event) {

    }
}