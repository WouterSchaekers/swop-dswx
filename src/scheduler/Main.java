package scheduler;

public class Main
{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for(int i = 0; i < 2666; i++){
			System.out.print("NOM");
		}
	}
	
	public static void increase(LittleClass bla){
		bla.increase();
		if(bla.getBla() < 10){
			increase(bla);
		}
		System.out.println(bla.getBla());
	}
	

}
