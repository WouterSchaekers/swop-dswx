package controllers;

import java.util.Iterator;
import help.FifoQueue;

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
