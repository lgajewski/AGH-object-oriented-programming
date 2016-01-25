package pl.edu.agh.iet.to2.teams.db.tables;

/**
 * Created by Iza on 2016-01-25.
 */
public class DbManager {
    private long managerId;
    private long personId;
    private long parentManagerId;

    public DbManager(long managerId, long personId, long parentManagerId) {
        this.managerId = managerId;
        this.personId = personId;
        this.parentManagerId = parentManagerId;
    }

    public long getManagerId() {
        return managerId;
    }

    public long getPersonId() {
        return personId;
    }

    public long getParentManagerId() {
        return parentManagerId;
    }
}
