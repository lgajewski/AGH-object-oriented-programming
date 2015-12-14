package pl.edu.agh.iet.to2.employees.model;

import java.math.BigDecimal;
import java.util.Date;

public class Salary {

    private Date date;
    private BigDecimal value;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
