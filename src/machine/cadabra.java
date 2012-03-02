package machine;

import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;

public abstract class cadabra
{
	protected asdf pool;

	cadabra(asdf pool) {
		this.pool = pool;
	}

	public abstract abracadabra build(int serial, String location)
			throws InvalidLocationException, InvalidSerialException;
}
