package users;

public class HospitalAdmin extends User
{

	protected HospitalAdmin(String name) {
		super(name);
	}

	@Override
	public usertype type() {
		return usertype.HospitalAdmin;
	}

}
