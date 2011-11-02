package machine;

import task.Resource;

/**
 * This class represents a machine in the hospital.
 */
public class Machine implements Resource
{
	private static int serialNow=0;
	private int serial = -1;
	private String location = "";
	
	/**
	 * Default constructor.
	 * @param serial
	 * The serial of this machine.
	 */
	public Machine(String location) {
		this.serial = serialNow++;
		this.location = location;
	}
	
	/**
	 * @return The serial of this machine.
	 */
	public int getSerial() {
		return this.serial;
	}

	/**
	 * @return The location of this machine.
	 */
	public String getLocation() {
		return this.location;
	}
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Machine)
			return ((Machine)o).serial==this.serial;
		return false;
		
	}
}
