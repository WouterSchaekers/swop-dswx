package DietersSandbox.domainman;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DomainObjectManager<T>
{
	/**
    * Checks if the object that was passed out is the samne object as the objec passed out by the getfactories
    * method
    */

	private Map<Integer,DomainObject<T>> _obs = new HashMap<Integer, DomainObject<T>>();
	private int i = 0;
	public Collection<DomainObject<T>> transform(Collection<T> domainObjects)
	{
		ArrayList<DomainObject<T>> rv = new ArrayList<DomainObject<T>>();
		for(T t:domainObjects)
		{
			DomainObject<T> dom = new DomainObject<T>(t, i);
			rv.add(dom);
			_obs.put(i, dom);
			i++;
		}
		return rv;
	}
	public T get(DomainObject<T> object) throws Exception
	{
		if(_obs.get(object.ID).equals(object))
			throw new Exception("this object was created outside the domain");
		
		return _obs.get(object.ID).value;
	}
	public void clear()
	{
		_obs.clear();
		i=0;
	}
}
