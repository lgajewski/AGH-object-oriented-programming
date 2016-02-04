package pl.edu.agh.iet.to2.teams.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Created by Pan Ciemnosci on 2016-01-23.
 */
public class ButtonView implements ComponentView{

    public AnchorPane pane;
    public VBox buttonPane;

    public Button addTeamButton = new Button("Add team.");
    public Button removeTeamButton = new Button("Remove team.");
    public Button addOrRemovePersonButton = new Button("Add/Remove person");
    public Button showTeamsButton = new Button("Display teams");
    public Button tellMaciekJavaIsNotC = new Button("Load object oriented approach into Maciek.c");

    public ButtonView(AnchorPane pane){
        this.pane = pane;
    }

    public void initialize(){
        initButtonPane();
        AnchorPane.setRightAnchor(buttonPane, 10.0);
        pane.getChildren().add(buttonPane);

        buttonPane.getChildren().addAll(this.addTeamButton,
                this.removeTeamButton,
                this.addOrRemovePersonButton,
                this.showTeamsButton,
                this.tellMaciekJavaIsNotC);

    }

    public void initButtonPane(){
        this.buttonPane = new VBox();
    }

}
