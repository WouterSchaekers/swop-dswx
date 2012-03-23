package users;

import exceptions.InvalidNameException;

public class HospitalAdminFactory extends UserFactory
{

	private String name_;
	public void setName(String name)
	{
		name_=name;
	}
	@Override
	User create() throws InvalidNameException {
		return new HospitalAdmin(name_);
	}
	@Override
	UserFactory newInstance() {
		return new HospitalAdminFactory();
	}

}
