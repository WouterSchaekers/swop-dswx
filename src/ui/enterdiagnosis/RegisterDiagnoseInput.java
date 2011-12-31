
package ui.enterdiagnosis;
import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;

public class RegisterDiagnoseInput extends EnterDiagnoseSuperClass
{

	public RegisterDiagnoseInput(UserinterfaceData data,
			EnterDiagnoseData chaindata) {
		super(data,chaindata);
	}

	@Override
	public Usecase Execute() {
		System.out.println("Would you like a second opinion on this diagnose?");
		System.out.println("Yes:y No:n Quit :q");
		String v = input.nextLine();
		if(v.equalsIgnoreCase("q"))
			return new SelectUsecase(data);
		if(v.equalsIgnoreCase("n"))
			return new NoSecondOpinionForDiagnose(data, chaindata);
		if(v.equalsIgnoreCase("y")){
			return new SelectDoctorForSecondOpinion(data,chaindata);
		}else{
			System.out.println("Entered something wrong");
			return this;
		}
	}

}
