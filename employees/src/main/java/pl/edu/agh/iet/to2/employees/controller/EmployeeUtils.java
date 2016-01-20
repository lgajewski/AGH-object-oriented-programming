package pl.edu.agh.iet.to2.employees.controller;

import javafx.scene.image.Image;

public class EmployeeUtils {

    public static final String INITIAL_DIRECTORY = "/employees/images/avatars/";

    public static Image getAvatar(String avatarName) {
        return new Image(EmployeeUtils.class.getClassLoader().getResourceAsStream(INITIAL_DIRECTORY + avatarName));
    }

}
