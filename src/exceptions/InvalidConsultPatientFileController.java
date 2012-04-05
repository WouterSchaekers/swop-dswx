package exceptions;

public class InvalidConsultPatientFileController extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidConsultPatientFileController(String string) {
		super(string);
	}
}
