package pl.edu.agh.iet.to2.teams.controller;

import pl.edu.agh.iet.to2.teams.view.ComponentView;
import pl.edu.agh.iet.to2.teams.view.TeamView;

/**
 * Created by maciek on 20.01.16.
 */
public interface SubController {

    int childHashcode = 0;

    void initialize();
    void setView(ComponentView view);
    int getChildHashcode();
    //long getChildId();

}
