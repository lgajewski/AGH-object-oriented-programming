package pl.edu.agh.iet.to2.teams;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import pl.edu.agh.iet.to2.app.ModuleManager;
import pl.edu.agh.iet.to2.app.Presenter;
import pl.edu.agh.iet.to2.app.TabInitializer;

import java.io.IOException;

public class TeamsTabInitializer implements TabInitializer {
    @Override
    public Pane initialize(Presenter presenter, ModuleManager moduleManager) throws IOException {
        AnchorPane pane = new AnchorPane();
        pane.getChildren().add(new Label("Put layout initialization here"));
        return pane;
    }
}
