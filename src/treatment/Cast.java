package treatment;


/**
 * This class represents a treatment by cast.
 */
public class Cast extends Treatment
{
	// the name of each treatment will be final and will be used later on to determine which treatment a Treatment is.
	public static final String TREATMENTNAME = "Cast";
	private String bodyPart; // the bodypart on which the cast needs to be cast onto.
	private int length; // the length of the cast.
	
	/**
	 * Default constructor.
	 * @param bodyPart
	 * The bodypart on which the cast needs to be cast onto.
	 * @param length
	 * The lengths of the cast.
	 */
	public Cast(String bodyPart, int length){
		super(TREATMENTNAME);
	}

	/**
	 * @return The bodypart on which this cast is on.
	 */
	public String getBodyPart() {
		return bodyPart;
	}

	/**
	 * This method can be used to change the body part the cast needs to be on.
	 * @param bodyPart
	 * The new body part.
	 */
	public void setBodyPart(String bodyPart) {
		this.bodyPart = bodyPart;
	}

	/**
	 * @return The length of the cast.
	 */
	public int getLength() {
		return length;
	}

	/**
	 * This method allows to adjust the length of the cast
	 * @param length
	 * The new length of the cast.
	 */
	public void setLength(int length) {
		this.length = length;
	}
	
}
