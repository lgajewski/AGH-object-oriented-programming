package pl.edu.agh.iet.to2.employees.model;

import java.util.List;

/**
 * Created by Patrycja on 01.12.2015.
 */
public interface ProjectsModule {

    List<String> getProjectsForEmployee(long id);
}
