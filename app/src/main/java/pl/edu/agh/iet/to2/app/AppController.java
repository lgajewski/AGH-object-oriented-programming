package pl.edu.agh.iet.to2.app;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class AppController {

    private ModuleManager moduleManager;
    private Presenter presenter;

    @FXML
    private TabPane tabPane;

    public AppController(Presenter presenter, ModuleManager moduleManager) {
        this.presenter = presenter;
        this.moduleManager = moduleManager;
    }

    @FXML
    private void initialize() throws IOException {
        for (AppTab appTab : AppTab.values()) {
            try {
                TabInitializer initializer = appTab.getTabInitializer();

                // update TabPane with a new tab
                Tab tab = new Tab(appTab.getName(), initializer.initialize(presenter, moduleManager));
                tabPane.getTabs().add(tab);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setTabContent(AppTab appTab, Pane content) {
        ObservableList<Tab> tabs = tabPane.getTabs();
        Tab tab = new Tab(appTab.getName(), content);
        tabs.stream().filter(t -> t.getText().equals(appTab.getName()))
                .forEach(t -> tabs.set(tabs.indexOf(t), tab));
        tabPane.getSelectionModel().select(tab);
    }
}