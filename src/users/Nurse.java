package users;

public class Nurse extends SchedulableUser
{
	public Nurse(String name) {
		super(name);
	}

	@Override
	public usertype type() {
		return usertype.Nurse;
	}
}