package pl.edu.agh.iet.to2.projects.controller;


import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import pl.edu.agh.iet.to2.Presenter;
import pl.edu.agh.iet.to2.projects.dummy.IProjectsSource;
import pl.edu.agh.iet.to2.projects.model.Project;
import pl.edu.agh.iet.to2.projects.persistence.ProjectDao;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ProjectOverviewController {

    private ObservableList<Project> data;

    private Presenter presenter;

    public ProjectOverviewController(Presenter presenter){
        this.presenter = presenter;
    }

    @FXML
    private TableView<Project> projectsTable;

    @FXML
    private TableColumn<Project, String> nameColumn;

    @FXML
    private TableColumn<Project, LocalDate> startDateColumn;

    @FXML
    private TableColumn<Project, LocalDate> endDateColumn;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private Button addButton;

    @FXML
    private Button projectMembersButton;

    @FXML
    private Button projectFinancialDetailsButton;

    public void setData() {
        data = FXCollections.observableArrayList();
        updateData();
        projectsTable.setItems(data);
    }

    public void updateData() {
        data.clear();
        data.setAll(ProjectDao.getProjects());
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @FXML
    private void initialize() {
        projectsTable.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE);

        nameColumn.setCellValueFactory(
                dataValue -> dataValue.getValue().getNameProperty());

        startDateColumn.setCellValueFactory(
                dataValue -> dataValue.getValue().getStartDateProperty());

        endDateColumn.setCellValueFactory(
                dataValue -> dataValue.getValue().getEndDateProperty());

        deleteButton.disableProperty().bind(
                Bindings.isEmpty(projectsTable.getSelectionModel()
                        .getSelectedItems()));

        editButton.disableProperty().bind(
                Bindings.size(projectsTable.getSelectionModel().getSelectedItems()).isNotEqualTo(1));

        projectMembersButton.disableProperty().bind(
                Bindings.size(projectsTable.getSelectionModel().getSelectedItems()).isNotEqualTo(1));

        projectFinancialDetailsButton.disableProperty().bind(
                Bindings.size(projectsTable.getSelectionModel().getSelectedItems()).isNotEqualTo(1));
    }

    @FXML
    private void handleDeleteAction(ActionEvent event) {
        ProjectDao.deleteProjects(projectsTable.getSelectionModel().getSelectedItems());
        for (Project project : projectsTable.getSelectionModel().getSelectedItems()) {
            data.remove(project);
        }
    }

    @FXML
    private void handleEditAction(ActionEvent event) throws IOException {
        Project project = projectsTable.getSelectionModel()
                .getSelectedItem();

        Pane pane = prepareProjectEditDialog(project);

        // Create the dialog Stage.
        presenter.showAndWait("Edit project", new Scene(pane));
        ProjectDao.saveProject(project);
    }

    @FXML
    private void handleAddAction(ActionEvent event) throws IOException {
        Project project = Project.newProject();


        Pane pane = prepareProjectEditDialog(project);

        // Create the dialog Stage.
        presenter.showAndWait("Add project", new Scene(pane));

        data.add(project);
        ProjectDao.saveProject(project);
    }

    private Pane prepareProjectEditDialog(Project project) throws IOException {
        ProjectEditDialogController controller = new ProjectEditDialogController();
        controller.setData(project);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ProjectEditDialog.fxml"));
        loader.setController(controller);

        return loader.load();
    }

    private Pane prepareProjectMembersOverview(Project project) throws IOException {
        ProjectEditDialogController controller = new ProjectEditDialogController();
        controller.setData(project);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ProjectEditDialog.fxml"));
        loader.setController(controller);

        return loader.load();
    }

    @FXML
    private void handleProjectMembersAction(ActionEvent event) {
        Project project = projectsTable.getSelectionModel().getSelectedItem();
        if (project != null) {
           // presenter.showProjectMembersOverview(project);
        }
    }

    @FXML
    private void handleFinancialDetailsAction(ActionEvent event) {
        Project project = projectsTable.getSelectionModel().getSelectedItem();
        if (project != null) {
           // presenter.showFinancialOverview(project);
        }
    }


}
