package pl.edu.agh.iet.to2.teams.api.team;

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
    private long id;
    private String name;

    private SimpleSetProperty<Member> members;
    private SimpleObjectProperty<TeamProject> project;

    private Team(long id){
        setDefaults(id);
    }

    public static Team createTeam(long id){
        return new Team(id);
    }

    private void setDefaults(long id){
       // this.manager = new SimpleObjectProperty<TeamManager>();
        this.id = id;
        this.setName("Team " + this.id);
        this.members = new SimpleSetProperty<Member>(FXCollections.observableSet());
        this.project = new SimpleObjectProperty<TeamProject>();
    }

   /* public Manager getManager(){
        return manager.get();
    }

    public void setManager(TeamManager manager){
        this.manager.set(manager);
    }*/

    public Project getProject() {
        return project.get();
    }

    public void setProject(TeamProject project) {
        this.project.set(project);
    }

   /* public SimpleObjectProperty<TeamManager> getManagerProperty(){
        return manager;
    }*/

    public SimpleObjectProperty<TeamProject> getProjectProperty(){
        return project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
