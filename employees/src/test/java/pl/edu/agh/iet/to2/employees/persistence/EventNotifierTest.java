package pl.edu.agh.iet.to2.employees.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.iet.to2.employees.IEmployeesModule;
import pl.edu.agh.iet.to2.employees.model.Employee;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EventNotifierTest {

    EventNotifier notifier;

    @Before
    public void setUp() throws Exception {
        notifier = new EventNotifier();
    }

    @Test
    public void testAddNotification(){
        Employee employee = mock(Employee.class);
        IEmployeesModule.OnEmployeeEventListener listener = mock(IEmployeesModule.OnEmployeeEventListener.class);
        notifier.addOnEmployeeAddedListener(listener);
        notifier.notifyAdd(employee);
        verify(listener).onEvent(employee);
    }

    @Test
    public void testUpdateNotification(){
        Employee employee = mock(Employee.class);
        Employee newEmployee = mock(Employee.class);
        IEmployeesModule.OnEmployeeUpdateListener listener1 = mock(IEmployeesModule.OnEmployeeUpdateListener.class);
        IEmployeesModule.OnEmployeeUpdateListener listener2 = mock(IEmployeesModule.OnEmployeeUpdateListener.class);
        notifier.addOnEmployeeUpdatedListener(listener1);
        notifier.addOnEmployeeUpdatedListener(listener2);
        notifier.notifyUpdate(employee, newEmployee);
        verify(listener1).onUpdate(employee, newEmployee);
        verify(listener2).onUpdate(employee, newEmployee);
    }

    @Test
    public void testDeleteNotification(){
        Employee employee = mock(Employee.class);
        IEmployeesModule.OnEmployeeEventListener listener = mock(IEmployeesModule.OnEmployeeEventListener.class);
        notifier.addOnEmployeeDeletedListener(listener);
        notifier.notifyDelete(employee);
        verify(listener).onEvent(employee);
    }

    @After
    public void tearDown() throws Exception {
        notifier = null;
    }
}