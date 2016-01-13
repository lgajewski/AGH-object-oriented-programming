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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Project implements IProject{

    private long id;
    private StringProperty name;
    private ObservableList<ITeam> teams;
    private Map<ITeamMember, String> memberRoleMap;
    private ObjectProperty<LocalDate> startDate;
    private ObjectProperty<LocalDate> endDate;
    private ObjectProperty<BigDecimal> budget;

    public Project() {
        this(0, "", LocalDate.now(), LocalDate.now(), BigDecimal.ZERO);
    }

    public Project(long id, String name, LocalDate startDate, LocalDate endDate, BigDecimal budget) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.teams = FXCollections.observableArrayList();
        this.memberRoleMap = new HashMap<>();
        this.startDate = new SimpleObjectProperty<>(startDate);
        this.endDate = new SimpleObjectProperty<>(endDate);
        this.budget = new SimpleObjectProperty<>(budget);
    }

    @Id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name="name")
    public String getName() {
        return name.getValue();
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public StringProperty getNameProperty(){
        return name;
    }

    @OneToMany
    public List<ITeam> getTeams() {
        return teams;
    }

    public void setTeams(ObservableList<ITeam> teams) {
        this.teams = teams;
    }

    public Map<ITeamMember, String> getMemberRoleMap() {
        return memberRoleMap;
    }

    public void setMemberRoleMap(Map<ITeamMember, String> memberRoleMap) {
        this.memberRoleMap = memberRoleMap;
    }

    public LocalDate getStartDate() {
        return startDate.getValue();
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate.setValue(startDate);
    }

    public ObjectProperty<LocalDate> getStartDateProperty(){
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate.getValue();
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate.setValue(endDate);
    }

    public ObjectProperty<LocalDate> getEndDateProperty(){
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
