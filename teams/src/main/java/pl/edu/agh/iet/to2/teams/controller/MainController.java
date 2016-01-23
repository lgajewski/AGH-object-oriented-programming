package pl.edu.agh.iet.to2.teams.controller;

import javafx.scene.layout.AnchorPane;
import pl.edu.agh.iet.to2.teams.view.ButtonView;
import pl.edu.agh.iet.to2.teams.view.ComponentView;
import pl.edu.agh.iet.to2.teams.view.TeamView;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Created by maciek on 21.01.16.
 */
public class MainController {

    private HashSet<SubController> controllers;
    private AnchorPane pane;
    private HashMap<String, ComponentView> allViews;

    public MainController(HashMap<String, ComponentView> allViews, AnchorPane pane){
        this.allViews = allViews;
        this.pane = pane;
        this.initialize();
    }

    public ComponentView getView(String viewName) {
        if(allViews.containsKey(viewName)){
            return allViews.get(viewName);
        } else {
            throw new NoSuchElementException("There is no View of name: " + viewName);
        }

    }

    public void setView(String viewName, ComponentView view) {
        if(allViews.containsKey(viewName)){
            allViews.replace(viewName, view);
        } else {
            throw new NoSuchElementException("There is no View of name: " + viewName);
        }
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
