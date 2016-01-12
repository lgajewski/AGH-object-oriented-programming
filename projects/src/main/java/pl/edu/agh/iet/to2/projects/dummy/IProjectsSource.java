package pl.edu.agh.iet.to2.projects.dummy;

import javafx.collections.ObservableList;
import projectsmodel.Project;


public interface IProjectsSource {
    ObservableList<Project> getProjects();
}
