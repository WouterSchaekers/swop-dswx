package help;

/**
 * Interface representing a Filter
 */
public interface Filter
{
	/**
	 * Checks whether the given object is allowed by the Filter.
	 * 
	 * @param arg
	 *            The object that has to be checked.
	 * @return True if the given object is allowed by the Filter.
	 */
	public <T> boolean allows(T arg);
}