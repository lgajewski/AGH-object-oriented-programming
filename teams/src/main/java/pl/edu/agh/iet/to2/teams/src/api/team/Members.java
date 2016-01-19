package pl.edu.agh.iet.to2.teams.src.api.team;

import api.person.Person;
import javafx.beans.property.SimpleSetProperty;
import javafx.collections.FXCollections;

import java.util.Set;

/**
 * Created by Pan Ciemnosci on 2015-12-15.
 */
public class Members{

    private SimpleSetProperty<Person> members;

    public Members(){
        this.members = new SimpleSetProperty<Person>(FXCollections.observableSet());
    }

    public void add(Person p){
        members.add(p);
    }

    public void remove(Person p){
        members.remove(p);
    }

    public void remove(long id){
        for(Person p: members){
            if(p.getId() == id){
                members.remove(p);
            }
        }
    }

    public Set<Person> getMembers() {
        return members.get();
    }

    public void setMembers(Set<Person> members) {
        this.members.clear();
        SimpleSetProperty<Person> newMembers = this.members;
        newMembers.addAll(members);
        this.members.set(newMembers);
    }

    public SimpleSetProperty<Person> getMembersProperty(){
        return members;
    }

}
