package pl.edu.agh.iet.to2.employees;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.edu.agh.iet.to2.employees.model.Employee;
import pl.edu.agh.iet.to2.employees.persistence.EmployeeDao;
import pl.edu.agh.iet.to2.employees.persistence.EventNotifier;

import static org.mockito.Mockito.*;

public class EmployeesModuleIntegTest {

    @Mock
    private EmployeeDao employeeDao;

    private EventNotifier eventNotifier;
    private EmployeesModule module;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        eventNotifier = new EventNotifier();
        module = new EmployeesModule(employeeDao, eventNotifier);
    }

    @Test
    public void testDeleteNotification() {
        long id = 10;
        IEmployeesModule.OnEmployeeEventListener listener = mock(IEmployeesModule.OnEmployeeEventListener.class);
        module.addOnEmployeeDeletedListener(listener);

        // simulate delete employee from EmployeeDao
        Employee employee = mock(Employee.class);
        when(employee.getId()).thenReturn(id);
        eventNotifier.notifyDelete(employee);

        // verify
        verify(listener).onEvent(employee);
    }

    @Test
    public void testAddNotification() {
        long id = 10;
        IEmployeesModule.OnEmployeeEventListener listener = mock(IEmployeesModule.OnEmployeeEventListener.class);
        module.addOnEmployeeAddedListener(listener);

        // simulate add employee from EmployeeDao
        Employee employee = mock(Employee.class);
        when(employee.getId()).thenReturn(id);
        eventNotifier.notifyAdd(employee);

        // verify
        verify(listener).onEvent(employee);
    }

    @Test
    public void testUpdateNotification() {
        long id = 10;
        String oldName = "Jan";
        String newName = "Janek";

        IEmployeesModule.OnEmployeeUpdateListener listener = mock(IEmployeesModule.OnEmployeeUpdateListener.class);
        module.addOnEmployeeUpdatedListener(listener);

        // simulate update employee from EmployeeDao
        Employee oldEmployee = mock(Employee.class);
        when(oldEmployee.getId()).thenReturn(id);
        when(oldEmployee.getName()).thenReturn(oldName);

        Employee newEmployee = mock(Employee.class);
        when(newEmployee.getId()).thenReturn(id);
        when(newEmployee.getName()).thenReturn(newName);
        eventNotifier.notifyUpdate(oldEmployee, newEmployee);

        // verify
        verify(listener).onUpdate(oldEmployee, newEmployee);
    }
}
