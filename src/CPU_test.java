
public class CPU_test 
{
	public void runTests()
	{
		computer CPU_test1 = new computer();
		

		String[] branch = new String[] {"move r2 15", "move r3 12" ,"Compare r2 r3", "BranchIfLess 8", "BranchIfGreater -4","BranchifEqual 8", "interrupt"};
		String[] assembleCode = Assembler.assemble(branch);
		
		for(int i = 0; i < branch.length; i++)
		{
			System.out.println("This is assemble code at line " +(i+1)+": "+assembleCode[i]);
		}

		CPU_test1.preload(assembleCode);
		
		System.out.println("\n Everything before this was preloaded \n");
		/*
		value.set(1023);
		CPU_test1.move(negligent, register14, value);			//move works
		value.set(63);
		CPU_test1.move(negligent, register8, value);			//move works
		value.set(1111);
		CPU_test1.move(negligent, register3, value);			//move works
		value.set(56748);
		CPU_test1.move(negligent, register0, value);			//move works
		*/
	
		CPU_test1.run();
		/*
		System.out.println("\n---Heading to a new CPU---\n");
		
		computer CPU_test2 = new computer();
		
		String[] jump = new String[] {"jump 3", "move r4 37", "move r7 56", "interrupt"};
		String[] assembledCode = Assembler.assemble(jump);
		
		for(int i = 0; i < jump.length; i++)
		{
			System.out.println("This is assemble code at line " +(i+1)+": "+assembledCode[i]);
		}

		CPU_test2.preload(assembledCode);
		
		System.out.println("\n Everything before this was preloaded \n");
		
		CPU_test2.run();
		*/
	}
}
