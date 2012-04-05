package result;

import exceptions.FactoryInstantiationException;
import exceptions.InvalidResultException;

public interface ResultHolder
{
	public ResultFactory get();
	public Result give(ResultFactory builder) throws InvalidResultException,FactoryInstantiationException;
	public Result getResult();
}
