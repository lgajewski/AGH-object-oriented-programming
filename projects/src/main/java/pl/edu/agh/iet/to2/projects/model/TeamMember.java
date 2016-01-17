package pl.edu.agh.iet.to2.projects.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import pl.edu.agh.iet.to2.employees.IEmployee;
import pl.edu.agh.iet.to2.teams.ITeam;
import pl.edu.agh.iet.to2.teams.ITeamMember;

/**
 * Created by Marcin on 2015-12-08.
 */
public class TeamMember implements ITeamMember {

    private ObjectProperty<ITeam> teamProperty;
    private IEmployee employee;

    public TeamMember(ITeam team, IEmployee employee) {
        this.teamProperty = new SimpleObjectProperty<ITeam>(team);
        this.employee = employee;
    }

    public ITeam getTeam() {
        return teamProperty.get();
    }

    public void setTeam(Team team) {
        teamProperty.set(team);
    }

    public IEmployee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public ObjectProperty<ITeam> getTeamProperty() {
        return teamProperty;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamMember that = (TeamMember) o;

        if (!employee.equals(that.employee)) return false;
        if (!teamProperty.equals(that.teamProperty)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = teamProperty.hashCode();
        result = 31 * result + employee.hashCode();
        return result;
    }
}
