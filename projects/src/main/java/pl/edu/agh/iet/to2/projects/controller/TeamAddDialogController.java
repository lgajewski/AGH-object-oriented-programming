package pl.edu.agh.iet.to2.projects.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pl.edu.agh.iet.to2.projects.model.Project;
import pl.edu.agh.iet.to2.projects.presenter.ProjectPresenter;
import pl.edu.agh.iet.to2.teams.ITeam;

import java.math.BigDecimal;
import java.util.List;

public class TeamAddDialogController {

    private Project project;
    private List<ITeam> teams;
    private ProjectPresenter presenter;

    public TeamAddDialogController(ProjectPresenter presenter){
        this.presenter = presenter;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setTeams(List<ITeam> teams) {
        this.teams = teams;
    }

    @FXML
    private TableView<ITeam> teamsTable;

    @FXML
    private TableColumn<ITeam, String> teamColumn;

    @FXML
    private TableColumn<ITeam, Long> numberColumn;

    @FXML
    private TableColumn<ITeam, BigDecimal> costColumn;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    public void initialize() {
        teamsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        teamColumn.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getName()));
        numberColumn.setCellValueFactory(dataValue -> new SimpleObjectProperty<Long>((long)dataValue.getValue().getTeamMembers().size()));
        costColumn.setCellValueFactory(dataValue -> new SimpleObjectProperty<BigDecimal>(dataValue.getValue().getCost()));
        teamsTable.setItems(FXCollections.observableList(teams));
    }

    @FXML
    private void handleOkAction(ActionEvent event) {
        ObservableList<ITeam> selectedTeams = teamsTable.getSelectionModel().getSelectedItems();
        if(selectedTeams != null) updateModel(selectedTeams);
        presenter.onCloseDialog();
    }

    @FXML
    private void handleCancelAction(ActionEvent event){
        presenter.onCloseDialog();
    }

    public void updateModel(List<ITeam> teams) {
        for(ITeam t : teams) {
            project.addTeam(t);
        }
    }

}
