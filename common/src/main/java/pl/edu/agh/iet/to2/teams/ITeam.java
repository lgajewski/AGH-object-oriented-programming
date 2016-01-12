package pl.edu.agh.iet.to2.teams;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Marcin on 2016-01-12.
 */
public interface ITeam {

    //TODO!!!
    long getId();
    String getName();
    ITeamMember getLeader();
    List<ITeamMember> getTeamMembers();
    BigDecimal getCost();

}
