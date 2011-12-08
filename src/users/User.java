package users;

import scheduler.TimeTable;
import controllers.interfaces.UserIN;

public abstract class User implements UserIN
{
	protected TimeTable timeTable;
	public abstract usertype type();

	protected String name;

	protected User(String name) {
		this.name = name;
		this.timeTable = new TimeTable();
	}

	public String getName() {
		return name;
	}

	public enum usertype
	{
		Doctor, Nurse, HospitalAdmin;
	}
	
	public TimeTable getTimeTable(){
		return this.timeTable;
	}
}
