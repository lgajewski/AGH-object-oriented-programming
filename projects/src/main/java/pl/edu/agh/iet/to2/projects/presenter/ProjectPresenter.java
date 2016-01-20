package pl.edu.agh.iet.to2.projects.presenter;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import pl.edu.agh.iet.to2.app.ModuleManager;
import pl.edu.agh.iet.to2.app.Presenter;
import pl.edu.agh.iet.to2.projects.controller.*;
import pl.edu.agh.iet.to2.projects.model.Project;
import pl.edu.agh.iet.to2.projects.persistence.ProjectDao;
import pl.edu.agh.iet.to2.teams.ITeamsModule;

import java.io.IOException;

public class ProjectPresenter {

    private final ModuleManager moduleManager;
    private final Presenter presenter;

    public ProjectPresenter(Presenter presenter, ModuleManager moduleManager) {
        this.presenter = presenter;
        this.moduleManager = moduleManager;
    }

    public Pane initRootLayout() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ProjectOverview.fxml"));
        loader.setController(new ProjectOverviewController(this));

        // TODO YOU NEED TO INITIALIZE ALL LAYOUT HERE!
        // READY COMPONENT SHOULD BE RETURNED BY ProjectsTabInitializer

        return loader.load();
    }

    public void onProjectEdit(Project project) throws IOException {
        ProjectEditDialogController controller = new ProjectEditDialogController(this);
        controller.setProject(project);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ProjectEditDialog.fxml"));
        loader.setController(controller);
        presenter.showAndWait("Edit project", new Scene(loader.load()));

        if (controller.isApproved())
            ProjectDao.saveProject(project);
    }

    public void onProjectMembersOverview(Project project) throws IOException {
        ProjectMembersOverviewController controller = new ProjectMembersOverviewController(this);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ProjectMembersOverview.fxml"));
        loader.setController(controller);

//        TODO: see comment above
//        presenter.setProjectsTabContent(loader.load());
        controller.setProject(project);
    }

    public void onProjectFinancialOverview(Project project) throws IOException {
        ProjectFinancialOverviewController controller = new ProjectFinancialOverviewController(this);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ProjectFinancialOverview.fxml"));
        loader.setController(controller);

//        TODO: see comment above
//        presenter.setProjectsTabContent(loader.load());
        controller.setProject(project);
    }

    public void onAddTeam(Project project) throws IOException {
        ITeamsModule teamsModule = moduleManager.getTeamsModule();

        TeamAddDialogController controller = new TeamAddDialogController(this);
        controller.setProject(project);
        controller.setTeams(teamsModule.getTeams());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/TeamAddDialog.fxml"));
        loader.setController(controller);

        presenter.showAndWait("Edit project", new Scene(loader.load()));

    }

    public void onProjectOverview() throws IOException {
//        TODO: see comment above
//        presenter.setProjectsTabContent(initRootLayout());
    }

    public void onCloseDialog() {
        presenter.closeCurrentStage();
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }
}
