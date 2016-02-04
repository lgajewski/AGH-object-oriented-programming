package pl.edu.agh.iet.to2.app;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

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


      //  for (AppTab appTab : AppTab.values()) {
        AppTab appTab = AppTab.TEAMS;
            try {
                TabInitializer initializer = appTab.getTabInitializer();

                // update TabPane with a new tab
                Tab tab = new Tab(appTab.getName(), initializer.initialize(presenter, moduleManager));
                tabPane.getTabs().add(tab);
            } catch (Exception e) {
                e.printStackTrace();
            }
     //   }
    }
}