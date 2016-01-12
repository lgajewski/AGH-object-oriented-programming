package pl.edu.agh.iet.to2.projects.dummy;

import javafx.collections.ObservableList;
import pl.edu.agh.iet.to2.projects.model.Project;


public interface IProjectsSource {
    ObservableList<Project> getProjects();
}
