package pl.edu.agh.iet.to2.projects.view;

import javafx.scene.layout.Pane;
import pl.edu.agh.iet.to2.ModuleManager;
import pl.edu.agh.iet.to2.Presenter;
import pl.edu.agh.iet.to2.TabInitializer;
import pl.edu.agh.iet.to2.projects.presenter.ProjectPresenter;

import java.io.IOException;

public class ProjectsTabInitializer implements TabInitializer {
    @Override
    public Pane initialize(Presenter presenter) throws IOException {
        ProjectPresenter projectPresenter = new ProjectPresenter(presenter, ModuleManager.getEmployeesModule(), ModuleManager.getTeamsModule());

        return projectPresenter.initRootLayout();
    }
}
