package pl.edu.agh.iet.to2.teams.controller;

import javafx.scene.layout.AnchorPane;
import pl.edu.agh.iet.to2.teams.view.ButtonView;
import pl.edu.agh.iet.to2.teams.view.ComponentView;
import pl.edu.agh.iet.to2.teams.view.TeamView;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by maciek on 21.01.16.
 */
public class MainController {

    private HashSet<SubController> controllers;
//    private TeamView teamView;
//    private ButtonView buttonView;
    private AnchorPane pane;
    private HashMap<String, ComponentView> allViews;

    public MainController(HashMap<String, ComponentView> allViews, AnchorPane pane){
        this.allViews = allViews;
        this.pane = pane;
        this.initialize();
    }

    public ComponentView getView(String viewName) {
        return allViews.get(viewName);
    }

    public void setView(String viewName, ComponentView view) {
        allViews.replace(viewName, view);
    }

    public AnchorPane getPane() {
        return pane;
    }

    public void setPane(AnchorPane pane) {
        this.pane = pane;
    }

    public HashSet<SubController> getControllers(){
        return controllers;
    }

    public void initialize() {
        controllers = new HashSet<SubController>();
        // code to init views
        for(String view: allViews.keySet()){
            allViews.get(view).initialize();
        }
    }

    public void addController(SubController controller){
        controllers.add(controller);
    }

    public void removeController(SubController controller){
        controllers.remove(controller);
    }

    public void removeControllerByHashcode(int childHashcode){
        for(SubController sc : controllers){
            if(sc.getChildHashcode() == childHashcode) {
                controllers.remove(sc);
                return;
            }
        }
    }
}
