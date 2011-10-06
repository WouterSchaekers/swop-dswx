package testutil;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import util.MathUtil;

public class MathUtilTest {
	@Test
	public void testPrime() {
		assertTrue(MathUtil.isPrime(5));
        assertFalse(MathUtil.isPrime(9));
        assertFalse(MathUtil.isPrime(12));
        assertFalse(MathUtil.isPrime(2));
        assertTrue(MathUtil.isPrime(37));
        assertTrue(MathUtil.isPrime(1000003));
	}
	@Test
	public void testFac()
	{
	    
	}
}
