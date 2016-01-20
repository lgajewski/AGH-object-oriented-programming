package pl.edu.agh.iet.to2.teams.api.person;

import java.math.BigDecimal;

public class TesterPerson implements Member {

	private long personID;
	private String name;
	private String occupation;

	
	public TesterPerson(long ID, String name){
		if(name == null)
			throw new NullPointerException();
		
		this.personID = ID;
		this.name = name;
		this.occupation = null;
	}
	
	public TesterPerson(long ID, String name, String position){
		if(name == null)
			throw new NullPointerException();
		
		this.personID = ID;
		this.name = name;
		this.name = position;
	}
	
	@Override
	public long getId() {
		return personID;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getSurname() {
		return null;
	}

	@Override
	public String getOccupation() {
		return null;
	}

	@Override
	public BigDecimal getSalary() {
		return null;
	}

    public String toString(){
        return "Name: " + getName()
                + ", Occupation: " + getOccupation();
    }


}
