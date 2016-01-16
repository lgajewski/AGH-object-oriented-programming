package pl.edu.agh.iet.to2.employees;

import java.util.List;

public interface IEmployeesModule {

    IEmployee getEmployeeId(long id);

    List<IEmployee> getEmployees();

    List<IEmployee> getFilteredEmployees(Predicate predicate);


    /**
     * OBSERVER PATTERN - ALLOWS TO HANDLE ALL EVENTS
     */

    void addOnEmployeeUpdatedListener(OnEmployeeUpdateListener listener);

    void addOnEmployeeAddedListener(OnEmployeeEventListener listener);

    void addOnEmployeeDeletedListener(OnEmployeeEventListener listener);

    interface Predicate {
        boolean filter(IEmployee employee);
    }

    interface OnEmployeeEventListener {
        void onEvent(IEmployee employee);
    }

    interface OnEmployeeUpdateListener {
        void onUpdate(IEmployee oldEmployee, IEmployee newEmployee);
    }

}