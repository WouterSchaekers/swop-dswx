package patient;

import java.util.*;
import scheduler.HospitalDate;
import be.kuleuven.cs.som.annotate.Basic;
import controllers.interfaces.PatientFileIN;
import exceptions.*;

/**
 * This class represents the patient file of a patient.
 */
public class PatientFile implements PatientFileIN 
{

	private String name = "";
	/**
	 * All the Diagnosis for this patient.
	 */
	private Collection<Diagnose> diagnosis = new ArrayList<Diagnose>();
	private boolean discharged = false;
	private ArrayList<HospitalDate> xrays = new ArrayList<HospitalDate>();
	
	/**
	 * Default Constructor.
	 * 
	 * @param patientname
	 *            The name of the patient to whom this patient file belongs to.
	 * @throws InvalidNameException
	 *             if(!isValidName(patientname))
	 */
	public PatientFile(String patientname) throws InvalidNameException {
		if(!isValidName(patientname))
			throw new InvalidNameException("The given patientname is not valid!");
		this.name = patientname;
	}
	
	/**
	 * This method will add a Diagnose to this PatientFile.
	 * 
	 * @param d
	 *            The Diagnose to add.
	 * @throws InvalidDiagnoseException
	 * if(!isValidDiagnose(d)) 
	 */
	public void addDiagnosis(Diagnose d) throws InvalidDiagnoseException {
		if(!isValidDiagnose(d))
			throw new InvalidDiagnoseException("The given Diagnose is not a valid!");
		this.diagnosis.add(d);
	}

	/**
	 * This function checks in an already registered patient in the hospital.
	 */
	public void checkIn() {
		this.discharged = false;
	}

	/**
	 * This function discharges this patient.
	 */
	public void discharge() {
		this.discharged = true;
	}

	/**
	 * @return True if d is a valid Diagnose.
	 */
	private boolean isValidDiagnose(Diagnose d) {
		return d != null;
	}
	
	/**
	 * @return True if d is a valid name.
	 */
	private boolean isValidName(String n) {
		return !n.equals("");
	}
	
	/**
	 * This method must be called if an XRrayScan is ordered for this patient.
	 * It is needed to keep track of the amount of xrays a patient has had in
	 * the past year.
	 * 
	 * @param d
	 *            The date on which the XRay is scheduled.
	 */
	public void addXRay(HospitalDate d) {
		xrays.add(d);
	}
	
	/**
	 * @param curDate
	 * The current system time.
	 * @return The amount of xrays this patient has had in the last year.
	 */
	public int amountOfXraysThisYear(HospitalDate curDate) {
		for(HospitalDate xr : this.xrays) {
			if(xr.getTimeBetween(curDate) > HospitalDate.ONE_YEAR)
				xrays.remove(xr);
		}
		return xrays.size();
	}
	
	@Basic
	public boolean isDischarged() {
		return this.discharged;
	}

	@Basic
	public String getName() {
		return this.name;
	}

	@Basic
	public Collection<Diagnose> getDiagnosis() {
		return new ArrayList<Diagnose>(this.diagnosis);
	}

	@Override
	public String toString() {
		return name;
	}
}
