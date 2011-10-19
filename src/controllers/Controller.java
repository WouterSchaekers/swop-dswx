package controllers;

import java.util.Iterator;
import help.FifoQueue;

/**
 * Parent class of all controllers.
 * 
 * @author Dieter, Stefaan
 * 
 */
public class Controller
{
	protected FifoQueue<String> q;
	Metamanager m;

	public Controller() {
		q = new FifoQueue<String>();
	}

	public Controller(Metamanager m) {
		this();
		this.m = m;
	}

	/**
	 * Used to iterate over responses from the user.
	 * 
	 * @return An iterable set of strings.
	 */
	public Iterable<String> response() {
		return new Iterable<String>()
		{
			@Override
			public Iterator<String> iterator() {
				return q.iterator();
			}
		};
	}
}
