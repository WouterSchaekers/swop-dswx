package ui.enterdiagnosis;

import exceptions.InvalidDiagnoseException;
import exceptions.InvalidDoctorException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileOpenController;
import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;

public class NoSecondOpinionForDiagnose extends EnterDiagnoseSuperClass
{

	NoSecondOpinionForDiagnose(UserinterfaceData data,
			EnterDiagnoseData chaindata) {
		super(data, chaindata);
	}

	@Override
	public Usecase Execute() {
		try {
			chaindata.getController().enterDiagnose(data.getLoginController(), data.getPatientFileOpenController(), chaindata.getDiag());
		} catch (InvalidLoginControllerException e) {
			System.out.println("Priviliged action please log in again");
			return new SelectUsecase(data);
		
		} catch (InvalidPatientFileOpenController e) {
			System.out.println("No patienf file was opened");
			return new SelectUsecase(data);
		
		} catch (InvalidDiagnoseException e) {
			System.out.println("Somethign went wrong with creating the diagnose please try again");
			return new SelectUsecase(data);
		} catch (InvalidDoctorException e) {
			System.out.println("Wrong doctor ");
			return new SelectUsecase(data);
		}
		System.out.println("Diagnose was succesfully entered");
		return new SelectUsecase(data);
	}

}
