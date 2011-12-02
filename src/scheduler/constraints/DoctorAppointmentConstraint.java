package scheduler.constraints;

import java.util.Date;
import scheduler.Appointment;

public class DoctorAppointmentConstraint implements TimeConstraint
{

	@Override
	public boolean isMet(Date time) {
		
		
		return false;
	}

	@Override
	public boolean applicableOn(Object object) {
		return object instanceof Appointment;
	}

}
