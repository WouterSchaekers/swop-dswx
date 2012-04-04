package ui;

public class UIRunner
{
	

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
