package pl.edu.agh.iet.to2.teams.view;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Created by Pan Ciemnosci on 2016-01-23.
 */
public class ButtonView {

    public AnchorPane pane;
    public VBox buttonPane;



    public Button addTeamButton = new Button("Add team.");
    public Button removeTeamButton = new Button("Remove team.");

    public ButtonView(AnchorPane pane){
        this.pane = pane;
    }

    public void initialize(){
        initButtonPane();
        AnchorPane.setRightAnchor(buttonPane, 10.0);
        pane.getChildren().add(buttonPane);

        buttonPane.getChildren().addAll(this.addTeamButton,
                this.removeTeamButton);

    }

    public void initButtonPane(){
        this.buttonPane = new VBox();
    }

}
