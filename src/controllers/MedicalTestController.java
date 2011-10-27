package controllers;

import java.util.Collection;
import scheduler.TreatmentAndMedicalTestScheduler;
import users.Doctor;
import medicaltest.MedicalTest;

public class MedicalTestController extends super0Controller
{
	private TreatmentAndMedicalTestScheduler s;

	public MedicalTestController(LoginController lc,
			ConsultPatientFileController cpf, TreatmentAndMedicalTestScheduler s)
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
