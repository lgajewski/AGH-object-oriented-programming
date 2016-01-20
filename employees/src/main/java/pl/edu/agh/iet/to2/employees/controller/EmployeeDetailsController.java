package pl.edu.agh.iet.to2.employees.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.util.converter.BigDecimalStringConverter;
import pl.edu.agh.iet.to2.app.Presenter;
import pl.edu.agh.iet.to2.employees.model.Employee;

import java.io.File;

public class EmployeeDetailsController {

    private final Employee employee;
    private final Presenter presenter;

    public EmployeeDetailsController(Presenter presenter) {
        this(presenter, new Employee());
    }

    public EmployeeDetailsController(Presenter presenter, Employee employee) {
        this.presenter = presenter;
        this.employee = employee;
    }

    private boolean submitted = false;

    @FXML
    private ImageView avatarImageView;

    @FXML
    private Button changeAvatar;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField salaryField;

    @FXML
    private TextField occupationField;

    @FXML
    private ImageView submitButton;

    @FXML
    private ImageView historyButton;


    @FXML
    private void initialize() {
        nameField.textProperty().bindBidirectional(employee.getNameProperty());
        surnameField.textProperty().bindBidirectional(employee.getSurnameProperty());
        salaryField.textProperty().bindBidirectional(employee.getSalaryProperty(), new BigDecimalStringConverter());
        occupationField.textProperty().bindBidirectional(employee.getOccupationProperty());

        historyButton.setOnMouseClicked(event -> showHistoryStage());

        submitButton.setOnMouseClicked(event -> {
            presenter.closeCurrentStage();
            submitted = true;
        });

        // avatar - listener for file chooser
        changeAvatar.setOnMouseClicked(event -> showChangeAvatarStage());

        // bind listener to update
        employee.getAvatarPathProperty().addListener((observable, oldValue, newValue) -> updateAvatar(newValue));

        // update with current value
        updateAvatar(employee.getAvatarName());
    }

    private void showChangeAvatarStage() {
        FileChooser fileChooser = new FileChooser();
        File initialDirectory = new File(getClass().getResource(EmployeeCell.INITIAL_DIRECTORY).getFile());
        fileChooser.setInitialDirectory(initialDirectory);
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            String relativePath = initialDirectory.toURI().relativize(selectedFile.toURI()).getPath();
            employee.setAvatarPath(relativePath);
        } else {
            System.out.println("File selection cancelled.");
        }
    }

    private void showHistoryStage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/employees/fxml/EmployeeHistory.fxml"));
            loader.setController(new EmployeeHistoryController(presenter, employee));
            Pane pane = loader.load();

            presenter.showAndWait("Employee History - " + employee, new Scene(pane));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Employee getEmployee() {
        return submitted ? employee : null;
    }

    public void updateAvatar(String avatarName) {
        Image image = new Image(getClass().getResourceAsStream(EmployeeCell.INITIAL_DIRECTORY + avatarName));
        avatarImageView.setImage(image);
    }
}
