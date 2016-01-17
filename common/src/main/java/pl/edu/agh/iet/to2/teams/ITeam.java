package pl.edu.agh.iet.to2.teams;

import java.math.BigDecimal;
import java.util.List;

public interface ITeam {

    long getId();
    void setId(long id);
    String getName();
    void setName(String name);
    ITeamMember getLeader();
    List<ITeamMember> getTeamMembers();
    BigDecimal getCost();

}
