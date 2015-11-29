package pl.edu.agh.iet.to2.employees;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.edu.agh.iet.to2.employees.controller.AppController;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Employees Modules");

        AppController appController = new AppController(primaryStage);
        appController.initRootLayout();
    }
}
