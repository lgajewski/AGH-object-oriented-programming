package pl.edu.agh.iet.to2.projects.presenter;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import pl.edu.agh.iet.to2.Presenter;
import pl.edu.agh.iet.to2.employees.EmployeesModule;
import pl.edu.agh.iet.to2.projects.controller.*;
import pl.edu.agh.iet.to2.projects.model.Project;
import pl.edu.agh.iet.to2.teams.TeamsModule;

import java.io.IOException;

public class ProjectPresenter {

    private Presenter presenter;
    private EmployeesModule employeesModule;
    private TeamsModule teamsModule;

    public ProjectPresenter(Presenter presenter, EmployeesModule employeesModule, TeamsModule teamsModule){
        this.presenter = presenter;
        this.employeesModule = employeesModule;
        this.teamsModule = teamsModule;
    }

    public Pane initRootLayout() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ProjectOverview.fxml"));
        loader.setController(new ProjectOverviewController(this));
        return loader.load();
    }

    public void onProjectEdit(Project project) throws IOException {
        ProjectEditDialogController controller = new ProjectEditDialogController(this);
        controller.setProject(project);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ProjectEditDialog.fxml"));
        loader.setController(controller);
        presenter.showAndWait("Edit project", new Scene(loader.load()));
    }

    public void onProjectMembersOverview(Project project) throws IOException {
        ProjectMembersOverviewController controller = new ProjectMembersOverviewController(this);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ProjectMembersOverview.fxml"));
        loader.setController(controller);

        presenter.setProjectsTabContent(loader.load());
        controller.setProject(project);
    }

    public void onProjectFinancialOverview(Project project) throws IOException {
        ProjectFinancialOverviewController controller = new ProjectFinancialOverviewController(this);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ProjectFinancialOverview.fxml"));
        loader.setController(controller);

        presenter.setProjectsTabContent(loader.load());
        controller.setProject(project);
    }

    public void onAddTeam(Project project) throws IOException {
        TeamAddDialogController controller = new TeamAddDialogController(this);
        controller.setProject(project);
        controller.setTeams(teamsModule.getTeams());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/TeamAddDialog.fxml"));
        loader.setController(controller);

        presenter.showAndWait("Edit project", new Scene(loader.load()));

    }

    public void onProjectOverview() throws IOException {
        presenter.setProjectsTabContent(initRootLayout());
    }

    public void onCloseDialog() {
        presenter.closeCurrentStage();
    }



}
