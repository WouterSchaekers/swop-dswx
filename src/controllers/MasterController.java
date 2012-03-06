package controllers;

import system.Hospital;
import exceptions.InvalidHospitalStateException;

/**
 * 
 */
//TODO: doc
class MasterController
{
	protected Hospital hospitalState;

	MasterController(Hospital hospitalState)
			throws InvalidHospitalStateException {
		if (!isValidHospitalState(hospitalState)) {
			throw new InvalidHospitalStateException("lskdjflke");
		}
		this.hospitalState = hospitalState;
	}

	private boolean isValidHospitalState(Hospital hospitalState) {
		return hospitalState != null;
	}

}
