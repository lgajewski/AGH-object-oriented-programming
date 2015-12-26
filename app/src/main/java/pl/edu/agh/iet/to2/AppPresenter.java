package pl.edu.agh.iet.to2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.edu.agh.iet.to2.employees.view.EmployeeTab;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AppPresenter implements Presenter {

    private Stage primaryStage;
    private final Map<String, TabInitializer> tabInitializerMap;

    public AppPresenter(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.tabInitializerMap = createTabInitializerMap();

    }

    private Map<String, TabInitializer> createTabInitializerMap() {
        Map<String, TabInitializer> map = new HashMap<>();

        map.put("Employees", new EmployeeTab());

        return map;
    }

    public void initRootLayout() throws IOException {
        // load layout from FXML file
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/AppOverview.fxml"));

        loader.setController(new AppOverviewController(this, tabInitializerMap));

        AnchorPane rootLayout = loader.load();

        primaryStage.setScene(new Scene(rootLayout));
        primaryStage.show();
    }

    public void showAndWait(String title, Scene scene) {
        // create new stage
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(primaryStage);

        // set scene for a new stage
        stage.setScene(scene);

        stage.showAndWait();
    }

}
