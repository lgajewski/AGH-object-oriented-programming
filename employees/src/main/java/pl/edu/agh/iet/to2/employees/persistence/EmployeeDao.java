package pl.edu.agh.iet.to2.employees.persistence;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.edu.agh.iet.to2.employees.model.Employee;

import java.util.List;

public class EmployeeDao {

    public static Employee getEmployeeId(long id) {
        String hql = "FROM Employee e where e.id = " + id;

        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();

        transaction.commit();

        return (Employee) session.createQuery(hql).uniqueResult();
    }

    public static List<Employee> getEmployees() {
        String hql = "FROM Employee";

        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();

        @SuppressWarnings("unchecked")
        List<Employee> employees = session.createQuery(hql).list();

        transaction.commit();

        return employees;
    }

    public static void saveEmployee(Employee employee) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(employee);
        transaction.commit();
    }

}
