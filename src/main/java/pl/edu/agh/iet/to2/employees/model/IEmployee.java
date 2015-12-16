package pl.edu.agh.iet.to2.employees.model;

import java.math.BigDecimal;

/**
 * Created by Patrycja on 01.12.2015.
 */
public interface IEmployee {
    long getId();

    String getName();

    String getSurname();

    String getOccupation();

    BigDecimal getSalary();
}
