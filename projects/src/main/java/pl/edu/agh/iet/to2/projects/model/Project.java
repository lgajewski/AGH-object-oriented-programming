package pl.edu.agh.iet.to2.projects.model;

import pl.edu.agh.iet.to2.teams.ITeam;
import pl.edu.agh.iet.to2.teams.ITeamMember;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.edu.agh.iet.to2.projects.IProject;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Project implements IProject {

    private long id;
    private StringProperty name;
    private ObservableList<ITeam> teams;
    private Map<ITeamMember, String> memberRoleMap;
    private ObjectProperty<Date> startDate;
    private ObjectProperty<Date> endDate;
    private ObjectProperty<BigDecimal> budget;

    public Project() {
        this("", new Date(), new Date(), BigDecimal.ZERO);
    }

    public Project(String name, Date startDate, Date endDate, BigDecimal budget) {
        this.name = new SimpleStringProperty(name);
        this.teams = FXCollections.observableArrayList();
        this.memberRoleMap = new HashMap<>();
        this.startDate = new SimpleObjectProperty<>(startDate);
        this.endDate = new SimpleObjectProperty<>(endDate);
        this.budget = new SimpleObjectProperty<>(budget);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name.getValue();
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public StringProperty getNameProperty(){
        return name;
    }


    public List<ITeam> getTeams() {
        return teams;
    }

    public void setTeams(List<ITeam> teams) {
        this.teams = FXCollections.observableArrayList(teams);
    }

    public Map<ITeamMember, String> getMemberRoleMap() {
        return memberRoleMap;
    }

    public void setMemberRoleMap(Map<ITeamMember, String> memberRoleMap) {
        this.memberRoleMap = memberRoleMap;
    }

    public Date getStartDate() {
        return startDate.getValue();
    }

    public void setStartDate(Date startDate) {
        this.startDate.setValue(startDate);
    }

    public ObjectProperty<Date> getStartDateProperty(){
        return startDate;
    }

    public Date getEndDate() {
        return endDate.getValue();
    }

    public void setEndDate(Date endDate) {
        this.endDate.setValue(endDate);
    }

    public ObjectProperty<Date> getEndDateProperty(){
        return endDate;
    }

    public BigDecimal getBudget() {
        return budget.getValue();
    }

    public void setBudget(BigDecimal budget) {
        this.budget.setValue(budget);
    }

    public ObjectProperty<BigDecimal> getBudgetProperty(){
        return budget;
    }

    public void addTeam(ITeam team) {
        teams.add(team);
    }

    public void removeTeam(ITeam team) {
        teams.remove(team);
    }

    public void addRole(ITeamMember teamMember, String role) {
        memberRoleMap.put(teamMember, role);
    }

    public static Project newProject() {
        return new Project();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (id != project.id) return false;
        if (!name.equals(project.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        return result;
    }
}
