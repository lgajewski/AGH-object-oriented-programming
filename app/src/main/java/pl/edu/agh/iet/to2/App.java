package pl.edu.agh.iet.to2;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.edu.agh.iet.to2.employees.persistence.HibernateUtils;

public class App extends Application {

    private AppPresenter appPresenter;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Company Management System");

        try {
            appPresenter = new AppPresenter(primaryStage);
            appPresenter.initRootLayout();
        } catch (Exception e) {
            // handle all exceptions here and print stack trace to debug more easily
            e.printStackTrace();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();

        appPresenter.stop();
        HibernateUtils.shutdown();
    }
}
