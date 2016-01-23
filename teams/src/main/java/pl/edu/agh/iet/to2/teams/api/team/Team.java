package pl.edu.agh.iet.to2.teams.api.team;

import javafx.beans.property.SimpleObjectProperty;
import pl.edu.agh.iet.to2.teams.api.person.Manager;
import pl.edu.agh.iet.to2.teams.api.person.TeamManager;
import pl.edu.agh.iet.to2.teams.api.project.Project;
import pl.edu.agh.iet.to2.teams.api.project.TeamProject;

/**
 * Created by Pan Ciemnosci on 2015-12-15.
 */
public class Team {
    private long id;
    private String name;
    private Members members;
    //private SimpleObjectProperty<TeamManager> manager;
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
        this.members = new Members();
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

    public Members getMembers(){
        return members;
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
}
