package pl.edu.agh.iet.to2.employees;

import java.math.BigDecimal;

public interface IEmployee {
    long getId();

    String getName();

    String getSurname();

    String getOccupation();

    BigDecimal getSalary();

}
