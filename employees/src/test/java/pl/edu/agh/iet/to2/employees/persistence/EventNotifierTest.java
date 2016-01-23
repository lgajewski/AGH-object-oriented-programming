package pl.edu.agh.iet.to2.employees.persistence;

import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.iet.to2.employees.model.Employee;

import static org.mockito.Mockito.*;
import static pl.edu.agh.iet.to2.employees.IEmployeesModule.OnEmployeeEventListener;
import static pl.edu.agh.iet.to2.employees.IEmployeesModule.OnEmployeeUpdateListener;

public class EventNotifierTest {

    private EventNotifier eventNotifier;

    @Before
    public void setUp() throws Exception {
        this.eventNotifier = new EventNotifier();
    }

    @Test
    public void testNotifyAdd() throws Exception {
        Employee employee = mock(Employee.class);
        OnEmployeeEventListener listener = mock(OnEmployeeEventListener.class);

        // add listener and notify
        eventNotifier.addOnEmployeeAddedListener(listener);
        eventNotifier.notifyAdd(employee);

        verify(listener).onEvent(employee);
    }

    @Test
    public void testNotifyUpdate() throws Exception {
        Employee oldEmployee = mock(Employee.class);
        Employee newEmployee = mock(Employee.class);
        OnEmployeeUpdateListener listener = mock(OnEmployeeUpdateListener.class);

        // add listener and notify
        eventNotifier.addOnEmployeeUpdatedListener(listener);
        eventNotifier.notifyUpdate(oldEmployee, newEmployee);

        verify(listener).onUpdate(oldEmployee, newEmployee);
    }

    @Test
    public void testNotifyDelete() throws Exception {
        Employee employee = mock(Employee.class);
        OnEmployeeEventListener listener = mock(OnEmployeeEventListener.class);

        // add listener and notify
        eventNotifier.addOnEmployeeDeletedListener(listener);
        eventNotifier.notifyDelete(employee);

        verify(listener).onEvent(employee);
    }

    @Test
    public void testAddMultipleListeners() {
        int listeners_amount = 10;
        OnEmployeeEventListener[] listeners = new OnEmployeeEventListener[listeners_amount];

        Employee employee = mock(Employee.class);
        for (int i = 0; i < listeners_amount; i++) {
            listeners[i] = mock(OnEmployeeEventListener.class);
        }

        // add listener and notify
        for (OnEmployeeEventListener listener : listeners) {
            eventNotifier.addOnEmployeeAddedListener(listener);
        }
        eventNotifier.notifyAdd(employee);

        // check for every listener
        for (OnEmployeeEventListener listener : listeners) {
            verify(listener).onEvent(employee);
        }
    }

    @Test
    public void testAddSameListenerTwice() {
        Employee employee = mock(Employee.class);
        OnEmployeeEventListener listener = mock(OnEmployeeEventListener.class);

        // add listener and notify
        eventNotifier.addOnEmployeeDeletedListener(listener);
        eventNotifier.addOnEmployeeDeletedListener(listener);
        eventNotifier.notifyDelete(employee);

        verify(listener, times(2)).onEvent(employee);
    }

    @Test
    public void testNoEventForDifferentNotification() {
        Employee employee = mock(Employee.class);
        OnEmployeeEventListener listener = mock(OnEmployeeEventListener.class);

        // add listener and notify
        eventNotifier.addOnEmployeeAddedListener(listener);
        eventNotifier.notifyDelete(employee);

        verifyNoMoreInteractions(listener);
    }

}