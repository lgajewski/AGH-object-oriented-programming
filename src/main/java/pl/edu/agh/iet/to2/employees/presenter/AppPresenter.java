package pl.edu.agh.iet.to2.employees.presenter;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.edu.agh.iet.to2.employees.controller.AppOverviewController;
import pl.edu.agh.iet.to2.employees.controller.EmployeeDetailsController;
import pl.edu.agh.iet.to2.employees.controller.generator.EmployeeGenerator;
import pl.edu.agh.iet.to2.employees.model.IEmployee;

import java.io.IOException;

public class AppPresenter {

    private EmployeeGenerator generator;
    private Stage primaryStage;

    public AppPresenter(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.generator = new EmployeeGenerator();
    }

    public void initRootLayout() throws IOException {
        // load layout from FXML file
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/employees/view/AppOverview.fxml"));

        loader.setController(new AppOverviewController(generator));

        AnchorPane rootLayout = loader.load();

        // get controller from loaded layout
        AppOverviewController controller = loader.getController();

        primaryStage.setScene(new Scene(rootLayout));
        primaryStage.show();
    }

    public void showEmployeeDetails(IEmployee employee) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/employees/view/EmployeeDetails.fxml"));
        loader.setController(new EmployeeDetailsController());

        AnchorPane rootLayout = loader.load();

        // create new stage
        Stage stage = new Stage();
        stage.setTitle("Employee - " + employee);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(primaryStage);

        // set scene for a new stage
        Scene scene = new Scene(rootLayout);
        stage.setScene(scene);

        stage.showAndWait();
    }

}
