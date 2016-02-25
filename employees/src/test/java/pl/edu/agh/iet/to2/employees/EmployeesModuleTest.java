package pl.edu.agh.iet.to2.employees;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.edu.agh.iet.to2.employees.persistence.EmployeeDao;
import pl.edu.agh.iet.to2.employees.persistence.EventNotifier;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EmployeesModuleTest {

    private EmployeesModule module;
    @Mock
    private EmployeeDao dao;
    @Mock
    private EventNotifier notifier;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        module = new EmployeesModule(dao, notifier);
    }

    @After
    public void tearDown() throws Exception {
        module = null;
        dao = null;
        notifier = null;
    }

    @Test
    public void testGetEmployeeId() throws Exception {
        long id = 5;
        assertEquals(module.getEmployeeId(id), dao.getEmployeeById(id));
    }

    @Test
    public void testGetEmployees() throws Exception{
        assertEquals(module.getEmployees(),dao.getEmployees());
    }

    @Test
    public void testAddOnEmployeeAddedListener(){
        IEmployeesModule.OnEmployeeEventListener listener = mock(IEmployeesModule.OnEmployeeEventListener.class);
        notifier.addOnEmployeeAddedListener(listener);
        verify(notifier).addOnEmployeeAddedListener(listener);
    }
}