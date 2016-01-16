package pl.edu.agh.iet.to2.teams;

import java.math.BigDecimal;
import java.util.List;

public interface ITeam {

    long getId();
    String getName();
    ITeamMember getLeader();
    List<ITeamMember> getTeamMembers();
    BigDecimal getCost();

}
