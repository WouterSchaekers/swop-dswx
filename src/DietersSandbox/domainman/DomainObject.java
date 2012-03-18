package DietersSandbox.domainman;

public class DomainObject<T>
{
	private final int identifier;
	static int identifiergenerator=0;
	public final T 	value;
	public final int ID;
	DomainObject(T arg, int ID)
	{
		value=arg;
		this.ID=ID;
		identifier=identifiergenerator++;
	}
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof DomainObject<?>)
			return ((DomainObject<?>)o).identifier == identifier;
		return false;
	}
}
