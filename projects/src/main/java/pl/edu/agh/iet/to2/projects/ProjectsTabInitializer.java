package pl.edu.agh.iet.to2.projects;

import javafx.scene.layout.Pane;
import pl.edu.agh.iet.to2.app.ModuleManager;
import pl.edu.agh.iet.to2.app.Presenter;
import pl.edu.agh.iet.to2.app.TabInitializer;
import pl.edu.agh.iet.to2.projects.presenter.ProjectPresenter;

import java.io.IOException;

public class ProjectsTabInitializer implements TabInitializer {

    @Override
    public Pane initialize(Presenter presenter, ModuleManager moduleManager) throws IOException {
        ProjectPresenter projectPresenter = new ProjectPresenter(presenter, moduleManager);

        return projectPresenter.initRootLayout();
    }
}
