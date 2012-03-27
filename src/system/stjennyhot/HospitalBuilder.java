package system.stjennyhot;

import java.io.DataOutputStream;
import java.io.IOException;
import system.Hospital;

public abstract class HospitalBuilder
{
	protected Hospital hospital;
	protected final DataOutputStream log;
	public HospitalBuilder(Hospital hospital,DataOutputStream log2)
	{
		this.hospital=hospital;
		this.log=log2;
	}
	/**
	 * Builds a part of the hospital
	 */
	public abstract void build();
	public final void write(String string)
	{
		
		try {
			log.writeBytes(string);
		} catch (IOException e) {
			throw new Error("failed writing string:" + string);
		}
	}
}
