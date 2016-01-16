package pl.edu.agh.iet.to2.projects.controller;


import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pl.edu.agh.iet.to2.projects.model.Project;
import pl.edu.agh.iet.to2.projects.presenter.ProjectPresenter;
import pl.edu.agh.iet.to2.teams.ITeam;
import pl.edu.agh.iet.to2.teams.ITeamMember;

import java.io.IOException;
import java.math.BigDecimal;


public class ProjectFinancialOverviewController {

    private Project project;
    
    private ProjectPresenter presenter;

    public ProjectFinancialOverviewController(ProjectPresenter presenter){
        this.presenter = presenter;
    }
    
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

        nameColumn.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getEmployee().getName()+" "+dataValue.getValue().getEmployee().getSurname()));
        roleColumn.setCellValueFactory(dataValue -> new SimpleStringProperty(project.getMemberRoleMap().get(dataValue.getValue())));
        teamColumn.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getTeam().getName()));
        salaryColumn.setCellValueFactory(dataValue -> new SimpleObjectProperty<>(dataValue.getValue().getEmployee().getSalary()));
    }

    @FXML
    private void handleBackAction() throws IOException {

        presenter.onProjectOverview();

    }

}
