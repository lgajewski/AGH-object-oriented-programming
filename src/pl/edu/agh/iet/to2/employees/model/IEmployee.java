package pl.edu.agh.iet.to2.employees.model;

/**
 * Created by Patrycja on 01.12.2015.
 */
public interface IEmployee {

    public long getId();
    public void setId(long id);
    
    public String getName();
    public void setName(String name);

    public String getSurname();
    public void setSurname(String surname);

    public EmployeeDetails getDetails();
    public void setDetails(EmployeeDetails details);
}
