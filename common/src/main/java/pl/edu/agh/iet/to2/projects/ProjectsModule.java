package pl.edu.agh.iet.to2.projects;

import java.util.List;

public interface ProjectsModule {

    List<String> getProjectsForEmployeeId(long id);
}
