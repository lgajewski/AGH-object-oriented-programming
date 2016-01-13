package pl.edu.agh.iet.to2.projects.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
import pl.edu.agh.iet.to2.Presenter;
import pl.edu.agh.iet.to2.projects.model.Project;

import java.time.format.DateTimeFormatter;

public class ProjectEditDialogController {


    private Project project;
    private Presenter presenter;

    public ProjectEditDialogController(Presenter presenter) {
        this.presenter = presenter;
    }


    @FXML
    private TextField nameTextField;

    @FXML
    private TextField startDateTextField;

    @FXML
    private TextField endDateTextField;


    private Stage dialogStage;

    private boolean approved;

    private LocalDateStringConverter converter;

    @FXML
    public void initialize() {
        String pattern = "yyyy-MM-dd";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        converter = new LocalDateStringConverter(formatter, formatter);

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setData(Project project) {
        this.project = project;
//        updateControls();
    }

    public boolean isApproved() {
        return approved;
    }

    @FXML
    private void handleOkAction(ActionEvent event) {
        if (isInputValid()) {
            updateModel();
            approved = true;
            presenter.closeCurrentStage();
        }
    }

    @FXML
    private void handleCancelAction(ActionEvent event) {
        dialogStage.close();
    }

    private boolean isInputValid() {
        // TODO: implement

        return true;
    }

    private void updateModel() {
        project.setStartDate(converter.fromString(startDateTextField.getText()));
        project.setEndDate(converter.fromString(endDateTextField.getText()));
        project.setName(nameTextField.getText());
    }

    private void updateControls() {
        nameTextField.setText(project.getName());
        startDateTextField.setText(converter.toString(project.getStartDate()));
        endDateTextField.setText(converter.toString(project.getEndDate()));
    }

}
