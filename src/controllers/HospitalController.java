package controllers;

import system.Hospital;
import exceptions.InvalidHospitalException;

/**
 * This controller is the parent controller of all controllers that require a
 * Hospital to fetch data from.
 */
public class HospitalController
{
	protected Hospital hospital;
	protected int campus;
	
	HospitalController(Hospital hospital)
			throws InvalidHospitalException {
		if (!isValidHospitalState(hospital)) {
			throw new InvalidHospitalException("Exception: invalid hospital!");
		}
		this.hospital = hospital;
	}

	private boolean isValidHospitalState(Hospital hospital) {
		return hospital != null;
	}

}
