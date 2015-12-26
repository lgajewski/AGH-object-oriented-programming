package pl.edu.agh.iet.to2.employees.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import pl.edu.agh.iet.to2.Presenter;
import pl.edu.agh.iet.to2.TabInitializer;
import pl.edu.agh.iet.to2.employees.IEmployee;
import pl.edu.agh.iet.to2.employees.controller.EmployeeDetailsController;
import pl.edu.agh.iet.to2.employees.controller.EmployeeTabController;

import java.io.IOException;

public class EmployeeTab implements TabInitializer {

    private Presenter presenter;

    public Pane initialize(Presenter presenter) throws IOException {
        this.presenter = presenter;

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/employees/fxml/EmployeeTab.fxml"));
        loader.setController(new EmployeeTabController(presenter));

        return loader.load();
    }

    // TODO move it to seperate classes
/*    public void showEmployeeAddDialog() throws IOException {
        // Load the fxml file and create a new stage for the dialog
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/employees/fxml/AddEmployeeDialog.fxml"));
        BorderPane pane = loader.load();

        // Create the dialog Stage.
        presenter.showAndWait("Add employee", new Scene(pane));
    }


    public void showEmployeeDetails(IEmployee employee) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/employees/fxml/EmployeeDetails.fxml"));
        loader.setController(new EmployeeDetailsController(employee));
        AnchorPane pane = loader.load();

        presenter.showAndWait("Employee - " + employee, new Scene(pane));
    }*/
}