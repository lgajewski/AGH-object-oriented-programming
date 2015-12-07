package pl.edu.agh.iet.to2.employees.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pl.edu.agh.iet.to2.Main;

import java.io.IOException;

public class AppController {

    private Stage primaryStage;

    public AppController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void initRootLayout() throws IOException {
        // load layout from FXML file
        AnchorPane rootLayout = FXMLLoader.load(Main.class.getResource("employees/view/AppOverviewPane.fxml"));

        primaryStage.setScene(new Scene(rootLayout));
        primaryStage.show();
    }

}
