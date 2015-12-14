package pl.edu.agh.iet.to2.employees.presenter;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pl.edu.agh.iet.to2.employees.controller.AppOverviewController;
import pl.edu.agh.iet.to2.employees.controller.generator.EmployeeGenerator;

import java.io.IOException;

public class AppPresenter {

    private EmployeeGenerator generator;
    private Stage primaryStage;

    public AppPresenter(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.generator = new EmployeeGenerator();
    }

    public void initRootLayout() throws IOException {
        // load layout from FXML file
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/employees/view/AppOverview.fxml"));

        loader.setController(new AppOverviewController(generator));

        AnchorPane rootLayout = loader.load();

        // get controller from loaded layout
        AppOverviewController controller = loader.getController();

        primaryStage.setScene(new Scene(rootLayout));
        primaryStage.show();
    }

}
