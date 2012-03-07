package controllers;

import system.Hospital;
import exceptions.InvalidHospitalException;

/**
 * This controller is the parent controller of all controllers that require a
 * Hospital to fetch data from.
 */
public class MasterController
{
	protected Hospital hospital;

	MasterController(Hospital hospital)
			throws InvalidHospitalException {
		if (!isValidHospitalState(hospital)) {
			throw new InvalidHospitalException("lskdjflke");
		}
		this.hospital = hospital;
	}

	private boolean isValidHospitalState(Hospital hospital) {
		return hospital != null;
	}

}
