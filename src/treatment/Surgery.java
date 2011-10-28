package treatment;

public class Surgery extends Treatment
{
	public static final String TREATMENTNAME = "Surgery";
	private String description;
	
	public Surgery(String description){
		super(TREATMENTNAME);
		setDescription(description);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	
}
