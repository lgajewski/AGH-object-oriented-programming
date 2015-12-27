package pl.edu.agh.iet.to2;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;

public class AppController {

    private Presenter presenter;

    @FXML
    private TabPane tabPane;

    public AppController(Presenter presenter) {
        this.presenter = presenter;
    }

    @FXML
    private void initialize() throws IOException {
        for (AppTab appTab : AppTab.values()) {
            TabInitializer initializer = appTab.getTabInitializer();

            // update TabPane with a new tab
            Tab tab = new Tab(appTab.getName(), initializer.initialize(presenter));
            tabPane.getTabs().add(tab);
        }

    }
}