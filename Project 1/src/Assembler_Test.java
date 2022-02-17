
public class Assembler_Test 
{
	public void runTests()
	{		//set for 4 bit 4 bit 8 bit scenarios
		String[] testString = new String[] {"and pizza -122", "multiply r13 23", "add R25 55", "nitroglyceren R2 166", "lEfTsHiFt r0 66"};
		
		String[] total = Assembler.assemble(testString);
		
		int length = total.length;
		for(int x = 0; x < length; x++)
		{
			if(x%3 == 0)
				System.out.println();
			System.out.println("This is total at postion "+x+": "+total[x]);
		}
	}
}
