package pl.edu.agh.iet.to2.projects.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pl.edu.agh.iet.to2.employees.IEmployee;
import pl.edu.agh.iet.to2.employees.IEmployeesModule;
import pl.edu.agh.iet.to2.projects.model.Project;
import pl.edu.agh.iet.to2.projects.presenter.ProjectPresenter;

import java.util.List;

public class MemberAddDialogController {

    private Project project;
    private List<IEmployee> employees;
    private ProjectPresenter presenter;

    public MemberAddDialogController(ProjectPresenter presenter) {
        this.presenter = presenter;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setEmployees(List<IEmployee> employees) {
        this.employees = employees;
    }

    @FXML
    private TableView<IEmployee> employeesTable;

    @FXML
    private TableColumn<IEmployee, String> firstNameColumn;

    @FXML
    private TableColumn<IEmployee, String> lastNameColumn;

    @FXML
    private TableColumn<IEmployee, String> occupationColumn;

    @FXML
    private TableColumn<IEmployee, String> salaryColumn;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    public void initialize() {
        IEmployeesModule employeesModule = presenter.getModuleManager().getEmployeesModule();
        employeesTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        firstNameColumn.setCellValueFactory(
                dataValue -> new SimpleStringProperty(dataValue.getValue().getName()));
        lastNameColumn.setCellValueFactory(
                dataValue -> new SimpleStringProperty(dataValue.getValue().getSurname()));

        occupationColumn.setCellValueFactory(
                dataValue -> new SimpleStringProperty(dataValue.getValue().getOccupation()));

        occupationColumn.setCellValueFactory(
                dataValue -> new SimpleStringProperty(dataValue.getValue().getSalary().toString()));

        employeesTable.setItems(FXCollections.observableList(employees));
    }

    @FXML
    private void handleOkAction(ActionEvent event) {
        ObservableList<IEmployee> selectedEmployees = employeesTable.getSelectionModel().getSelectedItems();
        if (selectedEmployees != null) updateModel(selectedEmployees);
        //ProjectDao.saveProject(project);
        presenter.onCloseDialog();
    }

    @FXML
    private void handleCancelAction(ActionEvent event) {
        presenter.onCloseDialog();
    }

    public void updateModel(List<IEmployee> employees) {
        for (IEmployee t : employees) {
            project.addMember(t);
        }
    }

}
