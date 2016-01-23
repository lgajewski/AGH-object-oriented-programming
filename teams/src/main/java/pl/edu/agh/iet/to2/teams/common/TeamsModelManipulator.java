package pl.edu.agh.iet.to2.teams.common;

import pl.edu.agh.iet.to2.teams.api.person.TeamManager;
import pl.edu.agh.iet.to2.teams.api.team.Team;
import pl.edu.agh.iet.to2.teams.controller.MainController;
import pl.edu.agh.iet.to2.teams.controller.TeamController;
import pl.edu.agh.iet.to2.teams.controller.TeamManagerController;
import pl.edu.agh.iet.to2.teams.model.TeamsTree;

/**
 * Created by maciek on 21.01.16.
 */
public class TeamsModelManipulator {

    private TeamsTree teamsTree;
    private MainController mainController;

    public TeamsModelManipulator(TeamsTree teamsTree, MainController mainController){
        this.teamsTree = teamsTree;
        this.mainController = mainController;
    }

    public boolean addTeam(int parentId, Team team)
    // parentId = 0 for root
    // if adding team succeeded, returns true
    // if there is no root in teamsTree, returns false
    // if there is no node with parentId, returns false
    {
        TeamManager parent = null;

        if(parentId == 0){
            if(teamsTree.rootExists())
                parent = teamsTree.getRoot();
            else
                parent = null;
        }
        else{
            parent = teamsTree.findTeamManager(parentId);
        }

        if(teamsTree.rootExists()){
            if(parent == null){
                return false;
            }
            else{
                mainController.addController(TeamController.createControllerOn(team, mainController.getPane(), mainController.getTeamView()));
                //team.setManager(parent);
                parent.addTeam(team);
                return true;
            }
        }
        else
            return false;
    }

   /* public void removeTeam(int id){
        Team team = teamsTree.findTeam(id);
        if(team != null){
            team.getManager().getTeamManager().getTeams().clear();
            team.setManager(null);
        }
    }

    public void removeTeamByHashcode(int hashcode){
        Team team = teamsTree.findTeamByHashcode(hashcode);
        if(team != null){
            team.getManager().getTeamManager().getTeams().clear();
            team.setManager(null);
        }
    }

    public void removeTeam(Team team){
        if(team != null){
            team.getManager().getTeamManager().getTeams().clear();
            team.setManager(null);
        }
    }*/

    public boolean addTeamManager(int parentId, TeamManager teamManager)
    // parentId = 0 for root
    // if adding team manager succeeded, returns true
    // if there is no node with parentId, returns false
    {
        TeamManager parent = null;

        if(parentId == 0){
            if(teamsTree.rootExists()) {
                parent = teamsTree.getRoot();
                mainController.addController(TeamManagerController.createControllerOn(teamManager, mainController.getPane(), mainController.getTeamView()));
                teamsTree.setRoot(teamManager); // new root
                return true;
            }
            else{
                mainController.addController(TeamManagerController.createControllerOn(teamManager, mainController.getPane(), mainController.getTeamView()));
                teamsTree.setRoot(teamManager);
                return true;
            }
        }
        else{
            parent = teamsTree.findTeamManager(parentId);
            if(parent == null){
                return false;
            }
            else{
                mainController.addController(TeamManagerController.createControllerOn(teamManager, mainController.getPane(), mainController.getTeamView()));
              //  teamManager.setSuperior(parent);
                parent.addManager(teamManager);
                return true;
            }
        }
    }

}
