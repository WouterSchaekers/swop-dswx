package ui.usecases;

import java.util.Collection;
import ui.UIData;
import ui.UseCase;
import controllers.EnterDiagnoseController;
import controllers.interfaces.DiagnoseIN;
import controllers.interfaces.DoctorIN;
import exceptions.InvalidComplaintsException;
import exceptions.InvalidDiagnoseException;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileOpenController;

public class EnterDiagnose extends UseCase
{
	
	private EnterDiagnoseController controller;

	public EnterDiagnose(UIData data) throws InvalidLoginControllerException, InvalidHospitalException, InvalidPatientFileOpenController {
		super(data, 115);
		controller = new EnterDiagnoseController(data.getLoginController(), data.getConsultPatientFileopenController());
	}

	@Override
	public UseCase execute() {
		printLn("Enter a diagnose for patient:"+data.getConsultPatientFileopenController().getPatientFile().getPatientIN().getName());
		print("What are the complaints of the user?");
		String complaints = read();
		print("What is your diagnose?");
		String diagnose = read();
		printLn("Do you want a second opinion");
		boolean secondop = Selector.yesNoSelector.get();
		DiagnoseIN createdDiagnose = null;
		if(!secondop)
		{
			try {
			createdDiagnose=controller.enterDiagnose(complaints, diagnose);
			} catch (InvalidDiagnoseException e) {
				printLn("Diagnose invalid");
				return mm();
			}  catch (InvalidComplaintsException e) {
				printLn("Complaints were invalid"+e.getMessage());
				return mm();
			}
			printLn("Diagnose was sucesfully entered");
			printLn("Complaints: "+createdDiagnose.getComplaintsIN());
			printLn("Diagnose:"+createdDiagnose.getDiagnoseIN());
			printLn("Attending doctor:"+createdDiagnose.getAttendingIN().getName());
			printLn("No second opinion required");
			return mm();
		}else
		{
			Collection<DoctorIN> doctors =controller.getAllDoctors();
			doctors.remove(data.getLoginController().getUserIN());
			if(doctors.isEmpty())
			{
				printLn("No doctors in this hospital to give a second opinion.");
			}
			Selector<DoctorIN> docSelector = new Selector<DoctorIN>(doctors, Selector.docdisplayer);
			DoctorIN selected = docSelector.get();
			try {
			createdDiagnose=controller.enterDiagnoseWithSecondOpinion(diagnose, complaints, selected);
			}  catch (InvalidDiagnoseException e) {
				printLn("Diagnose invalid");
				return mm();
			}  catch (InvalidComplaintsException e) {
				printLn("Complaints were invalid"+e.getMessage());
				return mm();
			}
			printLn("Diagnose was sucesfully entered");
			printLn("Complaints: "+createdDiagnose.getComplaintsIN());
			printLn("Diagnose:"+createdDiagnose.getDiagnoseIN());
			printLn("Attending doctor:"+createdDiagnose.getAttendingIN().getName());
			printLn("Second opinon doctor"+createdDiagnose.needsSecOpFromIN().getName());
			return mm();
		}
	}
	@Override
	public String toString()
	{
		return "Enter diagnose";
	}

}
