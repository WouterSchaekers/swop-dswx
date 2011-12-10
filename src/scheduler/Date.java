package scheduler;

@SuppressWarnings("deprecation")
public class Date extends java.util.Date
{

	public Date(long millis) {
		super(millis);
	}
	
	@Override
	public String toString() {
		return super.toLocaleString();
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
