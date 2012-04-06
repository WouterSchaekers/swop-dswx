package treatment;

import patient.Diagnose;
import patient.PatientFile;
import scheduler.HospitalDate;
import controllers.interfaces.DiagnoseIN;
import exceptions.FactoryInstantiationException;

/**
 * A TreatmentFactory is an abstract factory, used to create Treatments.
 */
@treatment.TreatmentAPI
public abstract class TreatmentFactory
{

	protected HospitalDate creationDate_;
	protected Diagnose diagnose_;


	/**
	 * Sets the diagnose of the factory.
	 * 
	 * @param diagnose
	 *            The diagnose.
	 */
	@treatment.TreatmentAPI
	public void setDiagnose(DiagnoseIN diagnose) {
		this.diagnose_ = (Diagnose) diagnose;
	}

	/**
	 * Returns the patientFile.
	 * 
	 * @return The patientFile.
	 */
	protected PatientFile getPatientFile() {
		return diagnose_.getPatientFile();
	}

	/**
	 * Sets the creationDate of the facotory.
	 * 
	 * @param creationDate
	 *            The creationDate.
	 */
	public void setCreationDate(HospitalDate creationDate) {
		this.creationDate_ = creationDate;
	}

	/**
	 * Checks whether the patientFile is valid.
	 * 
	 * @return True if the patientFile is not null.
	 */
	private boolean isValidPatientFile() {
		return this.getPatientFile() != null;
	}



	/**
	 * Checks whether the diagnose is valid.
	 * 
	 * @return True if the diagnose is not null.
	 */
	private boolean isValidDiagnose() {
		return this.diagnose_ != null;
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
	 * @return True if the patientFile and the creationDate is valid.
	 */
	protected boolean isReady() {
		return isValidPatientFile() && isValidCreationDate() && isValidDiagnose() ;
	}

	/**
	 * Creates a Treatment built from the given information. Should only be
	 * called in a domain-class!
	 * 
	 * @return A Treatment built from the given information.
	 * @throws FactoryInstantiationException
	 *             The factory was not ready yet.
	 */
	@Deprecated
	public abstract Treatment create() throws FactoryInstantiationException;

	/**
	 * Returns a new instance of the current factory.
	 * 
	 * @return A new instance of the current factory.
	 */
	@treatment.TreatmentAPI
	public abstract TreatmentFactory newInstance();
}