package pl.edu.agh.iet.to2.projects;

import pl.edu.agh.iet.to2.employees.IEmployee;
import pl.edu.agh.iet.to2.teams.ITeam;
import pl.edu.agh.iet.to2.teams.ITeamMember;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectsModuleImpl implements IProjectsModule {

    private Map<Long, IProject> projectMap;

    public ProjectsModuleImpl() {
        this.projectMap = new HashMap<Long, IProject>();
    }

    public List<IProject> getProjects() {
        return new ArrayList<IProject>(projectMap.values());
    }

    public IProject getProjectById(long id) {
        return projectMap.get(id);
    }

    public void addProject(IProject project) {
        projectMap.put(project.getId(), project);
    }

    public void clear() {
        projectMap.clear();
    }

    public List<String> getProjectsForEmployeeId(long id) {

        List<String> result = new ArrayList<String>();
        for (Long ID : projectMap.keySet()) {
            IProject p = projectMap.get(ID);
            for(IEmployee emp : p.getMembers()) {
                if(emp.getId() == id) {
                    result.add(p.getName());
                    break;
                }
            }
        }

        return result;

    }


}
