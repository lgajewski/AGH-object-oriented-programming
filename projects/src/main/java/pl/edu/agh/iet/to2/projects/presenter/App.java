package pl.edu.agh.iet.to2.projects.presenter;


import javafx.application.Application;
import javafx.stage.Stage;
import pl.edu.agh.iet.to2.projects.dummy.DummyProjects;
import pl.edu.agh.iet.to2.projects.dummy.DummyTeams;
import pl.edu.agh.iet.to2.projects.dummy.IProjectsSource;
import pl.edu.agh.iet.to2.projects.dummy.ITeamsSource;

public class App extends Application{

    private Stage primaryStage;

    private ProjectPresenter presenter;

    private IProjectsSource projectsSource = new DummyProjects();

    private ITeamsSource teamsSource = new DummyTeams();

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Projects");
        this.presenter = new ProjectPresenter(primaryStage);
        this.presenter.setProjectsSource(projectsSource);
        this.presenter.setTeamsSource(teamsSource);
        this.presenter.initRootLayout();

    }




    public static void main(String[] args) {
        launch(args);

        /*Employee emp = new Employee(1, "Marcin", "Zajda", new Salary(new BigDecimal(5000)), "Junior Developer");

        System.out.println(emp.getOccupation());*/

    }
}