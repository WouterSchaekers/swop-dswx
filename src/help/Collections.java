package help;

import java.util.ArrayList;
import java.util.Collection;
public class Collections
{
	 public static <T>Collection<T> filter(Collection<T> tofilter,Filter f)
	    {
	        ArrayList<T> a = new ArrayList<T>();
	        for(T t: tofilter)
	            if(f.allows(t))
	                a.add(t);
	        
	        return a;
	    }
}
