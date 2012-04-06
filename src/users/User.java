package users;

import controllers.interfaces.UserIN;
import exceptions.InvalidNameException;

/**
 * Class respresenting a User. Every User has a name. We assume all names are
 * unique.
 */
public abstract class User implements UserIN
{
	protected String name_;

	/**
	 * Default constructor. Package visible, since it should only be created by
	 * a UserFactory.
	 * 
	 * @param name
	 *            The name of the User.
	 * @throws InvalidNameException
	 *             The given name is invalid.
	 */
	User(String name) throws InvalidNameException {
		if (!isValidName(name))
			throw new InvalidNameException("Invalid name given in constructor of User!");
		this.name_ = name;
	}

	/**
	 * Returns the name of the User.
	 */
	@Override
	public String getName() {
		return name_;
	}

	/**
	 * Checks whether the given name is valid.
	 * 
	 * @param name
	 *            The name that has to be checked.
	 * @return True if the name is not null and not empty.
	 */
	private boolean isValidName(String name) {
		return name != null && !name.isEmpty();
	}

	/**
	 * @return A UserFactory of the correct type.
	 */
	public abstract UserFactory getType();

	/**
	 * Returns true if the given object is a User and has the same name as this
	 * User.
	 */
	@Override
	public boolean equals(Object o) {
		return o instanceof User && ((User) o).getName().equals(this.name_);
	}
}