package result;

import be.kuleuven.cs.som.annotate.Basic;

/**
 * This class represents the result of a medical testo r a treatment.
 */
public class Result
{
	private String details = "";

	@Basic
	public String getDetails() {
		return details;
	}

	@Basic
	public void setDetails(String details) {
		this.details = details;
	}
	
	
}
