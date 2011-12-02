package scheduler.constraints;

import java.util.Date;
import users.Nurse;

public final class NurseWorkingHour implements TimeConstraint
{
	private static int start=8;
	private static int untill=17;
	
	@Override
	public boolean isMet(Date time) {
		int Th=time.getHours();
		if(Th<untill&&Th>start)
			return true;
		if(Th==start)
		{
			if(time.getMinutes()>0||time.getSeconds()>0)
				return true;
		}
		return false;
	}

	@Override
	public boolean applicableOn(Object t) {
	return t instanceof Nurse;
	}

}
