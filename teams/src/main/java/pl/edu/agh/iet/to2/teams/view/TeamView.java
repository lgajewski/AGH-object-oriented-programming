package pl.edu.agh.iet.to2.teams.view;

import javafx.scene.Scene;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TeamView {

    public AnchorPane pane;
    public TreeView<CustomTreeObject> tree;

    public TeamView(AnchorPane pane) { this.pane = pane; }
    public void initialize() {
        this.tree = new TreeView<CustomTreeObject> (new TreeItem<CustomTreeObject>(new CustomTreeObject(0, "Root")));
        this.tree.setEditable(false);
        this.tree.setShowRoot(true);
        this.tree.setCellFactory(new Callback<TreeView<CustomTreeObject>,TreeCell<CustomTreeObject>>(){
            @Override
            public TreeCell<CustomTreeObject> call(TreeView<CustomTreeObject> p) {
                return new CustomTreeCellImpl();
            }
        });
        pane.getChildren().add(this.tree);

    }

    public void changeNode(TreeItem<CustomTreeObject> item, int oldHashcode, int newHashcode, String content){
        if(item.getValue().getHashcode() == oldHashcode){
            item.getValue().setHashcode(newHashcode);
            item.getValue().setContent(content);
            return;
        }

        for(TreeItem<CustomTreeObject> i : item.getChildren()){
            changeNode(i, oldHashcode, newHashcode, content);
        }
    }

    public void addNode(TreeItem<CustomTreeObject> item, int oldHashcode, int newHashcode, String content){
        if(item.getValue().getHashcode() == oldHashcode){

            TreeItem<CustomTreeObject> newNode = new TreeItem<CustomTreeObject>(new CustomTreeObject(newHashcode, content));

            newNode.getChildren().addAll(item.getChildren());
            item.getChildren().clear();
            item.getChildren().add(newNode);

            return;
        }

        for(TreeItem<CustomTreeObject> i : item.getChildren()){
            addNode(i, oldHashcode, newHashcode, content);
        }
    }

    public void removeNode(TreeItem<CustomTreeObject> item, TreeItem<CustomTreeObject> parent, int hashcode){
        if(item.getValue().getHashcode() == hashcode){

            parent.getChildren().addAll(item.getChildren());
            item.getChildren().clear();
            parent.getChildren().remove(item);

            return;
        }

        for(TreeItem<CustomTreeObject> i : item.getChildren()){
            removeNode(i, item, hashcode);
        }
    }



}