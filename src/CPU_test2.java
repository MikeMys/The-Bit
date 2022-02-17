
public class CPU_test2 
{
	public void runTests()
	{
		computer CPU_test = new computer();
		
		String[] call = new String[] {"move r4 15", "move r5 12", "move r7 47", "push r4", "push r5" , "push r7", "pop r6", "pop r7", "return", "call 1012", "return", "interrupt"};
		String[] assembleCode = Assembler.assemble(call);
		
		for(int i = 0; i < call.length; i++)
		{
			System.out.println("This is assemble code at line " +(i+1)+": "+assembleCode[i]);
		}
		
		CPU_test.preload(assembleCode);
		CPU_test.run();
	}

}
