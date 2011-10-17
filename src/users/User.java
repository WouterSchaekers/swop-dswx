package users;

public abstract class User
{
	public User(String name) {
		this.name = name;
	}

	String name;
	public String getName(){return name;}
}
