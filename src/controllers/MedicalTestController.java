package controllers;

import java.util.Collection;
import scheduler.Scheduler;
import users.Doctor;
import medicaltest.MedicalTest;

public class MedicalTestController extends super0Controller
{
	private Scheduler s;

	public MedicalTestController(LoginController lc,
			ConsultPatientFileController cpf, Scheduler s)
			throws IllegalArgumentException {
		super(lc, cpf);
		this.s = s;
	}

	public void orderMedicalTest(Doctor d, MedicalTest m) {

	}

	public Collection<MedicalTest> avialableMedicalTests() {
		return MedicalTest.availableTests();
	}
}
