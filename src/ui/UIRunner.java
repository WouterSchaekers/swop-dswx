package ui;

public class UIRunner
{
	public interface UseCase
	{

		public UseCase execute();

	}

	private UseCase start;

	public UIRunner(UseCase usecase) {
		this.start = usecase;
	}

	public void run() {
		UseCase current = start;
		while (current != null)
			current = current.execute();
	}
}
