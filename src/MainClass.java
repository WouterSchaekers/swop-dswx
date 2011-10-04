public class MainClass
{
	public static void main(String[] args) {
		Dieter dieter = new Dieter();
		System.out.println("Q: Is dieter awesome?");
		System.out.println("A: " + (dieter.isAwesome() && true));
	}
}
