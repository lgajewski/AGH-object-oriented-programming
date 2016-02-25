package pl.edu.agh.iet.to2.employees.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EmployeeTest {

    Employee employee;

    @Before
    public void setUp() throws Exception {
        employee = new Employee();
    }

    @After
    public void tearDown() throws Exception {
        employee = null;
    }

//    @Test
//    public void testGetId() throws Exception {
//        long id = 0;
//        assertEquals(id,employee.getId());
//    }

    @Test
    public void testSetId() throws Exception {
        long id = 3;
        employee.setId(id);
        assertEquals(id, employee.getId());
    }

    @Test
    public void testGetName() throws Exception {

    }

    @Test
    public void testSetName() throws Exception {
        String name = "Ala";
        employee.setName(name);
        assertEquals(name, employee.getName());
    }

    @Test
    public void testGetSurname() throws Exception {

    }

    @Test
    public void testSetSurname() throws Exception {
        String surname = "Nowak";
        employee.setSurname(surname);
        assertEquals(surname, employee.getSurname());
    }

    @Test
    public void testGetOccupation() throws Exception {

    }

    @Test
    public void testSetOccupation() throws Exception {
        String occupation = "Java Developer";
        employee.setOccupation(occupation);
        assertEquals(occupation, employee.getOccupation());
    }

    @Test
    public void testGetFullName() throws Exception {
        String name = "Jan";
        String surname = "Nowak";
        employee.setName(name);
        employee.setSurname(surname);
        assertEquals("Jan Nowak", employee.getName() + " " + employee.getSurname());
    }

//    @Test
//    public void testUpdate() throws Exception {
//
//    }
}