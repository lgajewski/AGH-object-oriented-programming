package pl.edu.agh.iet.to2.projects.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import pl.edu.agh.iet.to2.Presenter;
import pl.edu.agh.iet.to2.TabInitializer;
import pl.edu.agh.iet.to2.projects.controller.ProjectOverviewController;

import java.io.IOException;

public class ProjectsTabInitializer implements TabInitializer {
    @Override
    public Pane initialize(Presenter presenter) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ProjectOverview.fxml"));
        loader.setController(new ProjectOverviewController(presenter));

        return loader.load();
    }
}
