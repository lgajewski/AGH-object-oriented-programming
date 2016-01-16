package pl.edu.agh.iet.to2.app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AppPresenter implements Presenter {

    private ModuleManager moduleManager;

    private List<OnStopListener> onStopListeners;

    private Stage primaryStage;

    private Stack<Stage> stageStack;

    public AppPresenter(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.moduleManager = new AppModuleManager();
        this.onStopListeners = new ArrayList<>();

        // STAGE STACK
        this.stageStack = new Stack<>();
        this.stageStack.push(primaryStage);
    }

    public void initRootLayout() throws IOException {
        // load layout from FXML file
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/App.fxml"));
        loader.setController(new AppController(this, moduleManager));

        Pane rootLayout = loader.load();

        primaryStage.setScene(new Scene(rootLayout));
        primaryStage.show();
    }

    @Override
    public void showAndWait(String title, Scene scene) {
        // create new stage
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(primaryStage);

        // set scene for a new stage
        stage.setScene(scene);

        // push to stack
        stageStack.push(stage);

        stage.showAndWait();
    }

    @Override
    public void addOnStopListener(OnStopListener listener) {
        this.onStopListeners.add(listener);
    }

    @Override
    public void closeCurrentStage() {
        stageStack.pop().close();
    }

    public void stop() {
        onStopListeners.forEach(Presenter.OnStopListener::handle);
    }

}
