package pl.edu.agh.iet.to2.projects.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import pl.edu.agh.iet.to2.projects.model.Project;
import pl.edu.agh.iet.to2.projects.presenter.ProjectPresenter;
import pl.edu.agh.iet.to2.utils.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ProjectEditDialogController {


    private Project project;
    private ProjectPresenter presenter;

    public ProjectEditDialogController(ProjectPresenter presenter) {
        this.presenter = presenter;
    }


    @FXML
    private TextField nameTextField;

    @FXML
    private DatePicker startDate;

    @FXML
    private DatePicker endDate;

    @FXML
    private TextField budgetTextField;


    private boolean approved;
    private SimpleDateFormat format;

    @FXML
    public void initialize() {
        String pattern = "yyyy-MM-dd";
        format = new SimpleDateFormat(pattern);
        updateControls();
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        //converter = new LocalDateStringConverter(formatter, formatter);

    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Project getProject() {
        return project;
    }


    public boolean isApproved() {
        return approved;
    }

    @FXML
    private void handleOkAction(ActionEvent event) {
        if (isInputValid()) {
            updateModel();
            approved = true;
            presenter.onCloseDialog();
        }
    }

    @FXML
    private void handleCancelAction(ActionEvent event) {
        presenter.onCloseDialog();
    }

    private boolean isInputValid() {
        // TODO: implement

        return true;
    }

    private void updateModel() {
        try {
            project.setStartDate(Utils.convertLocalDateToDate(startDate.getValue()));
            project.setEndDate(Utils.convertLocalDateToDate(endDate.getValue()));
            project.setName(nameTextField.getText());
            project.setBudget(Utils.convertStringToBigDecimal(budgetTextField.getText()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void updateControls() {
        nameTextField.setText(project.getName());
        startDate.setValue(Utils.convertDateToLocalDate(project.getStartDate()));
        endDate.setValue(Utils.convertDateToLocalDate(project.getEndDate()));
        budgetTextField.setText(project.getBudget().toString());
    }

}
