package users;

public abstract class User
{
	public User(String name, int ssid) {
		this.name = name;
		this.ssid = ssid;
	}

	private String name;
	public String getName(){return name;}
	private int ssid;
	public int getSsid(){return ssid;}
}
