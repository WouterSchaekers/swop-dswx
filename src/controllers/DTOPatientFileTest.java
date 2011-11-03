package controllers;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import patient.PatientFile;

public class DTOPatientFileTest
{

	String name;
	PatientFile file;
	
	@Before
	public void setUp(){
		name = "Inspiration is killed";
		file = new PatientFile(name);
	}
	
	@Test
	public void creationSuccess() {
		DTOPatientFile dtoPatientFile = new DTOPatientFile(file);
	}
	
	@Test
	public void getName(){
		DTOPatientFile dtoPatientFile = new DTOPatientFile(file);
		assertTrue(dtoPatientFile.getName().equals(name));
	}
	
	@Test
	public void getPatientFile(){
		DTOPatientFile dtoPatientFile = new DTOPatientFile(file);
		assertTrue(dtoPatientFile.getPatientFile() == file);
	}
	
	@Test
	public void isDischarged(){
		DTOPatientFile dtoPatientFile = new DTOPatientFile(file);
		file.discharge();
		assertTrue(dtoPatientFile.isDischarged());
	}

}
