package pl.edu.agh.iet.to2.employees.presenter;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.edu.agh.iet.to2.Main;
import pl.edu.agh.iet.to2.employees.controller.AppOverviewController;
import pl.edu.agh.iet.to2.employees.controller.EmployeeAddDialogController;
import pl.edu.agh.iet.to2.employees.controller.generator.EmployeeGenerator;

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

    public boolean showEmployeeAddDialog() {
        try {
            // Load the fxml file and create a new stage for the dialog
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("resources/fxml/employees/view/AddEmployeeDialog.fxml"));
            BorderPane page = (BorderPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add employee");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            EmployeeAddDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            return controller.isApproved();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
