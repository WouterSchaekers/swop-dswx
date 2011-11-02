package task.requirement;

import java.util.Collection;
import task.resource.Resource;

public class AspecificResrouce extends Requirement
{
	private Resource specificResrouce;
	
	
	@Override
	public boolean isMetBy(Collection<Resource> availableNow) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeUsedResoursesFrom(Collection<Resource> availableNow,
			Collection<Resource> scheduledElements) {
		// TODO Auto-generated method stub
		
	}

}
