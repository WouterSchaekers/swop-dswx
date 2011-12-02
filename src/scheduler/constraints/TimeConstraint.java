package scheduler.constraints;

import java.util.Date;

public interface TimeConstraint
{
	public boolean isMet(Date time);
	public boolean applicableOn(Object object);
}
