package pl.edu.agh.iet.to2.employees.model;

import java.math.BigDecimal;

public interface EmployeesModule {

    String getName(long id);
    String getSurname(long id);

    BigDecimal getSalary(long id);
    String occupation(long id);

}