package pl.edu.agh.iet.to2.projects.controller;


import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pl.edu.agh.iet.to2.projects.dummy.IProjectsSource;
import pl.edu.agh.iet.to2.projects.model.Project;
import pl.edu.agh.iet.to2.projects.presenter.ProjectPresenter;

import java.time.LocalDate;
import java.util.List;

public class ProjectOverviewController {

    private List<Project> data;

    private ProjectPresenter presenter;

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

    public void setData(IProjectsSource projects) {
        this.data = projects.getProjects();
        projectsTable.setItems(projects.getProjects());
    }

    public void setPresenter(ProjectPresenter presenter) {
        this.presenter = presenter;
    }

    @FXML
    private void initialize() {
        projectsTable.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE);

        nameColumn.setCellValueFactory(dataValue -> dataValue.getValue().getNameProperty());

        startDateColumn.setCellValueFactory(dataValue -> dataValue.getValue().getStartDateProperty());

        endDateColumn.setCellValueFactory(dataValue -> dataValue.getValue().getEndDateProperty());

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
        for (Project project : projectsTable.getSelectionModel().getSelectedItems()) {
            data.remove(project);
        }
    }

    @FXML
    private void handleEditAction(ActionEvent event) {
        Project project = projectsTable.getSelectionModel()
                .getSelectedItem();
        if (project != null) {
            presenter.showProjectEditDialog(project);
        }
    }

    @FXML
    private void handleAddAction(ActionEvent event) {
        Project project = Project.newProject();

        if (presenter.showProjectEditDialog(project)) {
            data.add(project);
        }
    }

    @FXML
    private void handleProjectMembersAction(ActionEvent event) {
        Project project = projectsTable.getSelectionModel().getSelectedItem();
        if (project != null) {
            presenter.showProjectMembersOverview(project);
        }
    }

    @FXML
    private void handleFinancialDetailsAction(ActionEvent event) {
        Project project = projectsTable.getSelectionModel().getSelectedItem();
        if (project != null) {
            presenter.showFinancialOverview(project);
        }
    }


}
