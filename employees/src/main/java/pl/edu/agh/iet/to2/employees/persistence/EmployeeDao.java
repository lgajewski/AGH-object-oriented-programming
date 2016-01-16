package pl.edu.agh.iet.to2.employees.persistence;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.edu.agh.iet.to2.employees.IEmployeesModule;
import pl.edu.agh.iet.to2.employees.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    private List<IEmployeesModule.OnEmployeeUpdateListener> updateListeners;
    private List<IEmployeesModule.OnEmployeeEventListener> addListeners;
    private List<IEmployeesModule.OnEmployeeEventListener> deleteListeners;

    public EmployeeDao() {
        this.updateListeners = new ArrayList<>();
        this.addListeners = new ArrayList<>();
        this.deleteListeners = new ArrayList<>();
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

        addListeners.forEach(listener -> listener.onEvent(employee));
    }

    public void updateEmployee(long id, Employee employee) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();

        Employee oldEmployee = session.get(Employee.class, id);
        session.merge(employee);

        transaction.commit();
        session.close();

        updateListeners.forEach(listener -> listener.onUpdate(oldEmployee, employee));
    }

    public void deleteEmployee(long id) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();

        Employee employee = session.get(Employee.class, id);

        if (employee != null) {
            session.delete(employee);
            deleteListeners.forEach(listener -> listener.onEvent(employee));
        }

        transaction.commit();
        session.close();
    }

    public void addOnEmployeeUpdatedListener(IEmployeesModule.OnEmployeeUpdateListener listener) {
        updateListeners.add(listener);
    }

    public void addOnEmployeeAddedListener(IEmployeesModule.OnEmployeeEventListener listener) {
        addListeners.add(listener);
    }

    public void addOnEmployeeDeletedListener(IEmployeesModule.OnEmployeeEventListener listener) {
        deleteListeners.add(listener);
    }
}
