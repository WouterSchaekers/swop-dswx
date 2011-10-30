package ui;

import java.util.Scanner;


/**
 * This class handles the usecases and manages in- and output.
 */
public class UCHandler
{
	usecase currentUseCase;
	DataBlob data;
	public UCHandler(DataBlob data){
		this.data = data;
		currentUseCase = new SelectUsecase(data);
		usecase.setScanner(new Scanner(System.in));
	}
	public void start(){
		while(true)
		{
			currentUseCase=currentUseCase.Execute();
			if(currentUseCase instanceof exitSystem)
				return;
		}
			
	}

}
