package ui;

import java.util.Scanner;
import controllers.UCHandler;

public class UserInterface
{
	static UCHandler usecaseController;

	public UserInterface() {
		usecaseController = new UCHandler();
	}

	public void start() {

		boolean running = true;
		Scanner input = new Scanner(System.in);
		while (running) {
			String in = input.nextLine();
			Iterable<String> output = usecaseController.handleInput(in);
			for (String s : output)
				System.out.println(s);
		}
	}
}
