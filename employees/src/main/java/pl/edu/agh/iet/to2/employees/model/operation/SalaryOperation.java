package pl.edu.agh.iet.to2.employees.model.operation;

import java.util.Date;

public class SalaryOperation<BigDecimal> implements IOperation {

    private Date date;
    private BigDecimal value;

    public SalaryOperation(BigDecimal value, Date date) {
        this.value = value;
        this.date = date;
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }

    @Override
    public Date getDate() {
        return date;
    }
}
