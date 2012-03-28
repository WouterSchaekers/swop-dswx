package ui.registerpatient;

import java.util.Map;
import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
import controllers.RegisterPatientController;
import controllers.interfaces.PatientFileIN;

public class RegisterCheckin extends Usecase
{
	RegisterPatientController cpc;
	String name;
	Map<String, PatientFileIN> nameMap;

	public RegisterCheckin(UserinterfaceData data, RegisterPatientController cpc,
			Map<String, PatientFileIN> namePatientFileMap, String name) {
		super(data);
		this.name = name;
		this.nameMap = namePatientFileMap;
		this.cpc = cpc;
	}

	@Override
	public Usecase Execute() {
		try {
			//TODO: er is een verschil tussen register en checkin.
			// Checkin betekent dat de patient file van de patient die zich meldt al bestaat in het ziekenhuis
			// Register betekent dat de patient die zich aan het ziekenhuis meldt nog GEEN patientfile heeft. 
			// Gegeven de 2 bovenstaande zinnen is de usecase register patient inconsistent... 
			//cpc.checkIn(nameMap.get(name));
			System.out.println(name + " has been checked in");
			return new SelectDoctor(data, nameMap.get(name));
		} catch (Exception e) {
			e.getMessage();
			return new SelectUsecase(data);
		}
	}

}
