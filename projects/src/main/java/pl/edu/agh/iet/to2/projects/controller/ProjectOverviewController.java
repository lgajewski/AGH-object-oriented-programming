package pl.edu.agh.iet.to2.projects.controller;


import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pl.edu.agh.iet.to2.projects.model.Project;
import pl.edu.agh.iet.to2.projects.persistence.ProjectDao;
import pl.edu.agh.iet.to2.projects.presenter.ProjectPresenter;

import java.io.IOException;
import java.time.LocalDate;

public class ProjectOverviewController {

    private ObservableList<Project> data = FXCollections.observableArrayList();

    private ProjectPresenter presenter;

    public ProjectOverviewController(ProjectPresenter presenter){
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


    public void updateData() {
        data.clear();
        data.setAll(ProjectDao.getProjects());
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
        projectsTable.setItems(data);
        updateData();
    }

    @FXML
    private void handleDeleteAction(ActionEvent event) {
        //ProjectDao.deleteProjects(projectsTable.getSelectionModel().getSelectedItems());
        for (Project project : projectsTable.getSelectionModel().getSelectedItems()) {
            data.remove(project);
        }
    }

    @FXML
    private void handleEditAction(ActionEvent event) throws IOException {
        Project project = projectsTable.getSelectionModel()
                .getSelectedItem();
        presenter.onProjectEdit(project);
        //ProjectDao.saveProject(project);
    }

    @FXML
    private void handleAddAction(ActionEvent event) throws IOException {
        Project project = Project.newProject();
        presenter.onProjectEdit(project);
        data.add(project);
        //ProjectDao.saveProject(project);
    }


    @FXML
    private void handleProjectMembersAction(ActionEvent event) throws IOException {
        Project project = projectsTable.getSelectionModel().getSelectedItem();
        if (project != null) {
            presenter.onProjectMembersOverview(project);
        }
    }

    @FXML
    private void handleFinancialDetailsAction(ActionEvent event) throws IOException {
        Project project = projectsTable.getSelectionModel().getSelectedItem();
        if (project != null) {
            presenter.onProjectFinancialOverview(project);
        }
    }


}
