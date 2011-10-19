package users;

public class Person {
	public Person(String name, int ssid){
		setName(name);
		setSsid(ssid);
	}
	private String name;
	public void setName(String name){
		this.name = name;
	}
	private int ssid;
	public void setSsid(int ssid){
		this.ssid = ssid;
	}
}
