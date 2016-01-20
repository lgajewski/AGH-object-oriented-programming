package pl.edu.agh.iet.to2.projects.controller;


import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.edu.agh.iet.to2.projects.model.Employee;
import pl.edu.agh.iet.to2.projects.model.Project;
import pl.edu.agh.iet.to2.projects.model.Team;
import pl.edu.agh.iet.to2.projects.model.TeamMember;
import pl.edu.agh.iet.to2.projects.presenter.ProjectPresenter;
import pl.edu.agh.iet.to2.teams.ITeam;
import pl.edu.agh.iet.to2.teams.ITeamMember;

import java.io.IOException;
import java.util.List;


public class ProjectMembersOverviewController {

    private Project project;
    private ProjectPresenter presenter;

    TreeItem<ITeamMember> root;

    public ProjectMembersOverviewController(ProjectPresenter presenter) {
        this.presenter = presenter;
    }


    @FXML
    private TreeTableView<ITeamMember> membersTable;

    @FXML
    private TreeTableColumn<ITeamMember, String> firstNameColumn;

    @FXML
    private TreeTableColumn<ITeamMember, String> lastNameColumn;

    @FXML
    private TreeTableColumn<ITeamMember, String> occupationColumn;

    @FXML
    private Button removeTeamButton;

    @FXML
    private Button addTeamButton;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {

        membersTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        firstNameColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<ITeamMember, String> param) ->
                        new ReadOnlyStringWrapper(param.getValue().getValue().getEmployee().getName())
        );
        lastNameColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<ITeamMember, String> param) ->
                        new ReadOnlyStringWrapper(param.getValue().getValue().getEmployee().getSurname())
        );
        occupationColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<ITeamMember, String> param) ->
                        new ReadOnlyStringWrapper(param.getValue().getValue().getEmployee().getOccupation())
        );

        removeTeamButton.disableProperty().bind(
                Bindings.size(membersTable.getSelectionModel().getSelectedItems()).isNotEqualTo(1));

    }


    public void setProject(Project project) {

        this.project = project;
        loadTreeItems(project.getTeams());
        //teamsTable.getItems().setAll(project.getTeams());
    }


    public void loadTreeItems(List<ITeam> rootItems) {
        Employee emp = new Employee();
        emp.setName("Teams");

        root = new TreeItem<>(new TeamMember(new Team(), emp));
        root.setExpanded(true);

        for (ITeam team : rootItems) {
            TreeItem<ITeamMember> teamNode = teamAsTreeItem(team);
            root.getChildren().add(teamNode);

            for (ITeamMember member : team.getTeamMembers()) {
                TreeItem<ITeamMember> memberNode = new TreeItem<>(member);
                teamNode.getChildren().add(memberNode);
            }

        }

        membersTable.setRoot(root);
    }

    public TreeItem<ITeamMember> teamAsTreeItem(ITeam team) {
        Employee emp = new Employee();
        emp.setName(team.getName());
        return new TreeItem<>(new TeamMember(team, emp));
    }

    @FXML
    private void handleRemoveTeamAction() {
        TreeItem<ITeamMember> item = membersTable.getSelectionModel().getSelectedItem();
        if (item.getParent().equals(root)) {
            ITeam toDelete = item.getValue().getTeam();
            project.removeTeam(toDelete);
        }

    }

    @FXML
    private void handleAddTeamAction() throws IOException {

        presenter.onAddTeam(project);
        loadTreeItems(project.getTeams());

    }


    @FXML
    private void handleBackAction() throws IOException {

        //ProjectDao.saveProject(project);
        presenter.onProjectOverview();

    }

}
