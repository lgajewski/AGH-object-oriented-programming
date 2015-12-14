package pl.edu.agh.iet.to2.employees.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import pl.edu.agh.iet.to2.Main;

import java.io.IOException;

public class AppOverviewController {

    @FXML
    private AnchorPane tabEmployees;

    @FXML
    private void initialize() throws IOException {
        tabEmployees.getChildren().add(FXMLLoader.load(Main.class.getResource("employees/view/EmployeePane.fxml")));
    }

}
