package system;

import scheduler.HospitalDate;

public class TimeLord
{
	private HospitalDate systemtyme;

	public HospitalDate getSystemtyme() {
		return systemtyme;
	}

	public void setSystemtyme(HospitalDate systemtyme) {
		this.systemtyme = systemtyme;
	}
	public TimeLord(HospitalDate date)
	{
		setSystemtyme(date);
	}
	public TimeLord()
	{
		this(new HospitalDate());
	}
}
