package users;

public class Doctor extends User
{
	public Doctor(String name) {
		super(name);
	}

	@Override
	public usertype type() {
		return usertype.Doctor;
	}
}
