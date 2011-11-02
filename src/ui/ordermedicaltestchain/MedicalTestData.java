package ui.ordermedicaltestchain;

import java.util.ArrayList;
import java.util.Arrays;
import medicaltest.MedicalTest;
import medicaltest.MedicalTestTypes;

public class MedicalTestData
{
	ArrayList<MedicalTestTypes> types = new ArrayList<MedicalTestTypes>(Arrays.asList(MedicalTestTypes.values()));
	MedicalTestTypes testOfChoice;
	private MedicalTest m;
	
	public void setTestOfChoice(MedicalTestTypes m) {
		this.testOfChoice = m;
	}
	
	public void setTest(MedicalTest m) {
		this.m=m;
	}
	
	public MedicalTest getTest() {
		return this.m;
	}

}
