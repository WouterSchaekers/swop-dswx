package system.stjennyhot;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import system.Hospital;

public class MetaBuilder
{
	protected Hospital hospital;
	private Collection<HospitalBuilder> builders;
	private String loglocatoin;
	private DataOutputStream log;
	private Collection<HospitalBuilder> builders() {
		return builders;
	}

	public MetaBuilder(String loglocation) {
		
		hospital = new Hospital();
		builders = new ArrayList<HospitalBuilder>();
		this.loglocatoin = loglocation;
		
		try {
			OutputStream str = new FileOutputStream(loglocatoin);
			str.write(new String().getBytes());
			str.close();
			this.log = new DataOutputStream(new FileOutputStream(loglocation,
					true));
			
		} catch (FileNotFoundException e1) {
			throw new Error("File to write to is wrong!");
		} catch (IOException e) {
			throw new Error("File to write to is wrong!");
		}
		writeln(log, "Creating hospital system, v3.0");
		initializeBuilders(log);
		

	}

	public static final void writeln(DataOutputStream log, String string) {
		try {
			log.writeBytes(string+"\n");
			log.flush();
		} catch (IOException e) {
			throw new Error("failed writing string:" + string);
		}
	}

	private void initializeBuilders(DataOutputStream log) {
		builders.add(new CampusBuilder(hospital, log));

	}

	public Hospital build() {
		for (HospitalBuilder builder : builders())
			builder.build();
		try {
			log.close();
		} catch (IOException e) {
			throw new Error("");
		}
		return hospital;
	}

	public void a() {
		// Step one
	}
	
}
