package help;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Help class presenting methods that can filter.
 */
public final class Collections
{
	/**
	 * Filters a Collection with a certain Filter.
	 * 
	 * @param toFilter
	 *            The Collection that has to be filtered.
	 * @param filter
	 *            The Filter that will filter the Collection.
	 * @return A Collection of toFilter items that are allowed by the Filter.
	 */
	public static final <T> Collection<T> filter(Collection<T> toFilter, Filter filter) {
		ArrayList<T> rv = new ArrayList<T>();
		for (T element : toFilter)
			if (filter.allows(element))
				rv.add(element);
		return rv;
	}
}