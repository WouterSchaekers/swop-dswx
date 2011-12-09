package scheduler;

import java.util.ArrayList;
import java.util.Collection;
import scheduler.task.Schedulable;
import users.Nurse;
import users.User;

public class NurseView extends ArrayList<Schedulable>
{

	public NurseView(Collection<User> allUsers) {
		super();
		for(User u:allUsers)
			if(u instanceof Nurse)
				this.add((Schedulable)u);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
