package users;

import java.util.ArrayList;
import java.util.Collection;

class UserTypeManager
{

	private Collection<UserFactory> factories_=new ArrayList<UserFactory>();
	Collection<UserFactory> types() {
		Collection<UserFactory> rv = new ArrayList<UserFactory>();
		for(UserFactory f:factories_)
			rv.add((UserFactory)f.newInstance());
		return rv;
	}
	void add(UserFactory factory)
	{
		if(!factories_.contains(factory))
			factories_.add(factory);
	}
	public boolean contains(UserFactory factory) {
		return factories_.contains(factory);
	}
	
}
