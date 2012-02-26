package controllers;

import exceptions.InvalidHospitalStateException;
import system.HospitalState;

class MasterController
{
	protected HospitalState hospitalState;
	MasterController(HospitalState hospitalState) throws InvalidHospitalStateException {
		if(!isValidHospitalState(hospitalState)){
			throw new InvalidHospitalStateException("lskdjflke");
		}
		this.hospitalState = hospitalState;
	}
	
	private boolean isValidHospitalState(HospitalState hospitalState){
		return hospitalState != null;
	}
	
}
