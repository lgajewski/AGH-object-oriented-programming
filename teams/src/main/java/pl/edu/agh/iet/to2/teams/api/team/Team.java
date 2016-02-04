package pl.edu.agh.iet.to2.teams.api.team;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.collections.FXCollections;
import pl.edu.agh.iet.to2.teams.api.person.Member;
import pl.edu.agh.iet.to2.teams.api.person.Person;
import pl.edu.agh.iet.to2.teams.api.project.Project;
import pl.edu.agh.iet.to2.teams.api.project.TeamProject;

import java.util.Set;

/**
 * Created by Pan Ciemnosci on 2015-12-15.
 */
public class Team {
    private SimpleLongProperty id;
    private SimpleObjectProperty<String> name;

    private SimpleSetProperty<Member> members;
    private SimpleObjectProperty<TeamProject> project;

    private Team(long id){
        setDefaults(id);
    }

    private Team(long id, String name){
        setDefaults(id);
        this.name.set(name);
    }

    public static Team createTeam(long id){
        return new Team(id);
    }

    public static Team createTeam(long id, String name){
        return new Team(id, name);
    }

    private void setDefaults(long id){
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleObjectProperty<String>("Team " + this.id);
        this.members = new SimpleSetProperty<Member>(FXCollections.observableSet());
        this.project = new SimpleObjectProperty<TeamProject>();
    }


    public Project getProject() {
        return project.get();
    }

    public void setProject(TeamProject project) {
        this.project.set(project);
    }

    public SimpleObjectProperty<TeamProject> getProjectProperty(){
        return project;
    }

    public String getName() {
        return name.get();
    }

    public SimpleObjectProperty<String> getNameProperty(){
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public long getId() {
        return id.get();
    }

    public SimpleLongProperty getIdProperty(){
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String toString(){
        return "Team: " + getName();
    }

    public void add(Member p){
        members.add(p);
    }

    public void remove(Member p){
        members.remove(p);
    }

    public void remove(long id){
        for(Person p: members){
            if(p.getId() == id){
                members.remove(p);
                return;
            }
        }
    }

    public void removeByHashcode(int hashcode){
        for(Person p: members){
            if(p.hashCode() == hashcode){
                members.remove(p);
                return;
            }
        }
    }

    public Set<Member> getMembers() {
        return members.get();
    }

    public void setMembers(Set<Member> members) {
        this.members.clear();
        SimpleSetProperty<Member> newMembers = this.members;
        newMembers.addAll(members);
        this.members.set(newMembers);
    }

    public SimpleSetProperty<Member> getMembersProperty(){
        return members;
    }
}
