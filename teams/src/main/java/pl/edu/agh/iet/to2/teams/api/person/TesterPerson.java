package pl.edu.agh.iet.to2.teams.api.person;

public class TesterPerson implements Member {

	private long personID;
	private String personName;
	private String personPosition;
	
	public TesterPerson(long ID, String name){
		if(name == null)
			throw new NullPointerException();
		
		this.personID = ID;
		this.personName = name;
		this.personPosition = null;
	}
	
	public TesterPerson(long ID, String name, String position){
		if(name == null)
			throw new NullPointerException();
		
		this.personID = ID;
		this.personName = name;
		this.personPosition = position;
	}
	
	@Override
	public long getId() {
		return personID;
	}

	@Override
	public String getName() {
		return this.personName;
	}

	@Override
	public String getPosition() {
		return this.personPosition;
	}


}
