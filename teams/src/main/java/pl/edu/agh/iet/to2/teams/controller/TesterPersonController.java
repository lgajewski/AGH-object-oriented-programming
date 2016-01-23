package pl.edu.agh.iet.to2.teams.controller;

import javafx.scene.layout.AnchorPane;
import pl.edu.agh.iet.to2.teams.api.person.Person;
import pl.edu.agh.iet.to2.teams.api.person.TeamManager;
import pl.edu.agh.iet.to2.teams.api.person.TesterPerson;
import pl.edu.agh.iet.to2.teams.view.ComponentView;
import pl.edu.agh.iet.to2.teams.view.TeamView;

/**
 * Created by maciek on 23.01.16.
 */
public class TesterPersonController implements SubController{

    private int childHashcode;
    private long childId;
    private TesterPerson testerPerson;
    private AnchorPane pane;
    private TeamView view;


    public long getChildId() {
        return childId;
    }

    public void setChildId(long childId) {
        this.childId = childId;
    }

    public int getChildHashcode() {
        return childHashcode;
    }

    public void setChildHashcode(int childHashcode) {
        this.childHashcode = childHashcode;
    }

    public TeamView getView() {
        return view;
    }

    public void setView(ComponentView view) {
        this.view = (TeamView) view;
    }

    public TesterPerson getTesterPerson() {
        return this.testerPerson;
    }

    public Person getPerson(){
        return this.testerPerson;
    }

    public void setTesterPerson(TesterPerson testerPerson) {
        this.testerPerson = testerPerson;
    }

    public AnchorPane getPane() {
        return pane;
    }

    public void setPane(AnchorPane pane) {
        this.pane = pane;
    }


    public void initialize(){
        this.testerPerson.getNameProperty().addListener((o, oldVal, newVal) -> {
            view.redrawPerson(view.tree.getRoot(), this.testerPerson.hashCode(), this.testerPerson);
        });

        this.testerPerson.getOccupationProperty().addListener((o, oldVal, newVal) -> {
            view.redrawPerson(view.tree.getRoot(), this.testerPerson.hashCode(), this.testerPerson);
        });
    }

    private TesterPersonController(TesterPerson testerPerson, AnchorPane pane, TeamView view){
        this.pane = pane;
        this.testerPerson = testerPerson;
        this.childHashcode = testerPerson.hashCode();
        this.view = view;
    }

    public static TesterPersonController createControllerOn(TesterPerson testerPerson, AnchorPane pane, TeamView view){
        TesterPersonController tpc = new TesterPersonController(testerPerson, pane, view);
        tpc.initialize();
        return tpc;
    }


}
