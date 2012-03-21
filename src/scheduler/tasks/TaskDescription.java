package scheduler.tasks;

import java.util.Collection;
import patient.PatientFile;
import scheduler.HospitalDate;
import scheduler.requirements.Requirement;
import be.kuleuven.cs.som.annotate.Basic;
import exceptions.InvalidAmountException;
import exceptions.InvalidHospitalDateException;

public abstract class TaskDescription
{
	protected PatientFile patientFile_;
	private long duration_;
	private long extraTime_;
	private HospitalDate creationTime_;
	
	public TaskDescription(PatientFile patientFile, long duration, long extraTime, HospitalDate creationTime) throws InvalidAmountException, InvalidHospitalDateException{
		if (!isValidAmountOfExtraTime(extraTime))
			throw new InvalidAmountException(
					"Invalid amount of extra time since system start given to Unscheduled Task");
		if (!isValidSystemTime(creationTime))
			throw new InvalidHospitalDateException(
					"Invalid systemtime given to Unscheduled Task");
		this.patientFile_ = patientFile;
		this.duration_ = duration;
		this.extraTime_ = extraTime;
		this.creationTime_ = creationTime;
	}
	
	private boolean isValidSystemTime(HospitalDate currentSystemTime) {
		return currentSystemTime != null;
	}
	
	private boolean isValidAmountOfExtraTime(long extraTime) {
		return extraTime >= 0;
	}
	
	public abstract Collection<Requirement> getAllRequirements();
	
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
}