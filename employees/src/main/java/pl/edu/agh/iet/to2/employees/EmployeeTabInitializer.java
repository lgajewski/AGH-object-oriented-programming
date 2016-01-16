package pl.edu.agh.iet.to2.employees;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import pl.edu.agh.iet.to2.app.ModuleManager;
import pl.edu.agh.iet.to2.app.Presenter;
import pl.edu.agh.iet.to2.app.TabInitializer;
import pl.edu.agh.iet.to2.employees.controller.EmployeeTabController;
import pl.edu.agh.iet.to2.employees.persistence.HibernateUtils;

import java.io.IOException;

public class EmployeeTabInitializer implements TabInitializer {

    @Override
    public Pane initialize(Presenter presenter, ModuleManager moduleManager) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/employees/fxml/EmployeeTab.fxml"));
        loader.setController(new EmployeeTabController(presenter, moduleManager));

        // add hibernate shutdown listener
        presenter.addOnStopListener(HibernateUtils::shutdown);

        return loader.load();
    }

}
