package pl.edu.agh.iet.to2.projects;

import java.util.List;

public interface IProjectsModule {

    List<String> getProjectsForEmployeeId(long id);
}
