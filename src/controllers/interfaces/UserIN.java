package controllers.interfaces;

import users.UserFactory;

@controllers.PUBLICAPI
public interface UserIN
{

@controllers.PUBLICAPI
	public String getName();
@controllers.PUBLICAPI
	public UserFactoryIN getTypeIN();
}
