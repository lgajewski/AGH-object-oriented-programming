package pl.edu.agh.iet.to2.teams.dummy;

import pl.edu.agh.iet.to2.teams.ITeam;
import pl.edu.agh.iet.to2.teams.ITeamMember;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DummyTeam implements ITeam{
    @Override
    public long getId() {
        return 0;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public ITeamMember getLeader() {
        return null;
    }

    @Override
    public List<ITeamMember> getTeamMembers() {
        return new ArrayList<>();
    }

    @Override
    public BigDecimal getCost() {
        return null;
    }
}
