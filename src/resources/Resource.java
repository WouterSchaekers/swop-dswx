package resources;
import java.util.Collection;

public abstract class Resource
{
	private int duration;

	public Resource(int duration) {

	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public abstract Collection<Resource> getType();

}
