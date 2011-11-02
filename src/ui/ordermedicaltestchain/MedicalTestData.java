package ui.ordermedicaltestchain;

import java.util.ArrayList;
import java.util.Arrays;
import medicaltest.MedicalTestTypes;

public class MedicalTestData
{
	ArrayList<MedicalTestTypes> types = new ArrayList<MedicalTestTypes>(Arrays.asList(MedicalTestTypes.values()));
	MedicalTestTypes testOfChoice;
	
	public void setTestOfChoice(MedicalTestTypes m) {
		this.testOfChoice = m;
	}

}
