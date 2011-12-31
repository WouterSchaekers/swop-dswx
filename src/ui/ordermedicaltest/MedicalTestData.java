package ui.ordermedicaltest;

import medicaltest.MedicalTest;
import medicaltest.MedicalTestFactory;
import controllers.MedicalTestController;

public class MedicalTestData
{
	private MedicalTest m;
	private MedicalTestController mc;
	private MedicalTestFactory factory;

	public void setMedTestController(MedicalTestController mc) {
		this.mc = mc;
	}

	public MedicalTestController getMedTestController() {
		return this.mc;
	}

	public void setTest(MedicalTest m) {
		this.m = m;
	}

	public MedicalTest getTest() {
		return this.m;
	}

	public MedicalTestFactory getFactory() {
		// TODO Auto-generated method stub
		return factory;
	}



}
