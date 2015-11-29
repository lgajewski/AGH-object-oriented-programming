package pl.edu.agh.iet.to2.employees.controller;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pl.edu.agh.iet.to2.employees.Main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class AppController {

    private Stage primaryStage;

    public AppController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void initRootLayout() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/AppOverviewPane.fxml"));

        AnchorPane rootLayout = loader.load();

        // add layout to a scene and show them all
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
