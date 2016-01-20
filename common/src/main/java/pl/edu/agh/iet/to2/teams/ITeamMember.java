package pl.edu.agh.iet.to2.teams;

import javafx.beans.property.ObjectProperty;
import pl.edu.agh.iet.to2.employees.IEmployee;

/**
 * Created by Marcin on 2016-01-12.
 */
public interface ITeamMember {

    ITeam getTeam();
    IEmployee getEmployee();
    ObjectProperty getTeamProperty();

}

