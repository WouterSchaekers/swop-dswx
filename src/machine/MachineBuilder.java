package machine;

import system.Location;

/**
 * A MachineBuilder is a builder, used to create a Machine.
 */
public abstract class MachineBuilder
{
	protected Integer serial_;
	protected Location location_;
	protected String loc_;

	/**
	 * Creates a Machine built from the given information.
	 * 
	 * @return A Machine built from the given information.
	 */
	abstract Machine build();

	/**
	 * Returns a new Builder from the same type.
	 */
	abstract MachineBuilder newBuilder();

	/**
	 * Sets the serial of the Machine.
	 * 
	 * @param serial
	 *            The serial of the Machine.
	 */
	public final void setSerial(int serial) {
		this.serial_ = serial;
	}

	/**
	 * Sets the location of the Machine.
	 * 
	 * @param location
	 *            The location of the Machine.
	 */
	public final void setLocation(Location location) {
		this.location_ = location;
	}

	/**
	 * Sets the location of the Machine within the campus.
	 * 
	 * @param location
	 *            The location of the Machine within the campus.
	 */
	public final void setLocationWithinCampus(String location) {
		this.loc_ = location;
	}

	/**
	 * Checks whether the given builder is from the same type.
	 * 
	 * @param builder
	 *            The builder that has to be checked.
	 * @return True if the builder is from the same type.
	 */
	abstract boolean sameType(MachineBuilder builder);
}