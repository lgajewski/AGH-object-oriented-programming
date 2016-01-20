package pl.edu.agh.iet.to2.teams.dummy;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import pl.edu.agh.iet.to2.employees.IEmployee;
import pl.edu.agh.iet.to2.teams.ITeam;
import pl.edu.agh.iet.to2.teams.ITeamMember;

/**
 * Created by Marcin on 2016-01-20.
 */
public class DummyTeamMember implements ITeamMember {

    private ObjectProperty<ITeam> teamProperty;
    private IEmployee employee;
    private ITeam team;

    public DummyTeamMember(ITeam team, IEmployee employee) {
        this.teamProperty = new SimpleObjectProperty<ITeam>(team);
        this.employee = employee;
    }

    public ITeam getTeam() {
        return teamProperty.get();
    }

    public void setTeam(ITeam team) {
        teamProperty.set(team);
    }

    public IEmployee getEmployee() {
        return employee;
    }

    public void setEmployee(IEmployee employee) {
        this.employee = employee;
    }

    public ObjectProperty<ITeam> getTeamProperty() {
        return teamProperty;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DummyTeamMember that = (DummyTeamMember) o;

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
