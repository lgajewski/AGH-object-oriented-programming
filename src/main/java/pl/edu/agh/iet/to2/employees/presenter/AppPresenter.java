package pl.edu.agh.iet.to2.employees.presenter;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pl.edu.agh.iet.to2.employees.controller.AppOverviewController;

import java.io.IOException;

public class AppPresenter {

    private Stage primaryStage;

    public AppPresenter(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void initRootLayout() throws IOException {
        // load layout from FXML file
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/employees/view/AppOverview.fxml"));

        AnchorPane rootLayout = loader.load();

        // get controller from loaded layout
        AppOverviewController controller = loader.getController();

        primaryStage.setScene(new Scene(rootLayout));
        primaryStage.show();
    }

}
