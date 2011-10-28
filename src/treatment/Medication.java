package treatment;

public class Medication extends Treatment
{
	private String description = " "; 
	private boolean sensitive = false;
	public static final String TREATMENTNAME = "Medication";
	public Medication(String description, boolean sensitive){
		super(TREATMENTNAME);
		setDescription(description);
		setSensitive(sensitive);
		if(isSensitive())
			super.setDuration(20);
		else 
			super.setDuration(10);
	}
	
	public String information(){
		return TREATMENTNAME + ": " + getDescription() + " " + sensitiveText() + " this medication treatment takes " + super.getDuration() + " min.";
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
}
