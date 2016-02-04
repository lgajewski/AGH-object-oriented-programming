package pl.edu.agh.iet.to2.teams.api.person;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.collections.FXCollections;
import pl.edu.agh.iet.to2.teams.api.team.Team;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by Pan Ciemnosci on 2015-12-15.
 */
public class TeamManager implements Manager {

    private SimpleLongProperty id;
    private SimpleObjectProperty<String> name;
    private SimpleObjectProperty<String> occupation;
    //private Members subordinates;
    //private Manager superior;

    private SimpleSetProperty<Team> teams;
    private SimpleSetProperty<Manager> managers;

    public TeamManager(long id, String name, String occupation) {
        this.id = new SimpleLongProperty(id);
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

    public SimpleLongProperty getIdProperty(){
        return this.id;
    }

    @Override
    public long getId() {
        return this.id.get();
    }

    @Override
    public String getName() {
        return this.name.get();
    }

    @Override
    public String getSurname() {
        return null;
    }

    @Override
    public String getOccupation() {
        return this.occupation.get();
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
        this.id.set(id);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setOccupation(String occupation) {
        this.occupation.set(occupation);
    }


    public void addTeam(Team t){
        teams.add(t);
    }

    public void removeTeam(Team t){
        teams.remove(t);
    }

    public void removeTeam(long id){
        for(Team t : this.getTeams()){
            if(t.getId() == id){
                this.teams.remove(t);
                return;
            }
        }
    }

    public void removeTeamByHashcode(int hashcode){
        for(Team t : this.getTeams()){
            if(t.hashCode() == hashcode) {
                this.teams.remove(t);
                return;
            }
        }
    }

    public void addManager(TeamManager m) {
        managers.add(m);
    }

    public void removeManager(TeamManager m){
        managers.remove(m);
    }

    public void removeManager(long id){
        for(Manager m : this.getManagers()){
            if(m.getId() == id) {
                this.managers.remove(m);
                return;
            }
        }
    }

    public void removeManagerByHashcode(int hashcode){
        for(Manager m : this.getManagers()){
            if(m.hashCode() == hashcode) {
                this.managers.remove(m);
                return;
            }
        }
    }

    public String toString(){
        return "Name: " + getName()
                + ", Occupation: " + getOccupation();
    }


}
