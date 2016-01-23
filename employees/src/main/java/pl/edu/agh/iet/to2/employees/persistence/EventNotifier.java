package pl.edu.agh.iet.to2.employees.persistence;

import pl.edu.agh.iet.to2.employees.IEmployeesModule;
import pl.edu.agh.iet.to2.employees.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EventNotifier {

    private List<IEmployeesModule.OnEmployeeUpdateListener> updateListeners;
    private List<IEmployeesModule.OnEmployeeEventListener> addListeners;
    private List<IEmployeesModule.OnEmployeeEventListener> deleteListeners;

    public EventNotifier() {
        this.updateListeners = new ArrayList<>();
        this.addListeners = new ArrayList<>();
        this.deleteListeners = new ArrayList<>();
    }

    public void notifyAdd(Employee employee) {
        addListeners.forEach(listener -> listener.onEvent(employee));
    }

    public void notifyUpdate(Employee oldEmployee, Employee newEmployee) {
        updateListeners.forEach(listener -> listener.onUpdate(oldEmployee, newEmployee));
    }

    public void notifyDelete(Employee employee) {
        deleteListeners.forEach(listener -> listener.onEvent(employee));
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
