package controllers.interfaces;

/**
 * Interface representing a User.
 */
@controllers.PUBLICAPI
public interface UserIN
{

	/**
	 * @return The name of the User.
	 */
	@controllers.PUBLICAPI
	public String getName();

	/**
	 * @return The type of the User.
	 */
	@controllers.PUBLICAPI
	public UserFactoryIN getTypeIN();
}