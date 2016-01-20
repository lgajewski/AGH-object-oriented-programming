package pl.edu.agh.iet.to2.teams.dummy;

import pl.edu.agh.iet.to2.teams.ITeam;
import pl.edu.agh.iet.to2.teams.ITeamMember;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DummyTeam implements ITeam {

    long id;
    String name;
    List<ITeamMember> teamMembers = new ArrayList<ITeamMember>();
    ITeamMember leader = null;

    public DummyTeam() {
    }

    public DummyTeam(String name) {
        this.name = name;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public ITeamMember getLeader() {
        return leader;
    }

    @Override
    public List<ITeamMember> getTeamMembers() {
        return teamMembers;
    }

    @Override
    public BigDecimal getCost() {
        return null;
    }
}
