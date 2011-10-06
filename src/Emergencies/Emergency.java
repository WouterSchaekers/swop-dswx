package Emergencies;

import dispatching.Location;

public abstract class Emergency
{
	public Emergency(Location loc, int severety) {
		this.loc = loc;
	}

	public Location getLocation() {
		return (Location) loc.clone();
	}

	public int getSeverety() {
		return sev;
	}

	int sev;
	Location loc;
}
