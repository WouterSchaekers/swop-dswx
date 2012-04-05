package result;
/**
 * Builds no result.
 *
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
