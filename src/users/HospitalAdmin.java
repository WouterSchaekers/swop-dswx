package users;

public class HospitalAdmin extends User
{

	protected HospitalAdmin(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public usertype type() {
		// TODO Auto-generated method stub
		return usertype.HospitalAdmin;
	}

}
