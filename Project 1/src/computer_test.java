
public class computer_test 
{
	public void runTests()
	{	
		
		/*
		String[] str = new String[] {"0011000000000011", "0110010010100001", "0111001000110001", "1000011000100010", "0010000000000000", "0110000111000000"};
		String[] str1 = new String[] {"1110000100100011", "0110010010100001", "0111001000110001", "1000011000100010", "0010000000000000", "0110000111000000"};
		String[] str2 = new String[] {"1110000100100011", "0110010010100001", "0111001000110001", "1000011000100010", "0010000000000001", "0110000111000000"};
		String[] halt1 = new String[] {"0", "0", "1", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
		String[] halt2 = new String[] {"0", "0", "1", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "1"};
		
		bit on = new bit();
		bit off = new bit();
		on.set();
		off.clear();
		longword value = new longword();
		bit[] negligent = new bit[] {off, off, off, on};
		bit[] register0 = new bit[] {off, off, off, off};	// = 0000
		bit[] register2 = new bit[] {off, on, off, off};	// = 0010
		bit[] register8 = new bit[] {off, off, off, on};	// = 1000
		bit[] register14 = new bit[] {off, on, on, on};		// = 1110
		
		//CPU_test1.preload(str);
		CPU_test1.preload(str);				//preload works
		System.out.println("\n Everything before this was preloaded \n");
		/*
		value.set(1023);
		CPU_test1.move(negligent, register14, value);			//move works
		value.set(63);
		CPU_test1.move(negligent, register8, value);			//move works
		value.set(1111);
		CPU_test1.move(negligent, register2, value);			//move works
		value.set(56748);
		CPU_test1.move(negligent, register0, value);			//move works
		*/
		//CPU_test1.run();					//run runs in a loop, halt stops it when it is sent
		
		//System.out.println("\n---Heading to a new CPU---\n");
		//CPU_test1.halt(halt1);
		/*
		computer CPU_test2 = new computer();
		CPU_test2.preload(str);				//preload works
		bit[] register7 = new bit[] {on, on, on, off};
		value.set(6666);
		CPU_test2.move(negligent, register7, value);
		CPU_test2.run();					//run runs in a loop, halt stops it when it is sent
		
		System.out.println("\n---Heading to a new CPU---\n");
		
		computer CPU_test3 = new computer();
		CPU_test3.preload(str2);				//preload works
		CPU_test3.run();					//run runs in a loop, halt stops it when it is sent
		*/
		
	}
}
