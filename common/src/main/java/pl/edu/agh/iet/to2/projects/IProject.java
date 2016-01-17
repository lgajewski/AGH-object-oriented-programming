package pl.edu.agh.iet.to2.projects;

import pl.edu.agh.iet.to2.teams.ITeam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public interface IProject {

    long getId();

    String getName();

    List<ITeam> getTeams();

    Date getStartDate();

    Date getEndDate();

    BigDecimal getBudget();

}
