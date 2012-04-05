package controllers;

import system.Hospital;
import exceptions.InvalidHospitalException;

/**
 * This controller is the parent controller of all controllers that require a
 * Hospital to fetch data from.
 */
class HospitalController
{
	protected Hospital hospital;

	/**
	 * Initialises the hospital stored in this controller.
	 * 
	 * @param hospital
	 *            The hospital that this controller depends on.
	 * @throws InvalidHospitalException
	 *             If the given hospital is not a valid one.
	 */
	HospitalController(Hospital hospital) throws InvalidHospitalException {
		if (!isValidHospitalState(hospital)) {
			throw new InvalidHospitalException("Exception: invalid hospital!");
		}
		this.hospital = hospital;
	}

	/**
	 * @return True if hospital is a valid hospital for this hospital
	 *         controller.
	 */
	private boolean isValidHospitalState(Hospital hospital) {
		return hospital != null;
	}

}
