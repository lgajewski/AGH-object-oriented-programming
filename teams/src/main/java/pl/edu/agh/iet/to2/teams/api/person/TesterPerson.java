package pl.edu.agh.iet.to2.teams.api.person;

import javafx.beans.property.SimpleObjectProperty;

import java.math.BigDecimal;

public class TesterPerson implements Member {

	private long personID;
	private SimpleObjectProperty<String> name;
	private SimpleObjectProperty<String> occupation;

	
	public TesterPerson(long ID, String name){
		if(name == null)
			throw new NullPointerException();
		
		this.personID = ID;
		this.name = new SimpleObjectProperty<String>(name);
		this.occupation = new SimpleObjectProperty<String>(null);
	}
	
	public TesterPerson(long ID, String name, String position){
		if(name == null)
			throw new NullPointerException();
		
		this.personID = ID;
		this.name = new SimpleObjectProperty<String>(name);
		this.occupation = new SimpleObjectProperty<String>(position);
	}
	
	@Override
	public long getId() {
		return this.personID;
	}

	@Override
	public String getName() {
		return this.name.get();
	}

	@Override
	public String getSurname() {
		return null;
	}

	@Override
	public String getOccupation() {
		return this.occupation.get();
	}

	@Override
	public BigDecimal getSalary() {
		return null;
	}

	public SimpleObjectProperty<String> getOccupationProperty() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation.set(occupation);
	}

	public SimpleObjectProperty<String> getNameProperty() {
		return name;
	}

	public void setName(String name) {
		this.name.set(name);
	}

    public String toString(){
        return "Name: " + getName()
                + ", Occupation: " + getOccupation();
    }


}
