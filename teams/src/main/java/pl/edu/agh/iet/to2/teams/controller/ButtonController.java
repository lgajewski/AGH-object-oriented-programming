package pl.edu.agh.iet.to2.teams.controller;

import pl.edu.agh.iet.to2.teams.view.ButtonView;
import pl.edu.agh.iet.to2.teams.view.ComponentView;
import pl.edu.agh.iet.to2.teams.view.TeamView;

/**
 * Created by Pan Ciemnosci on 2016-01-23.
 */
public class ButtonController implements SubController{

    ButtonView view;


    @Override
    public void initialize() {
//        view.initialize();
    }

    @Override
    public void setView(ComponentView view) {
        this.view = (ButtonView) view;
    }

    @Override
    public int getChildHashcode() {
        return 0;
    }
}
