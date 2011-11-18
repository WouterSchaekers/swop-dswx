package users;

public class Doctor extends User implements IDoctor
{
	public Doctor(String name) {
		super(name);
	}

	@Override
	public usertype type() {
		return usertype.Doctor;
	}

}
