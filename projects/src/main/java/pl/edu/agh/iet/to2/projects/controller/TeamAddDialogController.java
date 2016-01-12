package pl.edu.agh.iet.to2.projects.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import pl.edu.agh.iet.to2.projects.dummy.ITeamsSource;
import pl.edu.agh.iet.to2.projects.model.Project;
import pl.edu.agh.iet.to2.teams.ITeam;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Marcin on 2015-12-16.
 */
public class TeamAddDialogController {

    private Project project;
    private ITeamsSource data;

    private Stage dialogStage;

    public void setProject(Project project) {
        this.project = project;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setData(ITeamsSource data) {
        this.data = data;
        teamsTable.setItems(data.getTeams());
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
    }

    @FXML
    private void handleOkAction(ActionEvent event) {
        ObservableList<ITeam> selectedTeams = teamsTable.getSelectionModel().getSelectedItems();
        if(selectedTeams != null) updateModel(selectedTeams);
        dialogStage.close();
    }

    @FXML
    private void handleCancelAction(ActionEvent event){
        dialogStage.close();
    }

    public void updateModel(List<ITeam> teams) {
        for(ITeam t : teams) {
            project.addTeam(t);
        }
    }

}
