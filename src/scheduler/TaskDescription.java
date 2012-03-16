package scheduler;

import java.util.Collection;
import be.kuleuven.cs.som.annotate.Basic;
import exceptions.InvalidAmountException;
import exceptions.InvalidHospitalDateException;
import patient.PatientFile;

public abstract class TaskDescription
{
	private PatientFile patientFile_;
	private long duration_;
	private long extraTime_;
	private HospitalDate creationTime_;
	
	@Basic
	public PatientFile getPatientFile(){
		return this.patientFile_;
	}
	
	@Basic
	public long getExtraTime() {
		return this.extraTime_;
	}

	@Basic
	public long getDuration() {
		return this.duration_;
	}

	@Basic
	public HospitalDate getCreationTime() {
		return new HospitalDate(this.creationTime_);
	}
	
	public TaskDescription(PatientFile _patientFile, long _duration, long _extraTime, HospitalDate _creationTime) throws InvalidAmountException, InvalidHospitalDateException{
		if (!isValidAmountOfExtraTime(_extraTime))
			throw new InvalidAmountException(
					"Invalid amount of extra time since system start given to Unscheduled Task");
		if (!isValidSystemTime(_creationTime))
			throw new InvalidHospitalDateException(
					"Invalid systemtime given to Unscheduled Task");
		this.patientFile_ = _patientFile;
		this.duration_ = _duration;
		this.extraTime_ = _extraTime;
		this.creationTime_ = _creationTime;
	}
	
	private boolean isValidSystemTime(HospitalDate _currentSystemTime) {
		return _currentSystemTime != null;
	}
	
	private boolean isValidAmountOfExtraTime(long _extraTime) {
		return _extraTime >= 0;
	}
	
	public abstract Collection<Requirement> getAllRequirements();
}