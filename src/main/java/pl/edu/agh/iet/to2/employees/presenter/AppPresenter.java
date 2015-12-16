package pl.edu.agh.iet.to2.employees.presenter;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pl.edu.agh.iet.to2.employees.controller.AppOverviewController;
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

        loader.setController(new AppOverviewController(this, generator));

        AnchorPane rootLayout = loader.load();

        primaryStage.setScene(new Scene(rootLayout));
        primaryStage.show();
    }

    public boolean showEmployeeAddDialog() {
        try {
            // Load the fxml file and create a new stage for the dialog
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/employees/view/AddEmployeeDialog.fxml"));
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

    public void showEmployeeDetails(IEmployee employee) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/employees/view/EmployeeDetails.fxml"));
            loader.setController(new EmployeeDetailsController(employee));
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
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
