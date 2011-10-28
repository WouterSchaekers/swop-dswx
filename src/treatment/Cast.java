package treatment;

public class Cast extends Treatment
{
	public static final String TREATMENTNAME = "Cast";
	private String bodyPart;
	private int length;
	
	public Cast(String bodyPart, int length){
		super(TREATMENTNAME);
		super.setDuration(120);
	}

	public String getBodyPart() {
		return bodyPart;
	}

	public void setBodyPart(String bodyPart) {
		this.bodyPart = bodyPart;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
}
