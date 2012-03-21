package scheduler.requirements;

public interface Requirement
{
	public boolean isMetBy(Requirable requirable);
	
	public boolean isMet();
	
	public void collect();
	
	public boolean backToBack();
}