package system.stjennyhot;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import exceptions.InvalidCampusException;
import system.Hospital;

public class CampusBuilder extends HospitalBuilder
{
	String campus1 = "jozef";
	String campus2 = "maria";
	public CampusBuilder(Hospital hospital,DataOutputStream log) {
		super(hospital,log);
	}

	@Override
	public void build() {
		try {
			this.hospital.addCampus(campus1);
		} catch (InvalidCampusException e) {
			
				write("Error creating "+campus1+" at campusbuilder");
			System.exit(0);
		}
		MetaBuilder.writeln(log,"succesfully created campus:"+campus1);
		
		try {
			this.hospital.addCampus(campus2);
		} catch (InvalidCampusException e) {
			write("Error creating "+campus2+" at campusbuilder");
			System.exit(0);
		}
		MetaBuilder.writeln(log,"succesfully created campus:"+campus2);
			}

}
