package ui;

import java.util.Scanner;
import exceptions.InvalidHospitalDateArgument;

/**
 * This class truly starts the user interface and initializes the first item do
 * display, this is the Select usecase class which will be a menu option. After
 * each execution (Execute() call) a new menu will be returned. The menu's and
 * classes can be Visualized as a finite state machine
 * http://en.wikipedia.org/wiki/Finite_state_machine, and the transistions (
 * currentusecase= currentusecase.Execute() ) is kinda like the command pattern
 * as described at http://en.wikipedia.org/wiki/Command_pattern, every state
 * requests the information he needs and returns a new state that either gets
 * more information or processes the given information.
 * 
 * Every usecase is represented by us as a chain of usecase commands, EX: the
 * loginchain is started by selecting login in select usecase it will then go to
 * the isallowedtologin command wich returns select usecase if you are not(there
 * already is a login controller in the userinterface or someone else is already
 * logged in. If you are allowed to login you will return a new
 * CreatLoginController. This object creates a logincontroller and returns a new
 * displayallnames object. This object displays all names when executed and
 * returns a login object that queries the user for his name. etc...
 * 
 * */
public class UCHandler
{
	Usecase currentUIView;
	UserinterfaceData data;

	/**
	 * Initializes the current command to SelectUseCase
	 * 
	 * @param data
	 */
	public UCHandler(UserinterfaceData data) {
		this.data = data;
		currentUIView = new SelectUsecase(data);
		Usecase.setScanner(new Scanner(System.in));
	}
	
	/**
	 * Here each command is called and executed, this returns a new command that
	 * then will be executed, until the exitSystem command is returned.
	 * @throws InvalidHospitalDateArgument 
	 * 
	 */
	public void start() {
		while (true) {
			if ((currentUIView = currentUIView.Execute()) instanceof ExitSystem)
				return;
		}

	}

}
