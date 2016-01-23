package pl.edu.agh.iet.to2.employees;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.edu.agh.iet.to2.employees.model.Employee;
import pl.edu.agh.iet.to2.employees.persistence.EmployeeDao;
import pl.edu.agh.iet.to2.employees.persistence.EventNotifier;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EmployeesModuleTest {

    @Mock
    private EmployeeDao employeeDao;

    @Mock
    private EventNotifier eventNotifier;

    private EmployeesModule module;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        module = new EmployeesModule(employeeDao, eventNotifier);
    }

    private List<Employee> createSampleEmployeeList() {
        Employee employee1 = mock(Employee.class);
        Employee employee2 = mock(Employee.class);

        when(employee1.getId()).thenReturn(1l);
        when(employee1.getId()).thenReturn(2l);

        List<Employee> list = new ArrayList<>();
        list.add(employee1);
        list.add(employee2);

        return list;
    }

    @Test
    public void testGetEmployeeId() throws Exception {
        long id = 123;
        Employee mockEmployee = mock(Employee.class);

        when(mockEmployee.getId()).thenReturn(id);
        when(employeeDao.getEmployeeById(id)).thenReturn(mockEmployee);

        IEmployee iEmployee = module.getEmployeeId(id);

        verify(employeeDao).getEmployeeById(id);
        assertEquals(id, iEmployee.getId());
    }

    @Test
    public void testGetEmployees() throws Exception {
        List<Employee> employeeList = createSampleEmployeeList();
        when(employeeDao.getEmployees()).thenReturn(employeeList);

        List<IEmployee> employees = module.getEmployees();

        assertNotNull(employees);
        verify(employeeDao).getEmployees();

        for (IEmployee iEmployee : employees) {
            assertThat(iEmployee, new BaseMatcher<IEmployee>() {
                @Override
                public boolean matches(Object o) {
                    for (Employee employee : employeeList) {
                        if (employee.getId() == iEmployee.getId()) {
                            return true;
                        }
                    }
                    return false;
                }

                @Override
                public void describeTo(Description description) {

                }
            });
        }
    }

    @Test
    public void testGetFilteredEmployees() throws Exception {
        List<Employee> employeeList = createSampleEmployeeList();
        when(employeeDao.getEmployees()).thenReturn(employeeList);

        IEmployeesModule.Predicate predicate = mock(IEmployeesModule.Predicate.class);
        when(predicate.filter(any(Employee.class))).thenReturn(true);

        List<IEmployee> filteredEmployees = module.getFilteredEmployees(predicate);

        for (IEmployee filteredEmployee : filteredEmployees) {
            assertThat(filteredEmployee, new BaseMatcher<IEmployee>() {
                @Override
                public boolean matches(Object o) {
                    IEmployee iEmployee = (IEmployee) o;
                    for (Employee employee : employeeList) {
                        if (employee.getId() == iEmployee.getId()) {
                            return true;
                        }
                    }
                    return false;
                }

                @Override
                public void describeTo(Description description) {

                }
            });
        }
    }

    @Test
    public void testGetFilteredEmployeesWithFalsePredicate() {
        List<Employee> employeeList = createSampleEmployeeList();
        when(employeeDao.getEmployees()).thenReturn(employeeList);

        IEmployeesModule.Predicate predicate = mock(IEmployeesModule.Predicate.class);
        when(predicate.filter(any(Employee.class))).thenReturn(false);

        List<IEmployee> filteredEmployees = module.getFilteredEmployees(predicate);

        assertTrue(filteredEmployees.isEmpty());
    }

    @Test
    public void testAddOnEmployeeUpdatedListener() throws Exception {
        IEmployeesModule.OnEmployeeUpdateListener listener = mock(IEmployeesModule.OnEmployeeUpdateListener.class);
        module.addOnEmployeeUpdatedListener(listener);
        verify(eventNotifier).addOnEmployeeUpdatedListener(listener);
    }

    @Test
    public void testAddOnEmployeeAddedListener() throws Exception {
        IEmployeesModule.OnEmployeeEventListener listener = mock(IEmployeesModule.OnEmployeeEventListener.class);
        module.addOnEmployeeAddedListener(listener);
        verify(eventNotifier).addOnEmployeeAddedListener(listener);
    }

    @Test
    public void testAddOnEmployeeDeletedListener() throws Exception {
        IEmployeesModule.OnEmployeeEventListener listener = mock(IEmployeesModule.OnEmployeeEventListener.class);
        module.addOnEmployeeDeletedListener(listener);
        verify(eventNotifier).addOnEmployeeDeletedListener(listener);
    }

    @Test
    public void testGetEmployeeDao() throws Exception {
        EmployeeDao dao = module.getEmployeeDao();

        assertNotNull(dao);
        assertEquals(employeeDao, dao);
    }

}