package help;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A very basic and simple FIFO queue
 * 
 * @author Stefaan
 * 
 * @param <T>
 *            The type of the elements in the FIFO queue
 */
public class FifoQueue<T> implements Iterable<T>, Iterator<T>
{
	private ArrayList<T> queue = new ArrayList<T>();

	/**
	 * Adds element to queue.
	 * 
	 * @param element
	 *            element to add
	 */
	public void push(T element) {
		queue.add(element);
	}

	/**
	 * Removes first element from queue and returns that element.
	 */
	public T next() {
		return queue.remove(0);
	}

	/**
	 * Removes all elements from queue.
	 */
	public void clear() {
		queue.clear();
	}

	/**
	 * Returns a queue iterator
	 */
	@Override
	public Iterator<T> iterator() {
		return (Iterator<T>) this;
	}

	@Override
	public boolean hasNext() {
		return (queue.isEmpty()) ? false : true;
	}

	@Override
	public void remove() {
		queue.remove(0);
	}

}