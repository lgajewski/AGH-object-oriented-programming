package pl.edu.agh.iet.to2.projects.persistence;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.edu.agh.iet.to2.projects.model.Project;

import java.util.List;

public class ProjectDao {

    public static Project getProjectById(long id) {


        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();

        Project project = session.get(Project.class, id);
        transaction.commit();
        session.close();
        return project;

    }


    public static List<Project> getProjects() {

        String hql = "FROM Project";

        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();

        @SuppressWarnings("unchecked")
        List<Project> projects = session.createQuery(hql).list();

        transaction.commit();
        session.close();
        return projects;

    }

    public static void saveProject(Project project) {

        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(project);
        transaction.commit();
        session.close();

    }

    public static void deleteProjects(List<Project> projects) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        for (Project p : projects) {
            Object persistentInstance = session.load(Project.class, p.getId());
            if (persistentInstance != null) {
                session.delete(persistentInstance);
            }
        }
        transaction.commit();
        session.close();
    }

}
