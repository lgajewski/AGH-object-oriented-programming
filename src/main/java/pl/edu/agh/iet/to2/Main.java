package pl.edu.agh.iet.to2;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.edu.agh.iet.to2.employees.presenter.AppPresenter;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Employees Modules");

        try {
            AppPresenter appPresenter = new AppPresenter(primaryStage);
            appPresenter.initRootLayout();
        } catch (Exception e) {
            // handle all exceptions here and print a trace to debug more easily
            e.printStackTrace();
        }
    }
}
