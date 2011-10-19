package system;
import java.util.Scanner;

public class Sandbox
{
	
	public static void main(String[] args) {
		
		String in = "";
		Scanner scanner = new Scanner(System.in);
		
		while(!in.equalsIgnoreCase("exit") || !in.equalsIgnoreCase("quit")){
		
		System.out.println("Hi, What would you like to do? \n");
		in = scanner.nextLine();
		
		System.out.println("You wrote: " + in + "\n"  
				+ "-> if 'login, exit' --> handle input met inHandler\n else if logged in --> input is doctornaam of randomness --> catchen" +"\n\n\n\n" + "DIETER IS NIET HELEMAAL JUIST IN ZIJN HOOFD, ZOALS UIT ZIJN CODESTYLE VALT OP TE MAKEN!!'");
		}
	}

}
