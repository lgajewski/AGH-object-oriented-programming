package pl.edu.agh.iet.to2.teams.src.api.team;

import api.person.Manager;
import api.person.TeamManager;
import api.project.Project;
import api.project.TeamProject;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Created by Pan Ciemnosci on 2015-12-15.
 */
public class Team {
    private long id;
    private String name;

    private SimpleObjectProperty<TeamManager> manager;
    private Members members;
    private SimpleObjectProperty<TeamProject> project;

    private Team(){
        setDefaults();
    }

    public static Team createTeam(){
        return new Team();
    }

    private void setDefaults(){
        this.manager = new SimpleObjectProperty<TeamManager>();
        this.members = new Members();
        this.project = new SimpleObjectProperty<TeamProject>();
    }

    public Manager getManager(){
        return manager.get();
    }

    public void setManager(TeamManager manager){
        this.manager.set(manager);
    }

    public Project getProject() {
        return project.get();
    }

    public void setProject(TeamProject project) {
        this.project.set(project);
    }

    public Members getMembers(){
        return members;
    }

    public SimpleObjectProperty<TeamManager> getManagerProperty(){
        return manager;
    }

    public SimpleObjectProperty<TeamProject> getProjectProperty(){
        return project;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
