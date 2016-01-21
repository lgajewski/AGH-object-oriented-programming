package pl.edu.agh.iet.to2.employees;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.edu.agh.iet.to2.employees.model.Employee;
import pl.edu.agh.iet.to2.employees.persistence.EmployeeDao;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class EmployeesModuleTest {

    private EmployeesModule module;

    @Mock
    private EmployeeDao employeeDao;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        module = new EmployeesModule(employeeDao);
    }

    @Test
    public void testGetEmployeeId() throws Exception {
        long id = 5;
        module.getEmployeeId(id);
        verify(employeeDao).getEmployeeById(id);
    }

    @Test
    public void testGetEmployees() throws Exception {
        module.getEmployees();

        verify(employeeDao, times(1)).getEmployees();
    }

    @Test
    public void testGetFilteredEmployees() throws Exception {
        List<Employee> employees = new ArrayList<>();
        IEmployeesModule.Predicate predicate = employee -> true;

        when(employeeDao.getEmployees()).thenReturn(employees);

        List<IEmployee> iEmployeeList = module.getFilteredEmployees(predicate);

        assertEquals(employees.size(), iEmployeeList.size());
        verify(employeeDao, times(1)).getEmployees();
    }

    @Test
    public void testAddOnEmployeeUpdatedListener() throws Exception {
        IEmployeesModule.OnEmployeeEventListener listener = mock(IEmployeesModule.OnEmployeeEventListener.class);

        module.addOnEmployeeAddedListener(listener);

        verify(employeeDao).addOnEmployeeAddedListener(listener);
    }

}