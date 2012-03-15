package scheduler;

import scheduler2.HospitalDate;

public class DummyDate
{
	HospitalDate dummyDate;

	public HospitalDate getHospitalDate() {
		return this.dummyDate;
	}

	public DummyDate(HospitalDate toDummy) {
		this(toDummy.getHour(), toDummy.getMinute(), toDummy.getSecond());
	}

	public DummyDate(int hour, int minute, int second) {
		dummyDate = new HospitalDate(0, 0, 0, hour, minute, second);
	}

	public boolean before(DummyDate dummyDate) {
		return dummyDate.before(dummyDate.getHospitalDate());
	}

	public boolean before(HospitalDate hospitalDate) {
		return dummyDate
				.before((new DummyDate(hospitalDate)).getHospitalDate());
	}

	public HospitalDate combineWithHospitalDate(HospitalDate hospitalDate) {
		return new HospitalDate(hospitalDate.getYear(),
				hospitalDate.getMonth(), hospitalDate.getDay(),
				this.dummyDate.getHour(), this.dummyDate.getMinute(),
				this.dummyDate.getSecond());
	}
}