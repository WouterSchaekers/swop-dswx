package scheduler.tasks;

import scheduler.HospitalDate;

public class Main
{
	public static void main(String[] fucks) {
		HospitalDate date = new HospitalDate(2012, 5, 2, 1, 0, 0);
		isFullHour(date);
	}

	private static boolean isFullHour(HospitalDate hospitalDate) {
		System.out.println(hospitalDate.getTotalMillis() % HospitalDate.ONE_HOUR);
		return false;
	}
}
