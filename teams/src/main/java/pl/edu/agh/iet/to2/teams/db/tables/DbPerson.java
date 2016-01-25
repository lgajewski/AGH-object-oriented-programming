package pl.edu.agh.iet.to2.teams.db.tables;

/**
 * Created by Iza on 2016-01-25.
 */
public class DbPerson {
    private long personId;
    private String name;
    private String position;
    private String date;

    public DbPerson(long personId, String name, String position, String date) {
        this.personId = personId;
        this.name = name;
        this.position = position;
        this.date = date;
    }

    public long getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getDate() {
        return date;
    }
}
