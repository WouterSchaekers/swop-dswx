package patient;

import medicaltest.MedicalTest;
import scheduler.tasks.FinishedTask;

class FinishedMedicalTest<T extends MedicalTest> extends FinishedTask<T>
{

	public FinishedMedicalTest(T description) {
		super(description);
	}

}