package pl.edu.agh.iet.to2.teams.api.person;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.collections.FXCollections;
import pl.edu.agh.iet.to2.teams.api.team.Members;
import pl.edu.agh.iet.to2.teams.api.team.Team;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by Pan Ciemnosci on 2015-12-15.
 */
public class TeamManager implements Manager {

    private long id;
    private SimpleObjectProperty<String> name;
    private SimpleObjectProperty<String> occupation;
    //private Members subordinates;
    //private Manager superior;

    private SimpleSetProperty<Team> teams;
    private SimpleSetProperty<Manager> managers;

    public TeamManager(long id, String name, String occupation) {
        this.id = id;
        this.name = new SimpleObjectProperty<String>(name);
        this.occupation = new SimpleObjectProperty<String>(occupation);
        teams = new SimpleSetProperty<Team>(FXCollections.observableSet());
        managers = new SimpleSetProperty<Manager>(FXCollections.observableSet());
    }

    public TeamManager getTeamManager(){
        return this;
    }

    public Set<Manager> getManagers() {
        return managers.get();
    }

    public SimpleSetProperty<Manager> getManagersProperty() { return this.managers; }

    public Set<Team> getTeams() {
        return this.teams.get();
    }

    public SimpleSetProperty<Team> getTeamsProperty() {
        return this.teams;
    }


    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name.get();
    }

    @Override
    public String getSurname() {
        return null;
    }

    @Override
    public String getOccupation() {
        return null;
    }

    @Override
    public BigDecimal getSalary() {
        return null;
    }

    public SimpleObjectProperty<String> getOccupationProperty() {
        return occupation;
    }

    public SimpleObjectProperty<String> getNameProperty() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setOccupation(String occupation) {
        this.occupation.set(occupation);
    }

   /* public Members getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(Members subordinates) {
        this.subordinates = subordinates;
    }

    public Manager getSuperior() {
        return superior;
    }

    public void setSuperior(Manager superior) {
        this.superior = superior;
    }*/




    /**
     operations on teams added here
     would be better to move them outside the class
     but "meh" for now
     */

    public void addTeam(Team t){
        teams.add(t);
    }

    public void removeTeam(Team t){
        teams.remove(t);
    }

    public void addManager(TeamManager m) {
        managers.add(m);
    }

    public void removeManager(TeamManager m){
        managers.remove(m);
    }

    public String toString(){
        return "Name: " + getName()
                + ", Occupation: " + getOccupation();
    }


}
