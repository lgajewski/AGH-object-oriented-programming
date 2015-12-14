package pl.edu.agh.iet.to2.employees.model;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeDetails {

    private String nickname;
    private URL photoURL;

    private Salary salary;
    private List<Salary> salaryHistory = new ArrayList<>();

    private Occupation occupation;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public URL getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(URL photoURL) {
        this.photoURL = photoURL;
    }

    public void setSalary(BigDecimal value) {
        Salary salary = new Salary();
        salary.setDate(new Date());
        salary.setValue(value);

        salaryHistory.add(salary);
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

}
