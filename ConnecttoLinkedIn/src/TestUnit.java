import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestUnit 
{
	static TestLink testInstance = null;
	
	@BeforeClass
	public static void setUp() {
		testInstance = new TestLink();
	}
	
	@Test
	public void testMyname()
	{	
		System.out.println("Test my name");
		assertEquals("Mandar", testInstance.getName());
	}
	
	@Test
	public void testConnectionCount()
	{
		System.out.println("Test my con count");
		assertEquals(49, testInstance.getConnectionCount());
	}
	
}
