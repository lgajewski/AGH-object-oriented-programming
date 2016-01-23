package pl.edu.agh.iet.to2.teams.model;

import pl.edu.agh.iet.to2.teams.api.person.Manager;
import pl.edu.agh.iet.to2.teams.api.person.TeamManager;
import pl.edu.agh.iet.to2.teams.api.team.Team;

/**
 * Created by maciek on 21.01.16.
 */
public class TeamsTree {

    private TeamManager root;

    public void setRoot(TeamManager teamManager){
        if(rootExists()){
            //root.setSuperior(teamManager);
            teamManager.addManager(root);
            root = teamManager;
        }
        else
            root = teamManager;
    }

    public void deleteRoot(TeamManager teamManager){
        root = null;
    }

    public TeamManager getRoot(){
        return root;
    }

    public boolean rootExists(){
        if(root == null)
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
            for(Manager tm : node.getManagers()){
                if(tm.getId() == id)
                    return tm.getTeamManager();
                else{
                    result = innerFindTeamManager(id, tm.getTeamManager());
                    if(result != null)
                        return result;
                }
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
            for(Manager tm : node.getManagers()){
                if(tm.hashCode() == hashcode)
                    return tm.getTeamManager();
                else{
                    result = innerFindTeamManagerByHashcode(hashcode, tm.getTeamManager());
                    if(result != null)
                        return result;
                }
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
            for(Manager tm : node.getManagers()){
                for(Team t : tm.getTeamManager().getTeams()){
                    if(t.getId() == id)
                        return t;
                    else{
                        result = innerFindTeam(id, tm.getTeamManager());
                        if(result != null)
                            return result;
                    }
                }
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
            for(Manager tm : node.getManagers()){
                for(Team t : tm.getTeamManager().getTeams()){
                    if(t.hashCode() == hashcode)
                        return t;
                    else{
                        result = innerFindTeamByHashcode(hashcode, tm.getTeamManager());
                        if(result != null)
                            return result;
                    }
                }
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
}
