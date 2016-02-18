package pl.edu.agh.iet.to2.teams.view;

import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Created by maciek on 03.02.16.
 */
public class DialogView implements ComponentView {

    private Stage dialog;
    private VBox allVBox;
    private HBox tipHBox;
    private Label tip;
    private HBox dialogHBox;
    private VBox labelVBox;
    private VBox textFieldVBox;
    private TextField idField;
    private TextField nameField;
    private TextField occupationField;
    private Scene dialogScene;

    public DialogView() { }

    public void initialize(){
        dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        //dialog.initOwner(view.pane.getsta);

        labelVBox = new VBox(20);
        labelVBox.getChildren().addAll(new HBox(new Text("ID:")), new HBox(new Text("Name:")), new HBox(new Text("Occupation:")));
        for(Node hb : labelVBox.getChildren()){
            ((HBox) hb).setAlignment(Pos.CENTER_RIGHT);
            ((HBox) hb).setPrefHeight(26.0);
        }
        labelVBox.setAlignment(Pos.CENTER);

        textFieldVBox = new VBox(20);
        idField = new TextField("");
        nameField = new TextField("");
        occupationField = new TextField("");
        textFieldVBox.getChildren().addAll(idField, nameField, occupationField);
        textFieldVBox.setAlignment(Pos.CENTER);
       // nameField.text

        dialogHBox = new HBox(20);
        dialogHBox.getChildren().addAll(labelVBox, textFieldVBox);
        dialogHBox.setAlignment(Pos.CENTER);

        tipHBox = new HBox(20);
        tip = new Label("Close dialog to submit");
        tip.setStyle("-fx-font-weight: bold");
        tipHBox.getChildren().add(tip);
        tipHBox.setAlignment(Pos.CENTER);

        allVBox = new VBox(20);
        allVBox.getChildren().addAll(dialogHBox, tipHBox);
        allVBox.setAlignment(Pos.CENTER);

        dialogScene = new Scene(allVBox, 300, 200);
        dialog.setScene(dialogScene);

        dialog.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                getIdFieldTextProperty().unbindBidirectional(getIdFieldTextProperty());
            }
        });
        //this.show();
    }

    public void setOnHiding(EventHandler<WindowEvent> value){
        dialog.setOnHiding(value);
    }

    public void show(){
        dialog.show();
    }

    public void hide(){
        dialog.hide();
    }

    public void enableId(){
        idField.setDisable(false);
    }

    public void enableName(){
        nameField.setDisable(false);
    }

    public void enableOccupation(){
        occupationField.setDisable(false);
    }

    public void disableId(){
        idField.setDisable(true);
    }

    public void disableName(){
        nameField.setDisable(true);
    }

    public void disableOccupation(){
        occupationField.setDisable(true);
    }

    public void setTitle(String title){
        dialog.setTitle(title);
    }

    public StringProperty getIdFieldTextProperty(){
        return this.idField.textProperty();
    }

    public StringProperty getNameFieldTextProperty(){
        return this.nameField.textProperty();
    }

    public StringProperty getOccupationFieldTextProperty(){
        return this.occupationField.textProperty();
    }
}
