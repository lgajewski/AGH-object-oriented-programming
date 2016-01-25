package pl.edu.agh.iet.to2.teams.db.tables;

/**
 * Created by Iza on 2016-01-25.
 */
public class DbMember {
    private long memberId;
    private long personId;
    private long teamId;

    public DbMember(long memberId, long personId, long teamId) {
        this.memberId = memberId;
        this.personId = personId;
        this.teamId = teamId;
    }

    public long getMemberId() {
        return memberId;
    }

    public long getPersonId() {
        return personId;
    }

    public long getTeamId() {
        return teamId;
    }
}
