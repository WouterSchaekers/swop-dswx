package scheduler;

import java.util.GregorianCalendar;

/**
 * We'll use this class as a date-class as Java's Date classes and alternatives
 * are outdated or complex or would make our code illegible at some points.
 */
@scheduler.SchedulerAPI
public class HospitalDate
{

	/**
	 * The year of the starttime of the system, given in the assignment.
	 */
	@scheduler.SchedulerAPI
	public final static int START_YEAR = 2011;

	/**
	 * The month of the starttime of the system, given in the assignment.
	 */
	@scheduler.SchedulerAPI
	public final static int START_MONTH = 11;

	/**
	 * The day of the month of the starttime of the system, given in the
	 * assignment.
	 */
	@scheduler.SchedulerAPI
	public final static int START_DAY = 8;

	/**
	 * The hour of the starttime of the system, given in the assignment.
	 */
	@scheduler.SchedulerAPI
	public final static int START_HOUR = 8;

	/**
	 * The amount of minutes of the starttime of the system, given in the
	 * assignment.
	 */
	@scheduler.SchedulerAPI
	public final static int START_MINUTE = 0;

	/**
	 * The amount of seconds of the starttime of the system, given in the
	 * assignment.
	 */
	@scheduler.SchedulerAPI
	public final static int START_SECOND = 0;

	/**
	 * This object will keep all the above fields stored in a HospitalDate.
	 */
	@scheduler.SchedulerAPI
	public final static HospitalDate START_OF_TIME = new HospitalDate();

	/**
	 * This object will keep the absolute end of time.
	 */
	@scheduler.SchedulerAPI
	public static final HospitalDate END_OF_TIME = new HospitalDate(Long.MAX_VALUE
			- HospitalDate.START_OF_TIME.getTotalMillis());

	/**
	 * One second in millis.
	 */
	@scheduler.SchedulerAPI
	public final static long ONE_SECOND = 1000;

	/**
	 * One minute in millis.
	 */
	@scheduler.SchedulerAPI
	public final static long ONE_MINUTE = ONE_SECOND * 60;

	/**
	 * One hour in millis.
	 */
	public final static long ONE_HOUR = ONE_MINUTE * 60;

	/**
	 * One day in millis.
	 */
	@scheduler.SchedulerAPI
	public final static long ONE_DAY = ONE_HOUR * 24;

	/**
	 * One month in millis.
	 */
	@scheduler.SchedulerAPI
	public final static long ONE_MONTH = ONE_DAY * 30;
	/**
	 * One year in millis.
	 */
	@scheduler.SchedulerAPI
	public static final long ONE_YEAR = ONE_DAY * 365;

	private GregorianCalendar gregorianCalendar;

	/**
	 * Default constructor. It will initialize the HospitalDate with the
	 * absolute beginning of time.
	 */
	@scheduler.SchedulerAPI
	public HospitalDate() {
		this.gregorianCalendar = new GregorianCalendar(START_YEAR, START_MONTH, START_DAY, START_HOUR, START_MINUTE,
				START_SECOND);
	}

	/**
	 * Creates a new hospitaldate that has the same date as the given
	 * hospitaldate.
	 * 
	 * @param hospitalDate
	 *            The hospitaldate that will be copied.
	 */
	@scheduler.SchedulerAPI
	public HospitalDate(HospitalDate hospitalDate) {
		this(hospitalDate.getTimeSinceStart());
	}

	/**
	 * Creates a new hostpitaldate, with the given new time.
	 * 
	 * @param milliseconds
	 *            The amount of milliseconds since the start of time.
	 */
	@scheduler.SchedulerAPI
	public HospitalDate(long milliseconds) {
		if (milliseconds < 0)
			throw new IllegalArgumentException("The provided date is before the start of time.");
		gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTimeInMillis(HospitalDate.START_OF_TIME.getTotalMillis() + milliseconds);
	}

	/**
	 * Creates a new hospitaldate, with the given date objects.
	 * 
	 * @param year
	 *            The year.
	 * @param month
	 *            The month of the year.
	 * @param day
	 *            The day of the month.
	 * @param hour
	 *            The hour of the day.
	 * @param minute
	 *            The minute of the hour.
	 * @param second
	 *            The second of the minute.
	 */
	@scheduler.SchedulerAPI
	public HospitalDate(int year, int month, int day, int hour, int minute, int second) {
		gregorianCalendar = new GregorianCalendar(year, month, day, hour, minute, second);
	}

	/**
	 * @return The year.
	 */
	@scheduler.SchedulerAPI
	public int getYear() {
		return gregorianCalendar.get(GregorianCalendar.YEAR);
	}

	/**
	 * @return The month of the year.
	 */
	@scheduler.SchedulerAPI
	public int getMonth() {
		return gregorianCalendar.get(GregorianCalendar.MONTH);
	}

	/**
	 * @return The day of the month.
	 */
	@scheduler.SchedulerAPI
	public int getDay() {
		return gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH);
	}

	/**
	 * @return The hour of the day.
	 */
	@scheduler.SchedulerAPI
	public int getHour() {
		return gregorianCalendar.get(GregorianCalendar.HOUR_OF_DAY);
	}

	/**
	 * Returns the time between this hospitalDate and the given one.
	 * 
	 * @return The time between this hospitalDate and the given one. This time
	 *         will always be positive.
	 */
	@scheduler.SchedulerAPI
	public long getTimeBetween(HospitalDate d) {
		if (this.before(d))
			return d.getTotalMillis() - this.getTotalMillis();
		return this.getTotalMillis() - d.getTotalMillis();
	}

	/**
	 * @return The minute of the hour.
	 */
	@scheduler.SchedulerAPI
	public int getMinute() {
		return gregorianCalendar.get(GregorianCalendar.MINUTE);
	}

	/**
	 * @return The second of the minute.
	 */
	@scheduler.SchedulerAPI
	public int getSecond() {
		return gregorianCalendar.get(GregorianCalendar.SECOND);
	}

	/**
	 * @return The amounf of UTC milliseconds from the epoch.
	 */
	@scheduler.SchedulerAPI
	public long getTimeSinceStart() {
		return gregorianCalendar.getTimeInMillis() - HospitalDate.START_OF_TIME.getTotalMillis();
	}

	/**
	 * @depricated Instead use getTimeSinceStart().
	 */
	@Deprecated
	public long getTotalMillis() {
		return gregorianCalendar.getTimeInMillis();
	}

	/**
	 * @param hospitalDate
	 *            The other HospitalDate.
	 * @return True if this HospitalDate occurs before the other HospitalDate.
	 */
	@scheduler.SchedulerAPI
	public boolean before(HospitalDate hospitalDate) {
		return this.getTotalMillis() < hospitalDate.getTotalMillis();
	}

	/**
	 * @return True if this date is after the given hospital date.
	 */
	@scheduler.SchedulerAPI
	public boolean after(HospitalDate hospitalDate) {
		return hospitalDate.before(this) && !this.equals(hospitalDate);
	}

	/**
	 * Returns the maximum of the two given HospitalDates.
	 * 
	 * @param hospitalDateOne
	 *            The first HospitalDate.
	 * @param hospitalDateTwo
	 *            The second HospitalDate.
	 * @return The maximum of hospitalDateOne and hospitalDateTwo.
	 */
	@scheduler.SchedulerAPI
	public static HospitalDate getMaximum(HospitalDate hospitalDateOne, HospitalDate hospitalDateTwo) {
		if (hospitalDateOne.before(hospitalDateTwo))
			return hospitalDateTwo;
		else
			return hospitalDateOne;
	}

	/**
	 * @return The current date as a string.
	 */
	@Override
	@scheduler.SchedulerAPI
	public String toString() {
		String hour = (("" + this.getHour()).length() == 1) ? "0" + this.getHour() : "" + this.getHour();
		String minute = (("" + this.getMinute()).length() == 1) ? "0" + this.getMinute() : "" + this.getMinute();
		String sec = (("" + this.getSecond()).length() == 1) ? "0" + this.getSecond() : "" + this.getSecond();
		return this.getYear() + "-" + this.getMonth() + "-" + this.getDay() + " " + hour + ":" + minute + ":" + sec;
	}

	/**
	 * Clones this HospitalDate.
	 */
	@Override
	@scheduler.SchedulerAPI
	public HospitalDate clone() {
		return new HospitalDate(this);
	}

	/**
	 * Checks whether a given Object equals this HospitalDate.
	 */
	@Override
	@scheduler.SchedulerAPI
	public boolean equals(Object o) {
		if (o instanceof HospitalDate)
			return this.gregorianCalendar.equals(((HospitalDate) o).gregorianCalendar);
		return false;
	}
}