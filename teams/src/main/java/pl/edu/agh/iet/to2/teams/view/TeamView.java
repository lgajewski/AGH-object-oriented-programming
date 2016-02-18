package pl.edu.agh.iet.to2.teams.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import pl.edu.agh.iet.to2.teams.api.person.Manager;
import pl.edu.agh.iet.to2.teams.api.person.Person;
import pl.edu.agh.iet.to2.teams.api.person.TeamManager;
import pl.edu.agh.iet.to2.teams.api.team.Team;

public class TeamView implements ComponentView{

    public AnchorPane pane;
    public TreeView<CustomTreeObject> tree;
    public ContextMenu contextMenu;
    private DialogView dialogView;

    public TeamView(AnchorPane pane, DialogView dialogView) {
        this.pane = pane;
        this.dialogView = dialogView;
    }

    public void initialize() {
        this.tree = new TreeView<CustomTreeObject> (new TreeItem<CustomTreeObject>(new CustomTreeObject(0, "Root")));
        this.tree.setEditable(false);
        this.tree.setShowRoot(false);
        this.tree.setMinSize(200, 400);
        this.tree.setPrefSize(699, 599);
        this.tree.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        this.tree.setCellFactory(new Callback<TreeView<CustomTreeObject>, TreeCell<CustomTreeObject>>() {
            @Override
            public TreeCell<CustomTreeObject> call(TreeView<CustomTreeObject> p) {
                return new CustomTreeCellImpl();
            }
        });

        contextMenu = new ContextMenu();

        this.tree.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                contextMenu.hide();
                if (event.getButton() == MouseButton.SECONDARY) {
                    if(tree.getRoot().getChildren().isEmpty())
                        contextMenu.getItems().get(0).setVisible(true); // add manager
                    contextMenu.show(tree, event.getScreenX(), event.getScreenY());
                }
            }
        });

        pane.getChildren().add(this.tree);
    }

    public DialogView getDialogView(){
        return dialogView;
    }

    public void redrawTeam(TreeItem<CustomTreeObject> item, int hashcode, Team team)
    {
        if(item.getValue().getHashcode() == hashcode){
            item.getValue().setContent(team.toString());
            item.getChildren().clear();
            for(Person p : team.getMembers())
            {
                item.getChildren().add(new TreeItem<CustomTreeObject>(new CustomTreeObject(p.hashCode(), p.toString())));
            }
            return;
        }

        for(TreeItem<CustomTreeObject> i : item.getChildren()){
           redrawTeam(i, hashcode, team);
        }

        this.tree.refresh();
    }

    public void redrawRoot(TeamManager root){
        this.tree.getRoot().getChildren().clear();
        if(root != null){
            this.tree.getRoot().getChildren().add(new TreeItem<CustomTreeObject>(new CustomTreeObject(root.hashCode(), root.toString())));
            this.redrawManager(this.tree.getRoot(), root.hashCode(), root);
        }

        this.tree.refresh();
    }

    public void redrawManager(TreeItem<CustomTreeObject> item, int hashcode, TeamManager manager)
    {
        if(item.getValue().getHashcode() == hashcode){
            item.getValue().setContent(manager.toString());
            item.getChildren().clear();
            for(Team t : manager.getTeams())
            {
                item.getChildren().add(new TreeItem<CustomTreeObject>(new CustomTreeObject(t.hashCode(), t.toString())));
                this.redrawTeam(item, t.hashCode(), t);
            }
            for(Manager m : manager.getManagers())
            {
                item.getChildren().add(new TreeItem<CustomTreeObject>(new CustomTreeObject(m.hashCode(), m.toString())));
                this.redrawManager(item, m.hashCode(), m.getTeamManager());
            }
            return;
        }

        for(TreeItem<CustomTreeObject> i : item.getChildren()){
            redrawManager(i, hashcode, manager);
        }

        this.tree.refresh();
    }

    public void redrawPerson(TreeItem<CustomTreeObject> item, int hashcode, Person person)
    {
        if(item.getValue().getHashcode() == hashcode){
            item.getValue().setContent(person.toString());
            return;
        }

        for(TreeItem<CustomTreeObject> i : item.getChildren()){
            redrawPerson(i, hashcode, person);
        }

        this.tree.refresh();
    }

}