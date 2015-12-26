package pl.edu.agh.iet.to2;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;
import java.util.Map;

public class AppOverviewController {

    private Presenter presenter;

    @FXML
    private TabPane tabPane;

    private final Map<String, TabInitializer> initializerMap;

    public AppOverviewController(Presenter presenter, Map<String, TabInitializer> initializerMap) {
        this.presenter = presenter;
        this.initializerMap = initializerMap;
    }

    @FXML
    private void initialize() throws IOException {
        for (Map.Entry<String, TabInitializer> entry : initializerMap.entrySet()) {
            String name = entry.getKey();
            TabInitializer initializer = entry.getValue();

            // update TabPane with a new tab
            Tab tab = new Tab(name, initializer.initialize(presenter));
            tabPane.getTabs().add(tab);
        }

    }
}