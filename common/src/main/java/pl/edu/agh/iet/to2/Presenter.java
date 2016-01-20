package pl.edu.agh.iet.to2;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public interface Presenter {
    void showAndWait(String title, Scene scene);
    void closeCurrentStage();
    void setProjectsTabContent(Pane content);
}
