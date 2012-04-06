package users;

import org.junit.Before;
import org.junit.Test;
import exceptions.InvalidNameException;

public class _UserTest
{	
	UserManager usermanager;
	@Before
	public void voi() throws InvalidNameException
	{
		usermanager	= new UserManager("hospital admin");
	}
	@Test
	public void addFactories()
	{
		
	}
}
