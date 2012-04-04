package medicaltest;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Observable;
import machine.UltraSoundScanner;
import patient.PatientFile;
import scheduler.HospitalDate;
import scheduler.requirements.Requirement;
import scheduler.requirements.RequirementType;
import scheduler.requirements.SpecificRequirement;
import users.Nurse;

public class UltraSoundScan extends MedicalTest
{
	private final String scaninfo;
	private final boolean recordVid;
	private final boolean recordImages;

	UltraSoundScan(PatientFile patientFile, HospitalDate creationTime, String scaninfo, boolean recordVid, boolean recordImages) {
		super(patientFile, 30 * HospitalDate.ONE_MINUTE, creationTime);
		this.scaninfo = scaninfo;
		this.recordVid = recordVid;
		this.recordImages = recordImages;
	}

	public String getScaninfo() {
		return scaninfo;
	}

	public boolean hasVideoRecordingEnabled() {
		return recordVid;
	}

	public boolean hasImageRecordingEnabled() {
		return recordImages;
	}

	@Override
	public Collection<Requirement> getAllRequirements() {
		Collection<Requirement> requirements = new LinkedList<Requirement>();
		requirements.add(new SpecificRequirement(this.patientFile_.getPatient(), false));
		requirements.add(new RequirementType<UltraSoundScanner>(UltraSoundScanner.class, false, 1));
		requirements.add(new RequirementType<Nurse>(Nurse.class, true, 1));
		return requirements;
	}

	@Override
	public Collection<Observable> getObservables() {
		// TODO Auto-generated method stub
		return null;
	}
}