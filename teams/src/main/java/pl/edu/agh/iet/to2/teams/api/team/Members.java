/*
package pl.edu.agh.iet.to2.teams.api.team;

import javafx.beans.property.SimpleSetProperty;
import javafx.collections.FXCollections;
import pl.edu.agh.iet.to2.teams.api.person.Member;
import pl.edu.agh.iet.to2.teams.api.person.Person;

import java.util.Set;

*/
/**
 * Created by Pan Ciemnosci on 2015-12-15.
 *//*

public class Members{

    private SimpleSetProperty<Member> members;

    public Members(){
        this.members = new SimpleSetProperty<Member>(FXCollections.observableSet());
    }

    public void add(Member p){
        members.add(p);
    }

    public void remove(Member p){
        members.remove(p);
    }

    public void remove(long id){
        for(Person p: members){
            if(p.getId() == id){
                members.remove(p);
            }
        }
    }

    public Set<Member> getMembers() {
        return members.get();
    }

    public void setMembers(Set<Member> members) {
        this.members.clear();
        SimpleSetProperty<Member> newMembers = this.members;
        newMembers.addAll(members);
        this.members.set(newMembers);
    }

    public SimpleSetProperty<Member> getMembersProperty(){
        return members;
    }

}
*/
