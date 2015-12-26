package pl.edu.agh.iet.to2;

import javafx.scene.Scene;
import pl.edu.agh.iet.to2.employees.IEmployee;

public interface Presenter {
    void showAndWait(String title, Scene scene);
}
