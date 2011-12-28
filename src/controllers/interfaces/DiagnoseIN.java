package controllers.interfaces;

import java.util.Collection;

/**
 * @see
 * @author Dieter
 *
 */
public interface DiagnoseIN
{

	boolean isApproved();

	Object needsSecOpFrom();

	public abstract Collection<TreatmentIN> getTreatments();

	public abstract DoctorIN getAttending();

	public abstract String getDiagnosis();

}
