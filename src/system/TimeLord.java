package system;

import scheduler.HospitalDate;

public class TimeLord
{
	private HospitalDate systemTime;

	public HospitalDate getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(HospitalDate systemtyme) {
		this.systemTime = systemtyme;
	}
	public TimeLord(HospitalDate date)
	{
		setSystemTime(date);
	}
	public TimeLord()
	{
		this(new HospitalDate());
	}
}
