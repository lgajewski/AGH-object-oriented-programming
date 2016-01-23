package pl.edu.agh.iet.to2.teams.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
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

    public TeamView(AnchorPane pane) { this.pane = pane; }
    public void initialize() {
        this.tree = new TreeView<CustomTreeObject> (new TreeItem<CustomTreeObject>(new CustomTreeObject(0, "Root")));
        this.tree.setEditable(false);
        this.tree.setShowRoot(false);
        this.tree.setMinSize(200, 400);
        this.tree.setPrefSize(500, 500);
        this.tree.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        this.tree.setCellFactory(new Callback<TreeView<CustomTreeObject>,TreeCell<CustomTreeObject>>(){
            @Override
            public TreeCell<CustomTreeObject> call(TreeView<CustomTreeObject> p) {
                return new CustomTreeCellImpl();
            }
        });
        pane.getChildren().add(this.tree);

    }

    public void redrawTeam(TreeItem<CustomTreeObject> item, int hashcode, Team team)
    {
        if(item.getValue().getHashcode() == hashcode){
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
    }

    public void redrawRoot(TeamManager root){
        this.tree.getRoot().getChildren().clear();
        this.tree.getRoot().getChildren().add(new TreeItem<CustomTreeObject>(new CustomTreeObject(root.hashCode(), root.toString())));
        this.redrawManager(this.tree.getRoot(), root.hashCode(), root);
    }

    public void redrawManager(TreeItem<CustomTreeObject> item, int hashcode, TeamManager manager)
    {
        if(item.getValue().getHashcode() == hashcode){
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
    }

}