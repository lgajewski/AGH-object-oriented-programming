package pl.edu.agh.iet.to2.app;

import javafx.scene.Scene;

public interface Presenter {
    void showAndWait(String title, Scene scene);

    void addOnStopListener(OnStopListener listener);

    void closeCurrentStage();

    interface OnStopListener {
        void handle();
    }
}
