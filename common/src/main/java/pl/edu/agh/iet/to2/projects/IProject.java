package pl.edu.agh.iet.to2.projects;

import pl.edu.agh.iet.to2.teams.ITeam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Marcin on 2016-01-12.
 */
public interface IProject {

    long getId();

    String getName();

    List<ITeam> getTeams();

    LocalDate getStartDate();

    LocalDate getEndDate();

    BigDecimal getBudget();

}
