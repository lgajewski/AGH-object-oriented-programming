package teams.view;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import pl.edu.agh.iet.to2.Presenter;
import pl.edu.agh.iet.to2.TabInitializer;

import java.io.IOException;

public class TeamsTabInitializer implements TabInitializer {
    @Override
    public Pane initialize(Presenter presenter) throws IOException {
        AnchorPane pane = new AnchorPane();
        pane.getChildren().add(new Label("Put layout initialization here"));
        return pane;
    }
}
