package pl.edu.agh.iet.to2.teams.common;

import pl.edu.agh.iet.to2.teams.api.person.*;
import pl.edu.agh.iet.to2.teams.api.team.Team;
import pl.edu.agh.iet.to2.teams.controller.MainController;
import pl.edu.agh.iet.to2.teams.controller.TeamController;
import pl.edu.agh.iet.to2.teams.controller.TeamManagerController;
import pl.edu.agh.iet.to2.teams.controller.TesterPersonController;
import pl.edu.agh.iet.to2.teams.model.TeamsTree;
import pl.edu.agh.iet.to2.teams.view.TeamView;

/**
 * Created by maciek on 21.01.16.
 */
public class TeamsModelManipulator {

    private TeamsTree teamsTree;
    private MainController mainController;
    private TeamView view;

    public TeamsModelManipulator(TeamsTree teamsTree, MainController mainController, TeamView view){
        this.teamsTree = teamsTree;
        this.mainController = mainController;
        this.view = view;
    }

    public boolean addTeam(long parentId, Team team)
    // if adding team succeeded, returns true
    // if there is no root in teamsTree, returns false
    // if there is no node with parentId, returns false
    {
        TeamManager parent = teamsTree.findTeamManager(parentId);

        if(teamsTree.rootExists()){
            if(parent == null)
                return false;
            else{
                mainController.addController(TeamController.createControllerOn(team, mainController.getPane(), (TeamView) mainController.getView("TeamView")));
                //team.setManager(parent);
                parent.addTeam(team);
                return true;
            }
        }
        else
            return false;
    }

    public void removeTeam(long id){
        TeamManager teamManager = teamsTree.findManagerOfTeam(id);
        if(teamManager != null)
            for(Team t : teamManager.getTeams())
                if(t.getId() == id){
                    mainController.removeControllerByHashcode(t.hashCode());
                    teamManager.removeTeam(id);
                }
    }

    public void removeTeamByHashcode(int hashcode){
        TeamManager teamManager = teamsTree.findManagerOfTeamByHashcode(hashcode);
        if(teamManager != null){
            mainController.removeControllerByHashcode(hashcode);
            teamManager.removeTeamByHashcode(hashcode);
        }
    }

    public void removeTeam(Team team){
        this.removeTeamByHashcode(team.hashCode());
    }

    public boolean addTeamManager(long parentId, TeamManager teamManager)
    // parentId = 0 for root
    // if root exists and parentId == 0, returns false
    // if adding team manager succeeded, returns true
    // if there is no node with parentId, returns false
    {
        TeamManager parent = null;

        if(parentId == 0){
            if(teamsTree.rootExists()) {
                /*mainController.addController(TeamManagerController.createControllerOn(teamManager, mainController.getPane(), mainController.getTeamView()));
                teamsTree.setRoot(teamManager); // new root*/
                return false;
            }
            else{
                mainController.addController(TeamManagerController.createControllerOn(teamManager, mainController.getPane(), (TeamView) mainController.getView("TeamView")));
                teamsTree.setRoot(teamManager);
                view.redrawRoot(teamManager);
                return true;
            }
        }
        else{
            parent = teamsTree.findTeamManager(parentId);
            if(parent == null){
                return false;
            }
            else{
                mainController.addController(TeamManagerController.createControllerOn(teamManager, mainController.getPane(),(TeamView) mainController.getView("TeamView")));
              //  teamManager.setSuperior(parent);
                parent.addManager(teamManager);
                return true;
            }
        }
    }

    public void removeTeamManager(long id){
        TeamManager teamManager = teamsTree.findSuperiorOfManager(id);
        if(teamManager != null)
            for(Manager m : teamManager.getManagers())
                if(m.getId() == id){
                    mainController.removeControllerByHashcode(m.hashCode());
                    teamManager.removeManager(id);
                }
    }

    public void removeTeamManagerByHashcode(int hashcode){
        TeamManager teamManager = teamsTree.findSuperiorOfManagerByHashcode(hashcode);
        if(teamManager != null){
            mainController.removeControllerByHashcode(hashcode);
            teamManager.removeManagerByHashcode(hashcode);
        }
    }

    public void removeTeamManager(TeamManager teamManager){
        this.removeTeamManagerByHashcode(teamManager.hashCode());
    }


    public boolean addMember(long teamId, TesterPerson testerPerson){
        Team team = teamsTree.findTeam(teamId);
        if(team != null){
            mainController.addController(TesterPersonController.createControllerOn(testerPerson, mainController.getPane(), (TeamView) mainController.getView("TeamView")));
            team.add(testerPerson);
            return true;
        }
        else
            return false;
    }

}
