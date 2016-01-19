package pl.edu.agh.iet.to2.teams.api.person;

import pl.edu.agh.iet.to2.teams.api.team.Members;
import pl.edu.agh.iet.to2.teams.api.team.Team;

import java.util.Set;

/**
 * Created by Pan Ciemnosci on 2015-12-15.
 */
public class TeamManager implements Manager {

    private long id;
    private String name;
    private String position;
    private Members subordinates;
    private Manager superior;

    private Set<Team> teams;
    private Set<Manager> managers;

    public TeamManager(long id, String name, String position, Members subordinates, Manager superior, Set<Team> teams, Set<Manager> managers) {
        this.id = id;
        this.name = name;
        this.superior = superior;
    }

    public Set<Manager> getManagers() {
        return managers;
    }

    public void setManagers(Set<Manager> managers) {
        this.managers = managers;
    }

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPosition() {
        return position;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Members getSubordinates() {
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
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public void addTeam(Team t){
        teams.add(t);
    }

    public void removeTeam(Team t){
        teams.remove(t);
    }


}
