package pl.edu.agh.iet.to2.projects.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.util.converter.LocalDateStringConverter;
import pl.edu.agh.iet.to2.projects.model.Project;
import pl.edu.agh.iet.to2.projects.presenter.ProjectPresenter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ProjectEditDialogController {


    private Project project;
    private ProjectPresenter presenter;

    public ProjectEditDialogController(ProjectPresenter presenter) {
        this.presenter = presenter;
    }


    @FXML
    private TextField nameTextField;

    @FXML
    private TextField startDateTextField;

    @FXML
    private TextField endDateTextField;


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

    public void setProject(Project project){
        this.project = project;
    }

    public Project getProject(){
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
            project.setStartDate(format.parse(startDateTextField.getText()));
            project.setEndDate(format.parse(endDateTextField.getText()));
            project.setName(nameTextField.getText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void updateControls() {
        nameTextField.setText(project.getName());
        startDateTextField.setText(project.getStartDate().toString());
        endDateTextField.setText(project.getEndDate().toString());
    }

}
