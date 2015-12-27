package pl.edu.agh.iet.to2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AppPresenter implements Presenter {

    private Stage primaryStage;

    public AppPresenter(Stage primaryStage) {
        this.primaryStage = primaryStage;

    }

    public void initRootLayout() throws IOException {
        // load layout from FXML file
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/App.fxml"));

        loader.setController(new AppController(this));

        Pane rootLayout = loader.load();

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
