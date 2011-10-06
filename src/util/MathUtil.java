package util;

public class MathUtil {
	public static long fac(int i)
	{
		if(i==0)
			return 1;
		return i*fac(i-1);
	}
	public static boolean isPrime(long i)
	{
		if(i%2==0)
			return false;
		for(int j=3;j<=Math.sqrt(i);j+=2)
		{
		    if(i==j)
		        return true;
			if(i%j==0)
				return false;
		}
		return true;
	}
}
