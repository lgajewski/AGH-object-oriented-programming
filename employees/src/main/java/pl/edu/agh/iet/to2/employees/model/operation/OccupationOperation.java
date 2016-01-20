package pl.edu.agh.iet.to2.employees.model.operation;

import java.util.Date;

public class OccupationOperation<String> implements IOperation {
    private Date date;
    private String value;

    public OccupationOperation(String value, Date date) {
        this.value = value;
        this.date = date;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public Date getDate() {
        return date;
    }
}
