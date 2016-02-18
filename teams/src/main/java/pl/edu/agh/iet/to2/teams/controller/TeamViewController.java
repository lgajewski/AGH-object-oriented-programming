package pl.edu.agh.iet.to2.teams.controller;

import javafx.beans.binding.Binding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pl.edu.agh.iet.to2.teams.api.person.Member;
import pl.edu.agh.iet.to2.teams.api.person.TeamManager;
import pl.edu.agh.iet.to2.teams.api.person.TesterPerson;
import pl.edu.agh.iet.to2.teams.api.team.Team;
import pl.edu.agh.iet.to2.teams.common.TeamData;
import pl.edu.agh.iet.to2.teams.common.TeamsModelManipulator;
import pl.edu.agh.iet.to2.teams.model.TeamsTree;
import pl.edu.agh.iet.to2.teams.view.ComponentView;
import pl.edu.agh.iet.to2.teams.view.CustomTreeObject;
import pl.edu.agh.iet.to2.teams.view.TeamView;

import java.util.Random;

/**
 * Created by maciek on 02.02.16.
 */
public class TeamViewController implements SubController{

    private int childHashcode;
    private AnchorPane pane;
    private TeamView view;
    private TeamsModelManipulator manipulator;
    private TeamsTree teamsTree;
    private TeamData database;


    public int getChildHashcode() {
        return childHashcode;
    }

    public void setChildHashcode(int childHashcode) {
        this.childHashcode = childHashcode;
    }

    public TeamView getView() {
        return view;
    }

    public void setView(ComponentView view) {
        this.view = (TeamView) view;
    }

    public AnchorPane getPane() {
        return pane;
    }

    public void setPane(AnchorPane pane) {
        this.pane = pane;
    }


    public void initialize(){

        initContextMenu(view.contextMenu);

        view.tree.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<CustomTreeObject>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<CustomTreeObject>> observable, TreeItem<CustomTreeObject> oldValue, TreeItem<CustomTreeObject> newValue) {
                if(observable != null && observable.getValue() != null && observable.getValue().getValue() != null){
                    //System.out.println(observable.getValue().getValue().getContent());

                    if(teamsTree.findTeamManagerByHashcode(observable.getValue().getValue().getHashcode()) != null){
                        // team manager is selected
                        view.contextMenu.getItems().get(0).setVisible(true);    // add manager
                        view.contextMenu.getItems().get(1).setVisible(true);    // add team
                        view.contextMenu.getItems().get(2).setVisible(false);   // add member
                        view.contextMenu.getItems().get(3).setVisible(true);    // edit
                        view.contextMenu.getItems().get(4).setVisible(true);    // remove
                    }
                    else if(teamsTree.findTeamByHashcode(observable.getValue().getValue().getHashcode()) != null){
                        // team is selected
                        view.contextMenu.getItems().get(0).setVisible(false);   // add manager
                        view.contextMenu.getItems().get(1).setVisible(false);   // add team
                        view.contextMenu.getItems().get(2).setVisible(true);    // add member
                        view.contextMenu.getItems().get(3).setVisible(true);    // edit
                        view.contextMenu.getItems().get(4).setVisible(true);    // remove
                    }
                    else if(teamsTree.findMemberByHashcode(observable.getValue().getValue().getHashcode()) != null){
                        // member is selected
                        view.contextMenu.getItems().get(0).setVisible(false);   // add manager
                        view.contextMenu.getItems().get(1).setVisible(false);   // add team
                        view.contextMenu.getItems().get(2).setVisible(false);   // add member
                        view.contextMenu.getItems().get(3).setVisible(true);    // edit
                        view.contextMenu.getItems().get(4).setVisible(true);    // remove
                    }
                    else
                        System.out.println("Brak elementu o danym hashcode: " + observable.getValue().getValue().getHashcode());
                }
                else{
                    // no items in tree
                    view.contextMenu.getItems().get(0).setVisible(true);    // add manager
                    view.contextMenu.getItems().get(1).setVisible(false);   // add team
                    view.contextMenu.getItems().get(2).setVisible(false);   // add member
                    view.contextMenu.getItems().get(3).setVisible(false);   // edit
                    view.contextMenu.getItems().get(4).setVisible(false);   // remove
                }
            }
        });
    }

    public void initContextMenu(ContextMenu menu){
        MenuItem itemAddManager = new MenuItem("Add manager");
        itemAddManager.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(view.tree.getSelectionModel().getSelectedItem() != null && view.tree.getSelectionModel().getSelectedItem().getValue() != null){
                    TeamManager tm = teamsTree.findTeamManagerByHashcode(view.tree.getSelectionModel().getSelectedItem().getValue().getHashcode());
                    if(tm != null){
                        view.getDialogView().setTitle("Add manager");
                        view.getDialogView().disableId();
                        view.getDialogView().enableName();
                        view.getDialogView().enableOccupation();
                        view.getDialogView().getIdFieldTextProperty().set(String.valueOf(teamsTree.maxManagerId()+1));
                        view.getDialogView().getNameFieldTextProperty().set("Manager"+view.getDialogView().getIdFieldTextProperty().get());
                        view.getDialogView().getOccupationFieldTextProperty().set("Manager");

                        view.getDialogView().setOnHiding(new EventHandler<WindowEvent>() {
                            @Override
                            public void handle(WindowEvent event) {
                                long id = database.addManagerInDb(view.getDialogView().getNameFieldTextProperty().get(), view.getDialogView().getOccupationFieldTextProperty().get(), tm.getId());
                                if(id < 0)
                                    throw new NullPointerException("invalid ID");
                                else
                                    manipulator.addTeamManager(tm.getId(), new TeamManager(id, view.getDialogView().getNameFieldTextProperty().get(), view.getDialogView().getOccupationFieldTextProperty().get()));

                            }
                        });
                        view.getDialogView().show();
                    }
                }
                else{   // no selection
                    view.getDialogView().setTitle("Add manager");
                    view.getDialogView().disableId();
                    view.getDialogView().enableName();
                    view.getDialogView().enableOccupation();
                    view.getDialogView().getIdFieldTextProperty().set(String.valueOf(teamsTree.maxManagerId()+1));
                    view.getDialogView().getNameFieldTextProperty().set("Manager"+view.getDialogView().getIdFieldTextProperty().get());
                    view.getDialogView().getOccupationFieldTextProperty().set("Manager");

                    view.getDialogView().setOnHiding(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent event) {
                            long id = database.addManagerInDb(view.getDialogView().getNameFieldTextProperty().get(), view.getDialogView().getOccupationFieldTextProperty().get(), 0);
                            if(id < 0)
                                throw new NullPointerException("invalid ID");
                            else
                            manipulator.addTeamManager(0, new TeamManager(id, view.getDialogView().getNameFieldTextProperty().get(), view.getDialogView().getOccupationFieldTextProperty().get()));
                        }
                    });
                    view.getDialogView().show();
                }
            }
        });
        view.contextMenu.getItems().add(0, itemAddManager);

        MenuItem itemAddTeam = new MenuItem("Add team");
        itemAddTeam.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(view.tree.getSelectionModel().getSelectedItem() != null && view.tree.getSelectionModel().getSelectedItem().getValue() != null){
                    TeamManager tm = teamsTree.findTeamManagerByHashcode(view.tree.getSelectionModel().getSelectedItem().getValue().getHashcode());
                    if(tm != null){
                        view.getDialogView().setTitle("Add team");
                        view.getDialogView().disableId();
                        view.getDialogView().enableName();
                        view.getDialogView().disableOccupation();
                        view.getDialogView().getIdFieldTextProperty().set(String.valueOf(teamsTree.maxTeamId()+1));
                        view.getDialogView().getNameFieldTextProperty().set("Team"+view.getDialogView().getIdFieldTextProperty().get());
                        view.getDialogView().getOccupationFieldTextProperty().set("");

                        view.getDialogView().setOnHiding(new EventHandler<WindowEvent>() {
                            @Override
                            public void handle(WindowEvent event) {
                                long id = database.addEmptyTeam(view.getDialogView().getNameFieldTextProperty().get(), tm.getId());
                                if(id < 0)
                                    throw new NullPointerException("invalid ID");
                                else
                                manipulator.addTeam(tm.getId(), Team.createTeam(id, view.getDialogView().getNameFieldTextProperty().get()));
                            }
                        });
                        view.getDialogView().show();
                    }
                }
            }
        });
        view.contextMenu.getItems().add(1, itemAddTeam);

        MenuItem itemAddMember = new MenuItem("Add member");
        itemAddMember.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(view.tree.getSelectionModel().getSelectedItem() != null && view.tree.getSelectionModel().getSelectedItem().getValue() != null){
                    Team t = teamsTree.findTeamByHashcode(view.tree.getSelectionModel().getSelectedItem().getValue().getHashcode());
                    if(t != null){
                        view.getDialogView().setTitle("Add member");
                        view.getDialogView().disableId();
                        view.getDialogView().enableName();
                        view.getDialogView().enableOccupation();
                        view.getDialogView().getIdFieldTextProperty().set(String.valueOf(teamsTree.maxMemberId()+1));
                        view.getDialogView().getNameFieldTextProperty().set("Member"+view.getDialogView().getIdFieldTextProperty().get());
                        view.getDialogView().getOccupationFieldTextProperty().set("None");

                        view.getDialogView().setOnHiding(new EventHandler<WindowEvent>() {
                            @Override
                            public void handle(WindowEvent event) {
                                long id = database.addMember(view.getDialogView().getNameFieldTextProperty().get(), view.getDialogView().getOccupationFieldTextProperty().get(), t.getId());
                                if(id < 0)
                                    throw new NullPointerException("invalid ID");
                                else
                                manipulator.addMember(t.getId(), new TesterPerson(id, view.getDialogView().getNameFieldTextProperty().get(), view.getDialogView().getOccupationFieldTextProperty().get()));
                            }
                        });
                        view.getDialogView().show();
                    }
                }
            }
        });
        view.contextMenu.getItems().add(2, itemAddMember);

        MenuItem itemEdit = new MenuItem("Edit");
        itemEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(view.tree.getSelectionModel().getSelectedItem() != null && view.tree.getSelectionModel().getSelectedItem().getValue() != null){
                    Member m;
                    Team t;
                    TeamManager tm;
                    if((m = teamsTree.findMemberByHashcode(view.tree.getSelectionModel().getSelectedItem().getValue().getHashcode())) != null){
                        view.getDialogView().setTitle("Edit member");
                        view.getDialogView().disableId();
                        view.getDialogView().enableName();
                        view.getDialogView().enableOccupation();

                        view.getDialogView().getIdFieldTextProperty().bind(((TesterPerson) m).getIdProperty().asString());
                        view.getDialogView().getNameFieldTextProperty().bindBidirectional(((TesterPerson) m).getNameProperty());
                        view.getDialogView().getOccupationFieldTextProperty().bindBidirectional(((TesterPerson) m).getOccupationProperty());
                        view.getDialogView().setOnHiding(new EventHandler<WindowEvent>() {
                            @Override
                            public void handle(WindowEvent event) {
                                database.editMemberName(m.getId(), m.getName());
                                database.editMemberPosition(m.getId(), m.getOccupation());
                                view.getDialogView().getIdFieldTextProperty().unbind();
                                view.getDialogView().getNameFieldTextProperty().unbindBidirectional(((TesterPerson) m).getNameProperty());
                                view.getDialogView().getOccupationFieldTextProperty().unbindBidirectional(((TesterPerson) m).getOccupationProperty());
                            }
                        });
                        view.getDialogView().show();
                    }
                    else if((t = teamsTree.findTeamByHashcode(view.tree.getSelectionModel().getSelectedItem().getValue().getHashcode())) != null){
                        view.getDialogView().setTitle("Edit team");
                        view.getDialogView().disableId();
                        view.getDialogView().enableName();
                        view.getDialogView().disableOccupation();

                        view.getDialogView().getIdFieldTextProperty().bind(t.getIdProperty().asString());
                        view.getDialogView().getNameFieldTextProperty().bindBidirectional(t.getNameProperty());
                        view.getDialogView().getOccupationFieldTextProperty().set("");
                        view.getDialogView().setOnHiding(new EventHandler<WindowEvent>() {
                            @Override
                            public void handle(WindowEvent event) {
                                database.editTeamName(t.getId(), t.getName());
                                view.getDialogView().getIdFieldTextProperty().unbind();
                                view.getDialogView().getNameFieldTextProperty().unbindBidirectional(t.getNameProperty());
                            }
                        });
                        view.getDialogView().show();
                    }
                    else if((tm = teamsTree.findTeamManagerByHashcode(view.tree.getSelectionModel().getSelectedItem().getValue().getHashcode())) != null){
                        view.getDialogView().setTitle("Edit manager");
                        view.getDialogView().disableId();
                        view.getDialogView().enableName();
                        view.getDialogView().enableOccupation();

                        view.getDialogView().getIdFieldTextProperty().bind(tm.getIdProperty().asString());
                        view.getDialogView().getNameFieldTextProperty().bindBidirectional(tm.getNameProperty());
                        view.getDialogView().getOccupationFieldTextProperty().bindBidirectional(tm.getOccupationProperty());
                        view.getDialogView().setOnHiding(new EventHandler<WindowEvent>() {
                            @Override
                            public void handle(WindowEvent event) {
                                database.editManagerName(tm.getId(), tm.getName());
                                database.editManagerPosition(tm.getId(), tm.getOccupation());
                                view.getDialogView().getIdFieldTextProperty().unbind();
                                view.getDialogView().getNameFieldTextProperty().unbindBidirectional(tm.getNameProperty());
                                view.getDialogView().getOccupationFieldTextProperty().unbindBidirectional(tm.getOccupationProperty());
                            }
                        });
                        view.getDialogView().show();
                    }
                }
            }
        });
        view.contextMenu.getItems().add(3, itemEdit);

        MenuItem itemRemove = new MenuItem("Remove");
        itemRemove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                long id = -1;

                if(view.tree.getSelectionModel().getSelectedItem() != null && view.tree.getSelectionModel().getSelectedItem().getValue() != null){
                    if((id = manipulator.removeMemberByHashcode(view.tree.getSelectionModel().getSelectedItem().getValue().getHashcode())) == -1)
                        if((id = manipulator.removeTeamByHashcode(view.tree.getSelectionModel().getSelectedItem().getValue().getHashcode())) == -1)
                            if((id = manipulator.removeTeamManagerByHashcode(view.tree.getSelectionModel().getSelectedItem().getValue().getHashcode())) == -1){
                                if(view.tree.getRoot().getChildren().isEmpty())
                                    view.tree.getSelectionModel().clearSelection();
                            }
                            else // manager removed
                                database.deleteManager(id);
                        else // team removed
                            database.deleteTeam(id);
                    else // member removed
                        database.deleteMember(id);

                    if(view.tree.getRoot().getChildren().isEmpty())
                        view.tree.getSelectionModel().clearSelection();
                }
            }
        });
        view.contextMenu.getItems().add(4, itemRemove);

        view.contextMenu.getItems().get(0).setVisible(false);   // add manager
        view.contextMenu.getItems().get(1).setVisible(false);   // add team
        view.contextMenu.getItems().get(2).setVisible(false);   // add member
        view.contextMenu.getItems().get(3).setVisible(false);   // edit
        view.contextMenu.getItems().get(4).setVisible(false);   // remove
    }

    private TeamViewController(AnchorPane pane, TeamView view, TeamsModelManipulator manipulator, TeamsTree teamsTree, TeamData database){
        this.pane = pane;
        this.manipulator = manipulator;
        this.view = view;
        this.childHashcode = view.hashCode();
        this.teamsTree = teamsTree;
        this.database = database;
    }

    public static TeamViewController createControllerOn(AnchorPane pane, TeamView view, TeamsModelManipulator manipulator, TeamsTree teamsTree, TeamData database){
        TeamViewController tvc = new TeamViewController(pane, view, manipulator, teamsTree, database);
        tvc.initialize();
        return tvc;
    }
}
