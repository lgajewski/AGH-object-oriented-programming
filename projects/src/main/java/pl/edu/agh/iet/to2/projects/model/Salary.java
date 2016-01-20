package pl.edu.agh.iet.to2.projects.model;

import pl.edu.agh.iet.to2.employees.ISalary;

import java.math.BigDecimal;

public class Salary implements ISalary {

    private BigDecimal salary;

    public Salary(BigDecimal salary) {
        this.salary = salary;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Salary salary1 = (Salary) o;

        if (!salary.equals(salary1.salary)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return salary.hashCode();
    }
}

