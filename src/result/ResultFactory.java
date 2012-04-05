package result;

import exceptions.FactoryInstantiationException;

/**
 * Interface representing a ResultFactory.
 */
public interface ResultFactory
{
	/**
	 * Creates and returns a Result.
	 * 
	 * @return The result.
	 * @throws FactoryInstantiationException
	 *             The factory was not fully instantiated yet.
	 */
	public Result create() throws FactoryInstantiationException;
}