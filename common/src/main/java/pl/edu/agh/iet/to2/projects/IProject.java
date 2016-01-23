package pl.edu.agh.iet.to2.projects;

import pl.edu.agh.iet.to2.employees.IEmployee;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public interface IProject {

    long getId();

    String getName();

    List<IEmployee> getMembers();

    Date getStartDate();

    Date getEndDate();

    BigDecimal getBudget();

}
