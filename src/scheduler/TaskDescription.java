package scheduler;

import java.util.Collection;
import be.kuleuven.cs.som.annotate.Basic;
import exceptions.InvalidAmountException;
import exceptions.InvalidHospitalDateException;
import patient.PatientFile;

public abstract class TaskDescription
{
	private PatientFile patientFile;
	private long duration;
	private long extraTime;
	private boolean backToBack;
	private HospitalDate creationTime;
	
	@Basic
	public PatientFile getPatientFile(){
		return this.patientFile;
	}
	
	@Basic
	public long getExtraTime() {
		return this.extraTime;
	}

	@Basic
	public long getDuration() {
		return this.duration;
	}

	@Basic
	public HospitalDate getCreationTime() {
		return new HospitalDate(this.creationTime);
	}

	@Basic
	public boolean isBackToBack() {
		return this.backToBack;
	}
	
	public TaskDescription(PatientFile _patientFile, long _duration, long _extraTime, boolean _backToBack, HospitalDate _creationTime) throws InvalidAmountException, InvalidHospitalDateException{
		if (!isValidAmountOfExtraTime(_extraTime))
			throw new InvalidAmountException(
					"Invalid amount of extra time since system start given to Unscheduled Task");
		if (!isValidSystemTime(_creationTime))
			throw new InvalidHospitalDateException(
					"Invalid systemtime given to Unscheduled Task");
		this.patientFile = _patientFile;
		this.duration = _duration;
		this.extraTime = _extraTime;
		this.backToBack = _backToBack;
		this.creationTime = _creationTime;
	}
	
	private boolean isValidSystemTime(HospitalDate _currentSystemTime) {
		return _currentSystemTime != null;
	}
	
	private boolean isValidAmountOfExtraTime(long _extraTime) {
		return _extraTime >= 0;
	}
	
	public abstract Collection<Requirement> getAllRequirements();
	public abstract boolean canBeScheduled();
}