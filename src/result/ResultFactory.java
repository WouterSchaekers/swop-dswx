package result;

import exceptions.FactoryInstantiationException;

public interface ResultFactory
{
	public Result create() throws FactoryInstantiationException;
}
