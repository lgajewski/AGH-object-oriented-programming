package pl.edu.agh.iet.to2.employees.persistence;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.edu.agh.iet.to2.employees.model.Employee;

import java.util.List;

public class EmployeeDao {

    private EventNotifier eventNotifier;

    public EmployeeDao(EventNotifier eventNotifier) {
        this.eventNotifier = eventNotifier;
    }

    public Employee getEmployeeById(long id) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();

        Employee employee = session.get(Employee.class, id);

        transaction.commit();
        session.close();

        return employee;
    }

    public List<Employee> getEmployees() {
        String hql = "FROM Employee";

        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();

        @SuppressWarnings("unchecked")
        List<Employee> employees = session.createQuery(hql).list();

        transaction.commit();
        session.close();

        return employees;
    }

    public void addEmployee(Employee employee) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(employee);
        transaction.commit();
        session.close();

        eventNotifier.notifyAdd(employee);
    }

    public void updateEmployee(long id, Employee employee) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();

        Employee oldEmployee = session.get(Employee.class, id);
        session.merge(employee);

        transaction.commit();
        session.close();

        eventNotifier.notifyUpdate(oldEmployee, employee);
    }

    public void deleteEmployee(long id) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();

        Employee employee = session.get(Employee.class, id);

        if (employee != null) {
            session.delete(employee);
            eventNotifier.notifyDelete(employee);
        }

        transaction.commit();
        session.close();
    }

}
