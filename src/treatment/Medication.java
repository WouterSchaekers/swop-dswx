package treatment;

public class Medication extends Treatment
{
	private String description = " "; 
	private boolean sensitive = false;
	private int duration = 0;
	public static final String TESTNAME = "Medication";
	public Medication(String description, boolean sensitive){
		super(TESTNAME);
		setDescription(description);
		setSensitive(sensitive);
	}
	
	public String information(){
		return toString()+ ": " + getDescription() + " " + sensitiveText() + " ";
	}
	
	public String toString(){
		return "Medication";
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isSensitive() {
		return sensitive;
	}
	
	public String sensitiveText(){
		if(isSensitive())
			return "the medication is sensitive";
		else
			return "the medication is not sensitive";
	}

	public void setSensitive(boolean sensitive) {
		this.sensitive = sensitive;
	}
	
	public int getDuration(){
		return duration;
	}
	
	public void setDuration(){
		if(isSensitive())
			duration = 20;
		else 
			duration = 10;
	}
}
