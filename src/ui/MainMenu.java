package ui;

import java.util.Collection;
import java.util.Stack;
import ui.usecases.AdvanceTime;
import ui.usecases.Checkin;
import ui.usecases.ConsultPatientFile;
import ui.usecases.CreateAppointment;
import ui.usecases.DischargePatient;
import ui.usecases.EnterDiagnose;
import ui.usecases.HospitalEquipmentUseCase;
import ui.usecases.HospitalStaff;
import ui.usecases.Login;
import ui.usecases.Logout;
import ui.usecases.Quit;
import ui.usecases.RegisterPatient;
import ui.usecases.Selector;

public class MainMenu extends UseCase
{
	
	public MainMenu(UIData data) {
		super(data,-1);
	}

	private Collection<UseCase> getAvailable() {
		Collection<UseCase> rv = new Stack<UseCase>();
		addLogin(rv);
		addHospitalEquipment(rv);
		addHospitalStaff(rv);
		addQuit(rv);
		addLougout(rv);
		addCheckin(rv);
		addRegisterPatient(rv);
		addAdvanceTime(rv);
		addCreateAppointment(rv);
		addConsultPatientFile(rv);
		addEnterDiagnose(rv);
		addDischargePatient(rv);
		return rv;
	}

	private void addDischargePatient(Collection<UseCase> rv) {
		try {
			rv.add(new DischargePatient(data));
		} catch (Exception e) {
			;
		}
	}

	private void addConsultPatientFile(Collection<UseCase> rv) {
		try {
			rv.add(new ConsultPatientFile(data));
		} catch (Exception e) {
			;
		}
	}

	private void addEnterDiagnose(Collection<UseCase> rv) {
	try {
		rv.add(new EnterDiagnose(data));
	} catch (Exception e) {
		;
	}
	}

	private void addCreateAppointment(Collection<UseCase> rv) {
		try {
			rv.add(new CreateAppointment(data));
		} catch (Exception  e) {;
		}
		
	}

	private void addAdvanceTime(Collection<UseCase> rv) {
		try {
			rv.add(new AdvanceTime(data));
		} catch (Exception e) {
			;
		}
	}

	private void addRegisterPatient(Collection<UseCase> rv) {
		try {
			rv.add(new RegisterPatient(data));
		} catch (Exception e) {
			;
		}
		
	}

	private void addCheckin(Collection<UseCase> rv) {
		try {
			rv.add(new Checkin(data));
		
		} catch (Exception e) {
		;
		
		}
	}

	private void addLougout(Collection<UseCase> rv) {
		try {
			rv.add(new Logout(data));
		} catch (Exception e) {
			;
		}
		
	}

	private void addQuit(Collection<UseCase> rv) {
		rv.add(new Quit(data));
		
	}

	private void addHospitalStaff(Collection<UseCase> rv) {
	try{
		rv.add(new HospitalStaff(data));
	}catch(Exception e)
	{
		
	}
	}

	private void addHospitalEquipment(Collection<UseCase> rv) {
	try {
		rv.add(new HospitalEquipmentUseCase(data));
	} catch (Exception e) {
		;
	}
		
	}

	private void addLogin(Collection<UseCase> rv) {
		try {
			rv.add(new Login(data));
		} catch (Exception e) {
			;
		}
	}
	@Override
	public UseCase execute() {

		Selector<UseCase> selector = new OrderedSelector<UseCase>(getAvailable(), new Selector.Displayer<UseCase>()
		{
			@Override
			public void display(UseCase s) {
				System.out.print(s);
			}
		});

		UseCase usecase = selector.get();
		if (usecase == null) {
			printLn("No usecase was selected.");
			return new MainMenu(data);
		} else
			return usecase;
	}


}
