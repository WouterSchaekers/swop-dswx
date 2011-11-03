package users;

public class Nurse extends User
{
	public Nurse(String name) {
		super(name);
	}

	@Override
	public usertype type() {
		return usertype.Nurse;
	}
}
