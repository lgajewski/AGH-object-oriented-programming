package pl.edu.agh.iet.to2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AppPresenter implements Presenter {

    private Stage primaryStage;
    private Stage currentStage;
    private AppController controller;

    public AppPresenter(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void initRootLayout() throws IOException {
        // load layout from FXML file
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/App.fxml"));

        controller = new AppController(this);
        loader.setController(controller);

        Pane rootLayout = loader.load();

        primaryStage.setScene(new Scene(rootLayout));
        primaryStage.show();
    }

    @Override
    public void showAndWait(String title, Scene scene) {
        // create new stage
        currentStage = new Stage();
        currentStage.setTitle(title);
        currentStage.initModality(Modality.WINDOW_MODAL);
        currentStage.initOwner(primaryStage);

        // set scene for a new stage
        currentStage.setScene(scene);

        currentStage.showAndWait();
    }

    @Override
    public void closeCurrentStage() {
        currentStage.close();
    }

    @Override
    public void setProjectsTabContent(Pane content){
        controller.setTabContent(AppTab.PROJECTS.getName(), content);
    }

}
