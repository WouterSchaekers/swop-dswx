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
	public ResultFactory get();

	/**
	 * Gives a result, based on the information of the builder.
	 * 
	 * @param resultFactory
	 *            The builder the result will be based on.
	 * @return The Result based on the ResultFactory.
	 * @throws InvalidResultException
	 * @throws FactoryInstantiationException
	 */
	public Result give(ResultFactory resultFactory) throws InvalidResultException, FactoryInstantiationException;

	/**
	 * @return The Result of the ResultHolder.
	 */
	public Result getResult();
}