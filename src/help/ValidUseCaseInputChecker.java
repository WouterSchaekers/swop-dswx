package help;

public class ValidUseCaseInputChecker
{
	public static boolean isValidStringResponse(String in) {
		if (in.isEmpty())
			return false;

		try {
			Integer.parseInt(in);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public static boolean isNumeric(String in) {
		if (in.isEmpty())
			return false;

		try {
			Integer.parseInt(in);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public static boolean isValidBooleanResponse(String in) {
		if(in.matches("y|Y|j|J|yes|ja") || in.matches("n|N|no|nee"))
			return true;
		return false;
	}
	
	public static boolean toBool(String in) {
		if (!isValidBooleanResponse(in)) throw new IllegalArgumentException("Unable to convert to boolean!");
		if(in.matches("y|Y|j|J|yes|ja"))
				return true;
		return false;
	}
	
}
