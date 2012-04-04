package medicaltest;

import patient.PatientFile;
import scheduler.HospitalDate;
import exceptions.FactoryInstantiationException;

/**
 * A MedicalTestFactory is a factory, used to create MedicalTests.
 */
public abstract class MedicalTestFactory
{
	protected PatientFile patientFile_;
	protected HospitalDate creationDate_;

	/**
	 * Sets the patientFile of the MedicalTest.
	 * 
	 * @param patientFile
	 *            The patientFile of the MedicalTest.
	 */
	public void setPatientFile(PatientFile patientFile) {
		this.patientFile_ = patientFile;
	}

	/**
	 * Sets the creationDate of the MedicalTest.
	 * 
	 * @param creationDate
	 *            The creationDate of the MedicalTest.
	 */
	public void setCreationDate(HospitalDate creationDate) {
		this.creationDate_ = creationDate;
	}

	/**
	 * Checks whether the patientFile is valid or not.
	 * 
	 * @return True if the factory has a valid patientFile.
	 */
	private boolean isValidPatientFile() {
		return this.patientFile_ != null;
	}

	/**
	 * Checks whether the creationDate is valid or not.
	 * 
	 * @return True if the creationDate is valid.
	 */
	private boolean isValidCreationDate() {
		return this.creationDate_ != null;
	}

	/**
	 * Checks whether the factory is ready for production.
	 * 
	 * @return True if the patientFile and the creationDate is valid.
	 */
	protected boolean isReady() {
		return this.isValidPatientFile() && this.isValidCreationDate();
	}

	
	public abstract MedicalTest create() throws FactoryInstantiationException;

	public abstract MedicalTestFactory newInstance();
}