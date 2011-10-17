package ui;
import java.util.Scanner;
import controllers.MasterController;

public class UserInterface
{
	static UCcontroller usecaseController;
	public UserInterface(MasterController m)
	{
		usecaseController = new UCcontroller(m);
	}
	public void start() {
		
		boolean running = true;
		Scanner input = new Scanner(System.in);
		while(running )
		{
			String in = input.nextLine();
			Iterable<String> output = usecaseController.handleInput(in);
			for(String s:output)
				System.out.println(s);
		}
	}
}
