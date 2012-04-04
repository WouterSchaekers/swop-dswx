package system;

import org.junit.Test;

public class SystemTest
{
	@Test
	public void test()
	{
		Hospital hospital = new StandardHospitalBuilder().build();
	}
}
