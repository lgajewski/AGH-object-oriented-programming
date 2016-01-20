package pl.edu.agh.iet.to2.app;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public interface Presenter {
    void showAndWait(String title, Scene scene);

    void addOnStopListener(OnStopListener listener);

    void closeCurrentStage();

    void setProjectsTabContent(Pane content);

    interface OnStopListener {
        void handle();
    }
}
