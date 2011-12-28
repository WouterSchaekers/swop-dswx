package controllers.interfaces;

import java.util.Collection;

public interface PatientFileIN
{

	String getName();

	boolean isDischarged();

	public Collection<DiagnoseIN> getAlldiagnosis();

}
