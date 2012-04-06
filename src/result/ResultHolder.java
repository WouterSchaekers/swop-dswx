package result;

import exceptions.FactoryInstantiationException;
import exceptions.InvalidResultException;

/**
 * Interface for maintaining Results.
 */
public interface ResultHolder
{
	/**
	 * @return A ResultFactory of the correct type.
	 */
	@controllers.PUBLICAPI
	public ResultFactory getResultFactory();

	/**
	 * Gives a result, based on the information of the factory.
	 * 
	 * @param resultFactory
	 *            The builder the result will be based on.
	 * @return The Result based on the ResultFactory.
	 * @throws InvalidResultException
	 * 			The given factory is from the wrong type.
	 * @throws FactoryInstantiationException
	 * 			The Factory was not ready yet.
	 */
	public Result give(ResultFactory resultFactory) throws InvalidResultException, FactoryInstantiationException;

	/**
	 * @return The Result of the ResultHolder.
	 */
	@controllers.PUBLICAPI
	public Result getResult();
}