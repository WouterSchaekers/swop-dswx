package schedulerold.task.unscheduled.treatment;

import java.util.LinkedList;
import patient.Diagnose;
import patient.PatientFile;
import scheduler.HospitalDate;
import treatment.Medication;
import users.UserManager;
import warehouse.Warehouse;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidOccurencesException;
import exceptions.InvalidResourceException;

public class UnscheduledMedication extends UnscheduledTreatment
{

	/**
	 * @param p
	 * @param diagnose
	 * @param systemTime
	 * @param cast
	 * @param userManager
	 * @throws InvalidResourceException
	 * @throws InvalidDurationException
	 * @throws InvalidOccurencesException
	 * @throws InvalidAmountException
	 * @throws InvalidHospitalDateException
	 */
	public UnscheduledMedication(PatientFile p, Diagnose diagnose,
			HospitalDate systemTime, Medication medication,
			UserManager userManager, Warehouse warehouse)
			throws InvalidResourceException, InvalidDurationException,
			InvalidOccurencesException, InvalidAmountException,
			InvalidHospitalDateException {
		super(p, diagnose, 2 * HospitalDate.ONE_HOUR, systemTime, userManager,
				medication);
		this.warehouse = warehouse;
	}

	@Override
	public boolean canBeScheduled() {
		return super.canBeScheduled() && this.getMedicationPool().size() > 0;
	}

	@Override
	public LinkedList<Integer> getOccurences() {
		LinkedList<Integer> rv = new LinkedList<Integer>();
		rv.add(1);
		return rv;
	}

	protected LinkedList<Medication> getMedicationPool() {
//		LinkedList<Medication> medicationPool = new LinkedList<Medication>();
//		medicationPool.addAll(Collections.filter(
//				this.warehouse.getMedication(), new Filter()
//				{
//					@Override
//					public <T> boolean allows(T arg) {
//						return arg.getClass().equals(
//								UnscheduledMedication.this.getMytreatment()
//										.getClass());
//					}
//				}));
//		return medicationPool;
		return null;
		//TODO: fix
	}

}