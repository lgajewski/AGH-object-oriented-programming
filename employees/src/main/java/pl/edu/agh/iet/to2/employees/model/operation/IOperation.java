package pl.edu.agh.iet.to2.employees.model.operation;

import java.util.Date;

public interface IOperation<T> {

    T getValue();

    Date getDate();
}
