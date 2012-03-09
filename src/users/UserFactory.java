package users;

import exceptions.FactoryInstantiationException;
import exceptions.InvalidNameException;

public class UserFactory
{
	protected String name ="";
	
	public void setName(String name) {
		this.name = name;
	}
	
	public User getNewStaffMember(String name, String type) throws InvalidNameException {
		if(type.equals("Nurse"))
			return new Nurse(name);
		else if (type.equals("Doctor"))
			return new Doctor(name);
		else if (type.equals("Hospital Admin"))
			return 
		return null;
	}
}
