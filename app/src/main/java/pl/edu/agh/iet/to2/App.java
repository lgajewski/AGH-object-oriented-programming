package pl.edu.agh.iet.to2;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Company Management System");

        try {
            AppPresenter appPresenter = new AppPresenter(primaryStage);
            appPresenter.initRootLayout();
        } catch (Exception e) {
            // handle all exceptions here and print stack trace to debug more easily
            e.printStackTrace();
        }
    }
}