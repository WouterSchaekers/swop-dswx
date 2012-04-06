package result;

/**
 * Class representing a NoResultFactory.
 */
@result.ResutlsAPI
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