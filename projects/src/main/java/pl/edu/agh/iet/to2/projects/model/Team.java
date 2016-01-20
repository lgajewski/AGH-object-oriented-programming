package pl.edu.agh.iet.to2.projects.model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.edu.agh.iet.to2.teams.ITeam;
import pl.edu.agh.iet.to2.teams.ITeamMember;

import java.math.BigDecimal;
import java.util.List;


public class Team implements ITeam {

    private LongProperty id;
    private StringProperty name;
    private ObjectProperty<ITeamMember> leader;
    private ObservableList<ITeamMember> teamMembers;

    public Team() {
        this.id = new SimpleLongProperty();
        this.name = new SimpleStringProperty();
        this.leader = new SimpleObjectProperty<>();
        this.teamMembers = FXCollections.observableArrayList();
    }

    public Team(long id, String name, ITeamMember leader, List<ITeamMember> teamMembers) {
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.leader = new SimpleObjectProperty<>(leader);
        this.teamMembers = FXCollections.observableArrayList();
    }

    public long getId() {
        return id.getValue();
    }

    public LongProperty getIdProperty() {
        return id;
    }

    public void setId(long teamId) {
        this.id.set(teamId);
    }

    public String getName() {
        return name.getValue();
    }

    public StringProperty getNameProperty() {
        return name;
    }

    public void setName(String teamName) {
        this.name.set(teamName);
    }

    public ITeamMember getLeader() {
        return leader.getValue();
    }

    public ObjectProperty<ITeamMember> getLeaderProperty() {
        return leader;
    }

    public void setLeader(ITeamMember leader) {
        this.leader.set(leader);
    }

    public ObservableList<ITeamMember> getTeamMembers() {
        return teamMembers;
    }

    public void addTeamMember(ITeamMember teamMember) {
        this.teamMembers.add(teamMember);
    }

    public BigDecimal getCost() {
        BigDecimal result = new BigDecimal(0);
        for (ITeamMember m : teamMembers) {
            result.add(m.getEmployee().getSalary());
        }
        return result;
    }


}

