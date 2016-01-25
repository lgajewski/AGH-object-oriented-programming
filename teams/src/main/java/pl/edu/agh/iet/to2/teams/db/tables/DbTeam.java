package pl.edu.agh.iet.to2.teams.db.tables;

/**
 * Created by Iza on 2016-01-25.
 */
public class DbTeam {
    private long teamId;
    private String name;
    private long managerId;

    public DbTeam(long teamId, String name, long managerId) {
        this.teamId = teamId;
        this.name = name;
        this.managerId = managerId;
    }

    public long getTeamId() {
        return teamId;
    }

    public String getName() {
        return name;
    }

    public long getManagerId() {
        return managerId;
    }
}
