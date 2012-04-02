package controllers.interfaces;

import java.util.Collection;
import users.Doctor;

public interface DiagnoseIN
{

	public boolean canBeReplacedWith(String newDiag, String newComplaints, Doctor newAttending, Doctor newSecOp);

	public DoctorIN getAttending();

	public String getComplaints();

	public String getDiagnose();

	public Collection<TaskIN> getTreatments();
	
	public boolean isApproved();
	
	public DoctorIN needsSecOpFrom();
}
