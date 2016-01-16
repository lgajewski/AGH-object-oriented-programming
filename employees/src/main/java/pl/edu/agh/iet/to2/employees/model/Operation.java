package pl.edu.agh.iet.to2.employees.model;

import java.util.Date;

public class Operation<T> {

    private T value;
    private Date date;

    public Operation(T value, Date date) {
        this.value = value;
        this.date = date;
    }

    public T getValue() {
        return value;
    }

    public Date getDate() {
        return date;
    }
}
