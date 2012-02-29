package ui.reviewpatient;

import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;

public class ReviewMore extends Usecase
{

	public ReviewMore(UserinterfaceData data) {
		super(data);
	}

	@Override
	public Usecase Execute() {
		System.out.print("Would you like to review more of this patient? y/n");
		String in = input.nextLine();
		if (in.equalsIgnoreCase("y"))
			return new ReviewPatient(data);
		if (in.equalsIgnoreCase("n"))
			return new SelectUsecase(data);
		System.out.println("unknown input entered");
		return this;
	}

}
