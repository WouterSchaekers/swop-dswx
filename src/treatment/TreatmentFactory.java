package treatment;

import patient.PatientFile;
import scheduler.HospitalDate;
import exceptions.FactoryInstantiationException;
import exceptions.InvalidAmountException;
import exceptions.InvalidHospitalDateException;

public abstract class TreatmentFactory
{
	protected PatientFile patientFile_;
	protected HospitalDate creationDate_;

	/**
	 * Sets the patientFile of the factory.
	 * 
	 * @param patientFile
	 *            The patientFile.
	 */
	public void setPatientFile(PatientFile patientFile) {
		this.patientFile_ = patientFile;
	}

	/**
	 * Sets the creationDate of the facotory.
	 * 
	 * @param creationDate
	 *            The creationDate.
	 */
	public void setCreationDate(HospitalDate creationDate) throws InvalidHospitalDateException {
		this.creationDate_ = creationDate;
	}

	/**
	 * Checks whether the patientFile is valid.
	 * 
	 * @return True if the patientFile is not null.
	 */
	private boolean isValidPatientFile() {
		return this.patientFile_ != null;
	}

	/**
	 * Checks whether the creationDate is valid.
	 * 
	 * @return True if the creationDate is not null.
	 */
	private boolean isValidCreationDate() {
		return this.creationDate_ != null;
	}

	/**
	 * Checks whether the factory is ready for production.
	 * 
	 * @return True if the patientFile is valid and the creationDate is valid.
	 */
	protected boolean isReady() {
		return isValidPatientFile() && isValidCreationDate();
	}

	/**
	 * Creates a Treatment from the given information.
	 * 
	 * @return
	 * @throws FactoryInstantiationException
	 * @throws InvalidAmountException
	 * @throws InvalidHospitalDateException
	 */
	public abstract Treatment create() throws FactoryInstantiationException, InvalidHospitalDateException;
}