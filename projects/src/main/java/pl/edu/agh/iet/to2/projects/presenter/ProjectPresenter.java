package pl.edu.agh.iet.to2.projects.presenter;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import pl.edu.agh.iet.to2.app.ModuleManager;
import pl.edu.agh.iet.to2.app.Presenter;
import pl.edu.agh.iet.to2.employees.IEmployeesModule;
import pl.edu.agh.iet.to2.projects.controller.*;
import pl.edu.agh.iet.to2.projects.model.Project;
import pl.edu.agh.iet.to2.projects.persistence.ProjectDao;

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

        for (Long id : project.getMembersIds()) {
            project.addMember(moduleManager.getEmployeesModule().getEmployeeId(id));
        }

        controller.setProject(project);
//        TODO: see comment above
        presenter.setProjectsTabContent(loader.load());
    }

    public void onProjectFinancialOverview(Project project) throws IOException {
        ProjectFinancialOverviewController controller = new ProjectFinancialOverviewController(this);
        controller.setProject(project);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ProjectFinancialOverview.fxml"));
        loader.setController(controller);

//        TODO: see comment above
        presenter.setProjectsTabContent(loader.load());
    }

    public void onAddMember(Project project) throws IOException {
        IEmployeesModule employeesModule = moduleManager.getEmployeesModule();

        MemberAddDialogController controller = new MemberAddDialogController(this);
        controller.setProject(project);
        controller.setEmployees(employeesModule.getEmployees());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/MemberAddDialog.fxml"));
        loader.setController(controller);

        presenter.showAndWait("Edit project", new Scene(loader.load()));

    }

    public void onProjectOverview() throws IOException {
//        TODO: see comment above
        presenter.setProjectsTabContent(initRootLayout());
    }


    public void onCloseDialog() {
        presenter.closeCurrentStage();
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }
}
