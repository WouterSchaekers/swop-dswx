package ui;

import help.FifoQueue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import javax.swing.Spring;
import controllers.DataPasser;
import controllers.LoginController;
import controllers.USERDTO;

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
