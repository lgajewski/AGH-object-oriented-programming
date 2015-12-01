package pl.edu.agh.iet.to2.employees.model;

import java.math.BigDecimal;
import java.net.URL;

public class EmployeeDetails {

    private String nickname;
    private URL photoURL;

    private BigDecimal salary;
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

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

}
