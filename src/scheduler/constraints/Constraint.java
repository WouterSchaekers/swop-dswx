package scheduler.constraints;

public interface Constraint
{
	public boolean isMet();
	public boolean applicableOn(Object t);
}
