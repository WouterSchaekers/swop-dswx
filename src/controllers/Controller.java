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
	Metamanager m;

	public Controller() {
	}

	public Controller(Metamanager m) {
		this();
		this.m = m;
	}


}
