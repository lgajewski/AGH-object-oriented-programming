package pl.edu.agh.iet.to2.app;

import javafx.scene.layout.Pane;

import java.io.IOException;

public interface TabInitializer {
    Pane initialize(Presenter presenter, ModuleManager moduleManager) throws IOException;
}
