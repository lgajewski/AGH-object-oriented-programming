package pl.edu.agh.iet.to2;

import javafx.scene.layout.Pane;

import java.io.IOException;

public interface TabInitializer {

    Pane initialize(Presenter presenter) throws IOException;

}
