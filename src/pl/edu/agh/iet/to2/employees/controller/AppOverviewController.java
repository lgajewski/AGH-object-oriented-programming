package pl.edu.agh.iet.to2.employees.controller;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pl.edu.agh.iet.to2.employees.Main;
import sun.plugin.javascript.navig.Anchor;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class AppOverviewController {

    @FXML
    private AnchorPane tabEmployees;

    @FXML
    private void initialize() throws IOException {
        tabEmployees.getChildren().add(FXMLLoader.load(Main.class.getResource("view/EmployeePane.fxml")));
    }

}
