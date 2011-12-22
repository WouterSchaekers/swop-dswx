package help;

public interface Filter
{
	public <T> boolean allows(T arg);
}
