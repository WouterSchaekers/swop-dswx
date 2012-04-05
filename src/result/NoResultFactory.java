package result;

/**
 * Class representing a NoResultFactory.
 */
public class NoResultFactory implements ResultFactory
{
	/**
	 * Returns null.
	 */
	@Override
	public Result create() {
		return null;
	}
}