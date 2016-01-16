package pl.edu.agh.iet.to2;

import javafx.scene.Scene;

public interface Presenter {
    void showAndWait(String title, Scene scene);

    void addOnStopListener(OnStopListener listener);

    interface OnStopListener {
        void handle();
    }
}
