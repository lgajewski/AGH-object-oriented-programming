package pl.edu.agh.iet.to2.projects.presenter;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.edu.agh.iet.to2.projects.controller.*;
import pl.edu.agh.iet.to2.projects.dummy.IProjectsSource;
import pl.edu.agh.iet.to2.projects.dummy.ITeamsSource;
import pl.edu.agh.iet.to2.projects.model.Project;

import java.io.IOException;

public class ProjectPresenter {

    private Stage primaryStage;

    private Scene primaryScene;

    private IProjectsSource source;

    private ITeamsSource teamsSource;

    public ProjectPresenter(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setProjectsSource(IProjectsSource source){
        this.source = source;
    }

    public void setTeamsSource(ITeamsSource teamsSource) {
        this.teamsSource = teamsSource;
    }

    public void initRootLayout() {
        try {
            this.primaryStage.setTitle("Projects");

            // load layout from FXML file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/ProjectOverview.fxml"));
            BorderPane rootLayout = (BorderPane) loader.load();

            // set initial data into controller
            ProjectOverviewController controller = loader.getController();
            //controller.setPresenter(this);
            //controller.setData(source);

            // add layout to a scene and show them all
            primaryScene = new Scene(rootLayout);
            primaryStage.setScene(primaryScene);
            primaryStage.show();

        } catch (IOException e) {
            // don't do this in common apps
            e.printStackTrace();
        }

    }

    public boolean showProjectEditDialog(Project project) {
        try {
            // Load the fxml file and create a new stage for the dialog
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/ProjectEditDialog.fxml"));
            BorderPane page = (BorderPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Project");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            ProjectEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setData(project);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            return controller.isApproved();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void showProjectMembersOverview(Project project) {
        try {
            this.primaryStage.setTitle("ProjectMembers");

            // load layout from FXML file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/ProjectMembersOverview.fxml"));
            BorderPane rootLayout = (BorderPane) loader.load();

            // set initial data into controller
            ProjectMembersOverviewController controller = loader.getController();
            controller.setPresenter(this);
            controller.setProject(project);
            //controller.setData(new ArrayList<Project>());

            //controller.setProject(Project.newProject());

            // add layout to a scene and show them all
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            //primaryStage.show();

        } catch (IOException e) {
            // don't do this in common apps
            e.printStackTrace();
        }
    }

    public void showFinancialOverview(Project project) {
        try {
            this.primaryStage.setTitle("Financial Details");

            // load layout from FXML file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/ProjectFinancialOverview.fxml"));
            BorderPane rootLayout = (BorderPane) loader.load();

            // set initial data into controller
            ProjectFinancialOverviewController controller = loader.getController();
            controller.setPresenter(this);
            controller.setProject(project);
            //controller.setData(new ArrayList<Project>());

            //controller.setProject(Project.newProject());

            // add layout to a scene and show them all
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            //primaryStage.show();

        } catch (IOException e) {
            // don't do this in common apps
            e.printStackTrace();
        }
    }

    public void showAddTeamDialog(Project project) {

        try {
            // Load the fxml file and create a new stage for the dialog
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/TeamAddDialog.fxml"));
            BorderPane page = (BorderPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Team");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            TeamAddDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setData(teamsSource);
            controller.setProject(project);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goBackToMainPage() {

        primaryStage.setTitle("Projects");
        primaryStage.setScene(primaryScene);

    }



}
