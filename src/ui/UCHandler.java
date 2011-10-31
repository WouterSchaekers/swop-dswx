package ui;

import java.util.Scanner;


/**
 * This class handles the use cases and manages in- and output.
 */
public class UCHandler
{
	usecase currentUseCase;
	UserinterfaceData data;
	public UCHandler(UserinterfaceData data){
		this.data = data;
		currentUseCase = new SelectUsecase(data);
		usecase.setScanner(new Scanner(System.in));
	}
	
	/**
	 * The system will start up and start the first chain. 
	 * 
	 * When the use case exit system is chosen the system will shut down. 
	 */
	
	public void start(){
		while(true)
		{
			currentUseCase=currentUseCase.Execute();
			if(currentUseCase instanceof exitSystem)
				return;
		}
			
	}

}
