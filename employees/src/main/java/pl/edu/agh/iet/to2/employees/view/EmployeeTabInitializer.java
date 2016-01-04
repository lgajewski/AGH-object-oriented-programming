package pl.edu.agh.iet.to2.employees.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import pl.edu.agh.iet.to2.Presenter;
import pl.edu.agh.iet.to2.TabInitializer;
import pl.edu.agh.iet.to2.employees.controller.EmployeeTabController;

import java.io.IOException;

public class EmployeeTabInitializer implements TabInitializer {

    public Pane initialize(Presenter presenter) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/employees/fxml/EmployeeTab.fxml"));
        loader.setController(new EmployeeTabController(presenter));

        return loader.load();
    }

}