package scheduler;

public class Main
{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LittleClass bla = new LittleClass();
		increase(bla);
		System.out.println(bla.getBla());
	}
	
	public static void increase(LittleClass bla){
		bla.increase();
		if(bla.getBla() < 10){
			increase(bla);
		}
		System.out.println(bla.getBla());
	}
	

}
