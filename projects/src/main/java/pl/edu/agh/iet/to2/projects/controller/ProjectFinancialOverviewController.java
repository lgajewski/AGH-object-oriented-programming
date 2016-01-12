package pl.edu.agh.iet.to2.projects.controller;

import interfaces.ITeam;
import interfaces.ITeamMember;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import presenter.ProjectPresenter;
import projectsmodel.Project;

import java.math.BigDecimal;

/**
 * Created by Marcin on 2015-12-15.
 */
public class ProjectFinancialOverviewController {

    private Project project;
    
    private ProjectPresenter presenter;
    
    @FXML
    private TableView<ITeamMember> membersTable;

    @FXML
    private TableColumn<ITeamMember, String> nameColumn;

    @FXML
    private TableColumn<ITeamMember, String> roleColumn;

    @FXML
    private TableColumn<ITeamMember, String> teamColumn;

    @FXML
    private TableColumn<ITeamMember, BigDecimal> salaryColumn;

    @FXML
    private Button backButton;

    public void setPresenter(ProjectPresenter presenter) {
        this.presenter = presenter;
    }

    public void setProject(Project project) {
        this.project = project;
        ObservableList<ITeamMember> teamMembers = FXCollections.observableArrayList();
        for(ITeam t : project.getTeams()) {
            for(ITeamMember tm : t.getTeamMembers()) {
                teamMembers.add(tm);
            }
        }
        membersTable.setItems(teamMembers);
    }
    
    public void initialize() {
        membersTable.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE);

        nameColumn.setCellValueFactory(dataValue -> dataValue.getValue().getEmployee().getFullNameProperty());
        roleColumn.setCellValueFactory(dataValue -> new SimpleStringProperty(project.getMemberRoleMap().get(dataValue.getValue())));
        teamColumn.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getTeam().getName()));
        salaryColumn.setCellValueFactory(dataValue -> dataValue.getValue().getEmployee().getSalaryProperty());
    }

    @FXML
    private void handleBackAction() {

        presenter.goBackToMainPage();

    }

}
