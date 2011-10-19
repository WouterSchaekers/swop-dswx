package users;

public abstract class User
{
	public User(String name) {
		this.name = name;
	}

	public User(String name, int ssid) {
		this.name = name;
		this.ssid = ssid;
	}

	protected String name;

	public String getName() {
		return name;
	}

	protected int ssid;

	public int getSsid() {
		return ssid;
	}
}
