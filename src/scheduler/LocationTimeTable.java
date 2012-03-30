package scheduler;

public class LocationTimeTable
{
	private HospitalDate startWorkingHours_;
	private HospitalDate stopWorkingHours_;

	public LocationTimeTable(HospitalDate startWorkingHours, HospitalDate stopWorkingHours) {
		this.startWorkingHours_ = createDummyDate(startWorkingHours);
		this.stopWorkingHours_ = createDummyDate(stopWorkingHours);
	}

	private HospitalDate createDummyDate(HospitalDate hospitalDate) {
		return new HospitalDate(1, 1, 1, hospitalDate.getHour(), hospitalDate.getMinute(), hospitalDate.getSecond());
	}
}