package controllers;

import system.Hospital;
import exceptions.InvalidHospitalException;

/**
 * 
 */
//TODO: doc
class MasterController
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
