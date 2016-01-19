package pl.edu.agh.iet.to2.teams.view;

import javafx.scene.Scene;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MainView {

    public Stage primaryStage;
    public TreeView<String> tree;

    public MainView(Stage primaryStage) { this.primaryStage = primaryStage; }
    public void initialize() {

        primaryStage.setTitle("Zespoły");

        this.tree = new TreeView<String> ();
        this.tree.setEditable(true);
        this.tree.setCellFactory(new Callback<TreeView<String>,TreeCell<String>>(){
            @Override
            public TreeCell<String> call(TreeView<String> p) {
                return new CustomTreeCellImpl();
            }
        });
        StackPane root = new StackPane();
        root.getChildren().add(this.tree);
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();

    }

}