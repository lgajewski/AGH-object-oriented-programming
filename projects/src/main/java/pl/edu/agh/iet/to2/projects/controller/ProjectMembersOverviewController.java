package pl.edu.agh.iet.to2.projects.controller;


import interfaces.ITeam;
import interfaces.ITeamMember;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import othersmodel.Employee;
import othersmodel.Team;
import othersmodel.TeamMember;
import presenter.ProjectPresenter;
import projectsmodel.Project;

import java.util.List;

/**
 * Created by Marcin on 2015-12-09.
 */
public class ProjectMembersOverviewController {

    private Project project;

    private ProjectPresenter presenter;

    TreeItem<ITeamMember> root;

    public void setPresenter(ProjectPresenter presenter) {
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
                        new ReadOnlyStringWrapper(param.getValue().getValue().getEmployee().getFirstName())
        );
        lastNameColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<ITeamMember, String> param) ->
                        new ReadOnlyStringWrapper(param.getValue().getValue().getEmployee().getLastName())
        );
        occupationColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<ITeamMember, String> param) ->
                        new ReadOnlyStringWrapper(param.getValue().getValue().getEmployee().getOccupation())
        );

        removeTeamButton.disableProperty().bind(
                Bindings.size(membersTable.getSelectionModel().getSelectedItems()).isNotEqualTo(1));

    }


    public void setProject(Project project) {
        /*List<Team> teams = new ArrayList<>();
        Team team1 = new Team();
        team1.setName("team1");
        team1.addTeamMember(new TeamMember(team1, new Employee(0, "Marek", "Marek", new Salary(new BigDecimal(3000)), "Junior Software Engineer")));
        team1.addTeamMember(new TeamMember(team1, new Employee(1, "Darek", "Marek", new Salary(new BigDecimal(4000)), "Software Engineer")));
        team1.addTeamMember(new TeamMember(team1, new Employee(2, "Jarek", "Marek", new Salary(new BigDecimal(4000)), "Software Engineer")));
        Team team2 = new Team();
        team2.setName("team2");
        team2.addTeamMember(new TeamMember(team2, new Employee(3, "Jan", "Nowak", new Salary(new BigDecimal(6000)), "Senior Software Engineer")));*/
        this.project = project;
        loadTreeItems(project.getTeams());
        //teamsTable.getItems().setAll(project.getTeams());
    }


    public void loadTreeItems(List<ITeam> rootItems) {
        Employee emp = new Employee();
        emp.setFirstName("Teams");

        root = new TreeItem<>(new TeamMember(new Team(), emp));
        root.setExpanded(true);

        for (ITeam team: rootItems) {
            TreeItem<ITeamMember> teamNode = teamAsTreeItem(team);
            root.getChildren().add(teamNode);

            for(ITeamMember member: team.getTeamMembers()){
                TreeItem<ITeamMember> memberNode = new TreeItem<>(member);
                teamNode.getChildren().add(memberNode);
            }

        }

        membersTable.setRoot(root);
    }

    public TreeItem<ITeamMember> teamAsTreeItem(ITeam team){
        Employee emp = new Employee();
        emp.setFirstName(team.getName());
        return new TreeItem<>(new TeamMember(team, emp));
    }

    @FXML
    private void handleRemoveTeamAction() {
        TreeItem<ITeamMember> item = membersTable.getSelectionModel().getSelectedItem();
        if(item.getParent().equals(root)) {
            ITeam toDelete = item.getValue().getTeam();
            project.removeTeam(toDelete);
        }

    }

    @FXML
    private void handleAddTeamAction() {

        presenter.showAddTeamDialog(project);

    }


    @FXML
    private void handleBackAction() {

        presenter.goBackToMainPage();

    }

}
