package scheduler;

import java.util.Collection;

public abstract class MedicalTestDescription extends TaskDescription
{

	@Override
	public Collection<Requirement> getAllRequirements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isReady() {
		// TODO Auto-generated method stub
		return false;
	}

}