package pl.edu.agh.iet.to2.projects;

import java.util.List;

/**
 * Created by Patrycja on 01.12.2015.
 */
public interface ProjectsModule {

    List<String> getProjectsForEmployeeId(long id);
}
