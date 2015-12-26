package pl.edu.agh.iet.to2;

import pl.edu.agh.iet.to2.employees.view.EmployeeTab;

import java.util.Locale;

enum Tab {
    EMPLOYEES(new EmployeeTab());

    private final TabInitializer tab;

    Tab(TabInitializer tab) {
        this.tab = tab;
    }

    public TabInitializer getTab() {
        return tab;
    }

    public String getName() {
        return toString().toLowerCase(Locale.ENGLISH);
    }
}