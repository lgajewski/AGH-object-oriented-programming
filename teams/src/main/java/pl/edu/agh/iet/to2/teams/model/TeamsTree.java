package pl.edu.agh.iet.to2.teams.model;

import javafx.beans.property.SimpleObjectProperty;
import pl.edu.agh.iet.to2.teams.api.person.Manager;
import pl.edu.agh.iet.to2.teams.api.person.Member;
import pl.edu.agh.iet.to2.teams.api.person.TeamManager;
import pl.edu.agh.iet.to2.teams.api.team.Team;

/**
 * Created by maciek on 21.01.16.
 */

public class TeamsTree {

    private SimpleObjectProperty<TeamManager> root;

    public TeamsTree(){
        root = new SimpleObjectProperty<TeamManager>(null);
    }

    public void setRoot(TeamManager teamManager){
        if(rootExists()){
            teamManager.addManager(root.getValue());
            root.setValue(teamManager);
        }
        else
            root.setValue(teamManager);
    }

    public void deleteRoot(){
        root.setValue(null);
    }

    public TeamManager getRoot(){
        return root.get();
    }

    public SimpleObjectProperty<TeamManager> getRootProperty(){
        return this.root;
    }

    public boolean rootExists(){
        if(root.get() == null)
            return false;
        else
            return true;
    }

    public TeamManager findTeamManager(long id){
        if(this.rootExists())
            return innerFindTeamManager(id, this.getRoot());
        else
            return null;
    }

    private TeamManager innerFindTeamManager(long id, TeamManager node) {
        TeamManager result = null;

        if(node == null)
            return null;
        else{
            if(node.getId() == id)
                return node;
            for(Manager tm : node.getManagers()){
                    result = innerFindTeamManager(id, tm.getTeamManager());
                    if(result != null)
                        return result;
            }
            return result;
        }
    }

    public TeamManager findTeamManagerByHashcode(int hashcode){
        if(this.rootExists())
            return innerFindTeamManagerByHashcode(hashcode, this.getRoot());
        else
            return null;
    }

    private TeamManager innerFindTeamManagerByHashcode(int hashcode, TeamManager node) {
        TeamManager result = null;

        if(node == null)
            return null;
        else{
            if(node.hashCode() == hashcode)
                return node;
            for(Manager tm : node.getManagers()){
                result = innerFindTeamManagerByHashcode(hashcode, tm.getTeamManager());
                if(result != null)
                    return result;
            }
            return result;
        }
    }

    public Team findTeam(long id){
        if(this.rootExists())
            return innerFindTeam(id, this.getRoot());
        else
            return null;
    }

    private Team innerFindTeam(long id, TeamManager node){
        Team result = null;

        if(node == null)
            return null;
        else{
            for(Team t : node.getTeams()){
                if(t.getId() == id)
                    return t;
            }

            for(Manager tm : node.getManagers()){
                result = innerFindTeam(id, tm.getTeamManager());
                if(result != null)
                    return result;
            }
            return result;
        }
    }

    public Team findTeamByHashcode(int hashcode){
        if(this.rootExists())
            return innerFindTeamByHashcode(hashcode, this.getRoot());
        else
            return null;
    }

    private Team innerFindTeamByHashcode(int hashcode, TeamManager node){
        Team result = null;

        if(node == null)
            return null;
        else{
            for(Team t : node.getTeams()){
                if(t.hashCode() == hashcode)
                    return t;
            }

            for(Manager tm : node.getManagers()){
                result = innerFindTeamByHashcode(hashcode, tm.getTeamManager());
                if(result != null)
                    return result;
            }
            return result;
        }
    }

    public TeamManager findManagerOfTeam(long id){
        if(this.rootExists())
            return innerFindManagerOfTeam(id, this.getRoot());
        else
            return null;
    }

    private TeamManager innerFindManagerOfTeam(long id, TeamManager node){
        TeamManager result = null;

        if(node == null)
            return null;
        else{
            for(Team t : node.getTeams()){
                if(t.getId() == id){
                    return node;
                }
            }
            for(Manager tm : node.getManagers()){
                result = innerFindManagerOfTeam(id, tm.getTeamManager());
                if(result != null)
                    return result;
            }
            return result;
        }
    }

    public TeamManager findManagerOfTeamByHashcode(int hashcode){
        if(this.rootExists())
            return innerFindManagerOfTeamByHashcode(hashcode, this.getRoot());
        else
            return null;
    }

    private TeamManager innerFindManagerOfTeamByHashcode(int hashcode, TeamManager node){
        TeamManager result = null;

        if(node == null)
            return null;
        else{
            for(Team t : node.getTeams()){
                if(t.hashCode() == hashcode){
                    return node;
                }
            }
            for(Manager tm : node.getManagers()){
                result = innerFindManagerOfTeamByHashcode(hashcode, tm.getTeamManager());
                if(result != null)
                    return result;
            }
            return result;
        }
    }

    public TeamManager findSuperiorOfManager(long id){
        if(this.rootExists())
            return innerFindSuperiorOfManager(id, this.getRoot());
        else
            return null;
    }

    private TeamManager innerFindSuperiorOfManager(long id, TeamManager node){
        TeamManager result = null;

        if(node == null)
            return null;
        else{
            for(Manager m : node.getManagers()){
                if(m.getId() == id){
                    return node;
                }
            }
            for(Manager tm : node.getManagers()){
                result = innerFindSuperiorOfManager(id, tm.getTeamManager());
                if(result != null)
                    return result;
            }
            return result;
        }
    }

    public TeamManager findSuperiorOfManagerByHashcode(int hashcode){
        if(this.rootExists())
            return innerFindSuperiorOfManagerByHashcode(hashcode, this.getRoot());
        else
            return null;
    }

    private TeamManager innerFindSuperiorOfManagerByHashcode(int hashcode, TeamManager node){
        TeamManager result = null;

        if(node == null)
            return null;
        else{
            for(Manager m : node.getManagers()){
                if(m.hashCode() == hashcode){
                    return node;
                }
            }
            for(Manager tm : node.getManagers()){
                result = innerFindSuperiorOfManagerByHashcode(hashcode, tm.getTeamManager());
                if(result != null)
                    return result;
            }
            return result;
        }
    }

    public Member findMember(long id){
        if(this.rootExists())
            return innerFindMember(id, this.getRoot());
        else
            return null;
    }

    private Member innerFindMember(long id, TeamManager node){
        Member result = null;

        if(node == null)
            return null;
        else{
            for(Team t : node.getTeams()){
                for(Member p : t.getMembers()){
                    if(p.getId() == id)
                        return p;
                }
            }

            for(Manager tm : node.getManagers()){
                result = innerFindMember(id, tm.getTeamManager());
                if(result != null)
                    return result;
            }
            return result;
        }
    }

    public Member findMemberByHashcode(int hashcode){
        if(this.rootExists())
            return innerFindMemberByHashcode(hashcode, this.getRoot());
        else
            return null;
    }

    private Member innerFindMemberByHashcode(int hashcode, TeamManager node){
        Member result = null;

        if(node == null)
            return null;
        else{
            for(Team t : node.getTeams()){
                for(Member p : t.getMembers()){
                    if(p.hashCode() == hashcode)
                        return p;
                }
            }

            for(Manager tm : node.getManagers()){
                result = innerFindMemberByHashcode(hashcode, tm.getTeamManager());
                if(result != null)
                    return result;
            }
            return result;
        }
    }

    public Team findTeamOfMember(long id){
        if(this.rootExists())
            return innerFindTeamOfMember(id, this.getRoot());
        else
            return null;
    }

    private Team innerFindTeamOfMember(long id, TeamManager node){
        Team result = null;

        if(node == null)
            return null;
        else{
            for(Team t : node.getTeams()){
                for(Member p : t.getMembers()){
                    if(p.getId() == id)
                        return t;
                }
            }

            for(Manager tm : node.getManagers()){
                result = innerFindTeamOfMember(id, tm.getTeamManager());
                if(result != null)
                    return result;
            }
            return result;
        }
    }

    public Team findTeamOfMemberByHashcode(int hashcode){
        if(this.rootExists())
            return innerFindTeamOfMemberByHashcode(hashcode, this.getRoot());
        else
            return null;
    }

    private Team innerFindTeamOfMemberByHashcode(int hashcode, TeamManager node){
        Team result = null;

        if(node == null)
            return null;
        else{
            for(Team t : node.getTeams()){
                for(Member p : t.getMembers()){
                    if(p.hashCode() == hashcode)
                        return t;
                }
            }

            for(Manager tm : node.getManagers()){
                result = innerFindTeamOfMemberByHashcode(hashcode, tm.getTeamManager());
                if(result != null)
                    return result;
            }
            return result;
        }
    }

    public long maxMemberId(){

        return innerMaxMemberId(0, this.getRoot());
    }

    private long innerMaxMemberId(long maxId, TeamManager node){
        long m = maxId;

        if(node == null)
            return maxId;
        else{
            for(Team t : node.getTeams())
                for(Member p : t.getMembers())
                    m = Math.max(m, p.getId());

            for(Manager tm : node.getManagers())
                m = Math.max(m, innerMaxMemberId(maxId, tm.getTeamManager()));

            return m;
        }
    }

    public long maxTeamId(){
        return innerMaxTeamId(0, this.getRoot());
    }

    private long innerMaxTeamId(long maxId, TeamManager node){
        long m = maxId;

        if(node == null)
            return maxId;
        else{
            for(Team t : node.getTeams())
                m = Math.max(m, t.getId());

            for(Manager tm : node.getManagers())
                m = Math.max(m, innerMaxTeamId(maxId, tm.getTeamManager()));

            return m;
        }
    }

    public long maxManagerId(){
        return innerMaxManagerId(0, this.getRoot());
    }

    private long innerMaxManagerId(long maxId, TeamManager node){
        long m = maxId;

        if(node == null)
            return maxId;
        else{
            m = Math.max(m, node.getId());
            for(Manager tm : node.getManagers()){
                m = Math.max(m, innerMaxManagerId(maxId, tm.getTeamManager()));
            }
            return m;
        }
    }
}
