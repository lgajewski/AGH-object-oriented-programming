package pl.edu.agh.iet.to2.employees.model;

public enum JobRank {

    INTERN(1),
    JUNIOR(2),
    REGULAR(3),
    SENIOR(4),
    PRINCIPAL(5);

    private int score;

    JobRank(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

}