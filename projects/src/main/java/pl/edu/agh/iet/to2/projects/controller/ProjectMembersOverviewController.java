package pl.edu.agh.iet.to2.projects.controller;


import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.edu.agh.iet.to2.employees.IEmployee;
import pl.edu.agh.iet.to2.employees.IEmployeesModule;
import pl.edu.agh.iet.to2.projects.model.Employee;
import pl.edu.agh.iet.to2.projects.model.Project;
import pl.edu.agh.iet.to2.projects.persistence.ProjectDao;
import pl.edu.agh.iet.to2.projects.presenter.ProjectPresenter;

import java.io.IOException;
import java.util.List;


public class ProjectMembersOverviewController {

    private Project project;
    private ProjectPresenter presenter;
    private ObservableList<IEmployee> data = FXCollections.observableArrayList();
    private IEmployeesModule employeesModule;


    //TreeItem<IEmployee> root;

    public ProjectMembersOverviewController(ProjectPresenter presenter) {
        this.presenter = presenter;
    }


    @FXML
    private TableView<IEmployee> membersTable;

    @FXML
    private TableColumn<IEmployee, String> firstNameColumn;

    @FXML
    private TableColumn<IEmployee, String> lastNameColumn;

    @FXML
    private TableColumn<IEmployee, String> occupationColumn;
    
    @FXML
    private TableColumn<IEmployee, String> salaryColumn;

    @FXML
    private Button removeMemberButton;

    @FXML
    private Button addMemberButton;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {

        employeesModule = presenter.getModuleManager().getEmployeesModule();
        membersTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        firstNameColumn.setCellValueFactory(
                dataValue -> new SimpleStringProperty(dataValue.getValue().getName()));
        lastNameColumn.setCellValueFactory(
                dataValue -> new SimpleStringProperty(dataValue.getValue().getSurname()));

        occupationColumn.setCellValueFactory(
                dataValue -> new SimpleStringProperty(dataValue.getValue().getOccupation()));

        occupationColumn.setCellValueFactory(
                dataValue -> new SimpleStringProperty(dataValue.getValue().getSalary().toString()));

        removeMemberButton.disableProperty().bind(
                Bindings.size(membersTable.getSelectionModel().getSelectedItems()).isNotEqualTo(1));
        updateData();
    }


    public void setProject(Project project) {

        this.project = project;
        //loadTreeItems(project.getMembers());
        //teamsTable.getItems().setAll(project.getMembers());
    }

    public void updateData() {
        data.clear();
        for(Long id : project.getMembersIds()) {
            data.add(employeesModule.getEmployeeId(id));
        }
        membersTable.setItems(data);
    }


    /*public void loadTreeItems(List<IEmployee> items) {
        Employee emp = new Employee();
        emp.setName("Members");

        root = new TreeItem<>(new MemberMember(new Member(), emp));
        root.setExpanded(true);

        for (IMember team : rootItems) {
            TreeItem<IEmployee> teamNode = teamAsTreeItem(team);
            root.getChildren().add(teamNode);

            for (IEmployeemember : team.getMemberMembers()) {
                TreeItem<IEmployee> memberNode = new TreeItem<>(member);
                teamNode.getChildren().add(memberNode);
            }

        }

        membersTable.setRoot(root);
    }*/

    /*public TreeItem<IEmployee> teamAsTreeItem(IMember team) {
        Employee emp = new Employee();
        emp.setName(team.getName());
        return new TreeItem<>(new MemberMember(team, emp));
    }*/

    @FXML
    private void handleRemoveMemberAction() {
        IEmployee item = membersTable.getSelectionModel().getSelectedItem();
        project.removeMember(item);
        ProjectDao.saveProject(project);
        updateData();
    }

    @FXML
    private void handleAddMemberAction() throws IOException {

        presenter.onAddMember(project);
        ProjectDao.saveProject(project);
        updateData();

    }


    @FXML
    private void handleBackAction() throws IOException {

        presenter.onProjectOverview();

    }

}
