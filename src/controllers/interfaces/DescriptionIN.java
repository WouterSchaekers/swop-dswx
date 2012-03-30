package controllers.interfaces;

import result.Result;

public interface DescriptionIN
{
	public boolean needsResult();

	public void setResult(String result);

	public Result getResult();
	
	public PatientFileIN getPatientFile();
}
