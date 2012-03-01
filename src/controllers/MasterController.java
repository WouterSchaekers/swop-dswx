package controllers;

import system.HospitalState;
import exceptions.InvalidHospitalStateException;

/**
 * 
 */
//TODO: doc
class MasterController
{
	protected HospitalState hospitalState;

	MasterController(HospitalState hospitalState)
			throws InvalidHospitalStateException {
		if (!isValidHospitalState(hospitalState)) {
			throw new InvalidHospitalStateException("lskdjflke");
		}
		this.hospitalState = hospitalState;
	}

	private boolean isValidHospitalState(HospitalState hospitalState) {
		return hospitalState != null;
	}

}
