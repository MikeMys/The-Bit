//Michael Mysliwiec

public class computer 
{	
	private memory mem = new memory();			//memory variable
	longword PC = new longword();				//program counter
	longword SP = new longword();				//stack pointer 
	longword currentInstruction = new longword();
	longword tempcurrentInstruction = new longword();
	longword op1 = new longword();
	longword op2 = new longword();
	bit[] temp1 = new bit[4];			//place holders
	bit[] temp2 = new bit[4];			//place holders
	bit[] operation = new bit[4];
	bit[] opholder = new bit[4];
	longword result = new longword();
	bit[] compare = new bit[2];
	bit on = new bit();				//controls the running loop in the computer
	//int first = 0;
	
	private longword[] register = new longword[16];
	
	public computer()
	{
		
		for(int x = 0; x < 16; x++)
		{
			register[x] = new longword();
		}
		
		//System.out.println("I am happening");

		
		PC.set(0);
		SP.set(1020);			//standard initization at last 32 bit in the memory
		on.set();				//setting it to OFF by default
		
	}

	public void run() //run calls the other methods
	{

		while(on.getValue() == 1)		//loop of run while it is on
		{
			fetch();
			decode();
			execute();
			store();
		}
		
	}
	public void fetch()		//empty for now
	{
		longword temp = new longword();			//just a temp for incrementing
		temp.set(4);
		
		currentInstruction.copy(mem.read(PC));				//holds the first value in PC, used for register
		tempcurrentInstruction.copy(currentInstruction);
		System.out.println("---This is Current Instruction----\n" +currentInstruction.toString());
		PC.copy(RippleAdder.add(PC,  temp));				//reads the next value in PC, again for register 
		//System.out.println("---This is PC----\n" +PC.toString());
		
	}
	public void decode()	//empty for now
	{
		int count = 1;
		
		//for operation holder
		/*This loop sets "operation", which will hold the value of the op for the ALU
		 * Finds it by checking the first place in PC, which is currentInstruction
		 */
		//System.out.println("This is currentInstruction loop "+count+": "+currentInstruction.toString());
		for(int a = 0; a < 4; a++)
		{
			opholder[a] = new bit();
			if(currentInstruction.getBit(a).getValue() == 1)
			{
				opholder[a].set();
			}
			else if(currentInstruction.getBit(a).getValue() == 0)
			{
				opholder[a].set(0);
			}
			else
			{
				System.out.println("There is a null pointer exception (1)");
			}
		//System.out.println("This is opholder "+a+": " +opholder[a]);

		}
		//rightshifting to access the next set of 4 bits
		currentInstruction.copy(currentInstruction.rightShift(4));
		//System.out.println("This is currentInstruction loop "+count+": "+currentInstruction.toString());
		//for temp1
		/*
		 * This temp1 holds the second set of 4 bits that were in PC, which will be the first register used
		 */
		for(int x = 0; x < 4; x++)
		{
			temp2[x] = new bit();
			if(currentInstruction.getBit(x).getValue() == 1)
			{
				temp2[x].set();
			}
			else if(currentInstruction.getBit(x).getValue() == 0)
			{
				temp2[x].clear();
			}
			else
			{
				System.out.println("There is a null pointer exception (2)");
			}
			//System.out.println("This is register 1 "+x+": " +temp2[x]);
		}
		//rightshifting to access the next set of 4 bits
		currentInstruction.copy(currentInstruction.rightShift(4));
		//System.out.println("This is currentInstruction loop "+count+": "+currentInstruction.toString());
		//for temp2
		/*
		 * This temp2 holds the third set of 4 bits that were in PC, which will be the second register used
		 */
		for(int y = 0; y < 4; y++)
		{
			temp1[y] = new bit();
			if(currentInstruction.getBit(y).getValue() == 1)
			{
				temp1[y].set();
			}
			else if(currentInstruction.getBit(y).getValue() == 0)
			{
				temp1[y].clear();
			}
			else
			{
				System.out.println("There is a null pointer exception (3)");
			}
		}
		
		currentInstruction.copy(currentInstruction.rightShift(4));
		//System.out.println("This is currentInstruction loop "+count+": "+currentInstruction.toString());
		//for last register
		/*
		 * This "opholder" is the register that holds the result of the operation
		 * If it was: add R1 R2 R3, then it would be R3 = R1 + R2, and this is R3 (last register)
		 */
		for(int z = 0; z < 4; z++)
		{
			operation[z] = new bit();
			if(currentInstruction.getBit(z).getValue() == 1)
			{
				operation[z].set();
			}
			else if(currentInstruction.getBit(z).getValue() == 0)
			{
				operation[z].clear();
			}
			else
			{
				System.out.println("There is a null pointer exception");
			}
			//System.out.println("This is operation "+z+": " +operation[z]);

		}

		op1 = register[getNum(temp1).getSigned()];			//the "current" PC 
		op2 = register[getNum(temp2).getSigned()];			//the "next" PC
		
		
	}
	public void execute()	
	{
		longword memholder = new longword();	//holding the 32 bit segment of memory that will be printed out repeatedly
		longword increment = new longword();	//what we are increasing the call by each iteration
		longword call = new longword();		//the place in the memory that is being called
		call.set(0);			
		increment.set(1);		
		
		//This is done to check if there is an interuption before the result is done
		if(operation[0].getValue() == 0 && operation[1].getValue() == 1 && operation[2].getValue() == 0 && operation[3].getValue() == 0)		//checking for 0000
		{
			//System.out.println("Now its coming together");
			//checks if it fits the case to print all the registers
			if(opholder[0].getValue() == 0 && opholder[1].getValue() == 0 && opholder[2].getValue() == 0 && opholder[3].getValue() == 0)		//checking for 0000
			{
				//System.out.println("I got to here");
				for(int x = 0; x < 16; x ++)
				{
					//doesn't specify so I do both
					System.out.println("Register "+x+": " +register[x].toString());				//prints whole longword of register
					System.out.println(register[x].getSigned());			//prints value of register
				}
				on.clear();
			}
			else
				if(opholder[0].getValue() == 1 && opholder[1].getValue() == 0 && opholder[2].getValue() == 0 && opholder[3].getValue() == 0)		//checking for 0001
				{
					//this is the second case where we print all 1024 bytes, print whole memory
					for(int a = 0; a < 1024; a++)
					{
						//System.out.println("Why is this happening");
						memholder.copy(mem.read(call));	//now holds value of mem from call to call+31
						//System.out.println(memholder);
						//this loops 8 bits together to form a byte
						for(int x = 7; x >= 0; x--)
						{
							System.out.print(memholder.getBit(x).getValue());
						}
						System.out.println(); 				//new line
						call = RippleAdder.add(call, increment);			//basically call += increment (adds 1 each time because in read 1 is actually 8)
					}
					on.clear();
				}
		}
		//System.out.println("This is happening inside of execute");
		System.out.println("--------calling--------");
		//move
				if(getNum(operation).getSigned() == 1)
				{			
					longword value = new longword();
					int set = 0;
					for(int x = 0; x < 4; x++)
					{
						value.setBit(set, opholder[x]);
						set++;
					}
					for(int x = 0; x < 4; x++)
					{
						value.setBit(set, temp2[x]);
						set++;	
					}
					//System.out.println(getNum(opholder).getSigned());
					//System.out.println(getNum(temp2).getSigned());
					op1.set((value).getSigned());
					op2.set(getNum(temp2).getSigned());
				}
				//compare
				if(getNum(operation).getSigned() == 4)
				{
					bit one = new bit();
					one.set();
					bit zero = new bit();
					zero.clear();
					//temp1 is first
					System.out.println("Register 1: "+getNum(temp1).getSigned());
					System.out.println("Register 2: "+getNum(temp2).getSigned());
					if(getNum(temp1).getSigned() > getNum(temp2).getSigned())
					{
						register[getNum(temp1).getSigned()].setBit(15, one);			//setting greater than mark
						register[getNum(temp2).getSigned()].setBit(15, zero);			//setting less than mark
					}
					if(getNum(temp2).getSigned() > getNum(temp1).getSigned())
					{
						register[getNum(temp2).getSigned()].setBit(15, one);			//setting greater than mark
						register[getNum(temp1).getSigned()].setBit(15, zero);			//setting less than mark
					}
					
					if(getNum(temp1).getSigned() == getNum(temp2).getSigned())
					{
						register[getNum(temp1).getSigned()].setBit(14, one);			//setting equal mark
						register[getNum(temp2).getSigned()].setBit(14, one);			//setting equal mark
					}
					if(getNum(temp1).getSigned() != getNum(temp2).getSigned())
					{
						register[getNum(temp1).getSigned()].setBit(14, zero);			//setting not equal mark
						register[getNum(temp2).getSigned()].setBit(14, zero);			//setting not equal mark
					}
				}
				//branch
				if(getNum(operation).getSigned() == 5)
				{
					longword compared = new longword();
					boolean negative = false;
					bit zero = new bit();
					bit one = new bit();
					one.set();
					zero.clear();
					//getting the number being compared
					for(int x = 15; x > 9; x--)
					{
						//setting the manditory zeros for first part of longword 000000SAAAAAAAAA
						compared.setBit(x, zero);
					}
					//setting rest of compared number
					int y = 3;
					
					if(temp1[0].getValue() == 1)
					{
						negative = true;
						compared.setBit(8, one);
					}
					
					//doing this to make future setting easier
					y = 3;
					for(int x = 7; x > 3;x--)
					{
						if(temp2[y].getValue() == 1)
						{
							compared.setBit(x, one);
						}
						else
						{
							compared.setBit(x, zero);
						}
						y--;
					}
					y = 3;
					for(int x = 3; x >= 0;x--)
					{
						//System.out.println("This is ophodler: "+opholder[y].getValue());
						if(opholder[y].getValue() == 1)
						{
							compared.setBit(x, one);
							//System.out.println("COMPARED: "+compared.toString());
						}
						else
						{
							compared.setBit(x, zero);
						}
						y--;
					}
					
					if(negative == true)
					{
						System.out.println("Negative is true");
						System.out.println("This is negative: "+compared.getSigned());
						System.out.println("This is negative: "+compared.toString());
						for(int i = 0; i < 9; i++)
						{
							if(compared.getBit(i).getValue() == 1)
							{
								compared.setBit(i, zero);
							}
							else
							{
								compared.setBit(i, one);
							}
						}
						int hold = compared.getSigned() + 1;
						compared.set(hold);
						System.out.println("This is positive: "+compared.getSigned());
					}
					
					for(int x = 1; x < 16; x++)
					{
						//if the first matches, either greater than or equals
						if(register[x].getBit(11).getValue() == 1)
						{
							//greater than
							if(register[x].getBit(10).getValue() == 0)
							{
								if(compared.getSigned() > register[x].getSigned())
								{
									{
										//System.out.println("IT WORKED");
										System.out.println("PC before: "+PC.getSigned());
										if(negative == true) 
										{
											PC.copy(RippleAdder.subtract(PC, compared));
										}
										else
										{
											PC.copy(RippleAdder.add(PC, compared));
										}
										System.out.println("PC after: "+PC.getSigned());
										break;
									}
								}
							}
							//this is equals
							else if(register[x].getBit(10).getValue() == 1)
							{
								if(compared.getSigned() == register[x].getSigned())
								{
									//System.out.println("IT WORKED");
									System.out.println("PC before: "+PC.getSigned());
									if(negative == true) 
									{
										PC.copy(RippleAdder.subtract(PC, compared));
									}
									else
									{
										PC.copy(RippleAdder.add(PC, compared));
									}
									System.out.println("PC after: "+PC.getSigned());
									break;
								}
							}
						}
						//less than and not equal
						else if(register[x].getBit(11).getValue() == 0)
						{
							//less than
							if(register[x].getBit(10).getValue() == 1)
							{
								if(register[x].getSigned() > compared.getSigned())
								{
									//System.out.println("IT WORKED");
									System.out.println("PC before: "+PC.getSigned());
									if(negative == true) 
									{
										PC.copy(RippleAdder.subtract(PC, compared));
									}
									else
									{
										PC.copy(RippleAdder.add(PC, compared));
									}
									System.out.println("PC after: "+PC.getSigned());
									break;
								}
							}
							//not equal to
							else if(register[x].getBit(10).getValue() == 0)
							{
								if(register[x].getSigned() != compared.getSigned())
								{
									//System.out.println("IT WORKED");
									System.out.println("PC before: "+PC.getSigned());
									if(negative == true) 
									{
										PC.copy(RippleAdder.subtract(PC, compared));
									}
									else
									{
										PC.copy(RippleAdder.add(PC, compared));
									}
									System.out.println("PC after: "+PC.getSigned());
									break;
								}
							}
						}
					}
				}
				
				//push, pop, call, return
				if(getNum(operation).getSigned() == 6)
				{
					//push 0110 "00
					if(temp1[3].getValue() == 0 && temp1[2].getValue() == 0)
					{
						System.out.println("This is push");

						System.out.println("This is the stack pointer: "+SP.getSigned());
						longword number = new longword();
						number = getNum(opholder);
						
						System.out.println("This is the register number: "+number.getSigned());
						System.out.println("This is what is in the register: "+register[number.getSigned()].getSigned());
						System.out.println("In the stack pointer: "+ register[number.getSigned()].toString());
												
						//writing to memory
						longword writeholder = new longword();
						//where we are writing to (mulitply by 8 because that is a byte)
						writeholder.set(SP.getSigned()*8);
						mem.write(writeholder, register[number.getSigned()]);
						//decrement SP
						SP.set(SP.getSigned()-4);
						System.out.println("This is the stack pointer: "+SP.getSigned());
					}
					//pop, 0110 "1000 0000" then register
					else if(temp1[3].getValue() == 1 && temp1[2].getValue() == 0 && temp1[1].getValue() == 0 && temp1[0].getValue() == 0 && temp2[3].getValue() == 0 && temp2[2].getValue() == 0 && temp2[1].getValue() == 0 && temp2[0].getValue() == 0)
					{
						System.out.println("This is pop");
						
						System.out.println("This is the stack pointer: "+SP.getSigned());
						longword number = getNum(opholder);
						System.out.println("This is the register number: "+number.getSigned());
						
						//check: reading from memory
						register[number.getSigned()] = mem.read(SP);
						System.out.println("This is memory at SP: "+register[number.getSigned()].toString());
						System.out.println("This is the value of memory: "+register[number.getSigned()].getSigned());
						
						//increment SP
						SP.set(SP.getSigned()+4);
						System.out.println("This is the stack pointer: "+SP.getSigned());
					}
					//call, 0110 "10" with address after it
					else if(temp1[3].getValue() == 1 && temp1[2].getValue() == 0)
					{
						System.out.println("This is call");
						longword caller = new longword();
						bit zero = new bit();
						bit one = new bit();
						one.set();
						zero.clear();
						//000000AAAAAAAAAA
						for(int x = 15; x < 9; x--)
						{
							caller.setBit(x, zero);
						}
						//@9
						if(temp1[1].getValue() == 1)
						{
							caller.setBit(9, one);
						}
						else
						{
							caller.setBit(9, zero);
						}
						//@8
						if(temp1[0].getValue() == 1)
						{
							caller.setBit(8, one);
						}
						else
						{
							caller.setBit(8, zero);
						}
						//@7-4
						int b = 7;
						for(int a = 3; a >= 0; a--)
						{
							if(temp2[a].getValue() == 1)
							{
								caller.setBit(b, one);
							}
							else
							{
								caller.setBit(b, zero);
							}
							b--;
						}
						for(int c = 3; c >= 0; c--)
						{
							if(opholder[c].getValue() == 1)
							{
								caller.setBit(c, one);
							}
							else
							{
								caller.setBit(c, zero);
							}
						}
						
						System.out.println("This is location of SP: "+SP.getSigned());
						//holds value for mem.write location
						longword writeholder = new longword();
						//holds value for mem.write value
						longword SPholder = new longword();
						writeholder.set(caller.getSigned()*8);

						SPholder.copy(mem.read(SP));
						//writing to memory 
						mem.write(writeholder, SPholder);
						longword readholder = new longword();
						
						readholder.set(caller.getSigned());
						//reading for a check to see that it worked
						System.out.println("This is read at "+writeholder.getSigned()+": "+mem.read(readholder));
						
						//change SP to the current address set
						System.out.println("This is SP before: "+SP.getSigned());
						SP.set(caller.getSigned());
						System.out.println("This is SP after: "+SP.getSigned());
						
					}
					//return, 0110 "11"
					else if(temp1[3].getValue() == 1 && temp1[2].getValue() == 1)
					{	
						System.out.println("This is return");
						//shows what is at the location of the current SP
						System.out.println("This is the longword at the location of SP ("+SP.getSigned()+"): "+mem.read(SP));
					}
				}
				
			result.copy(ALU.doOp(operation, op1, op2));	//simply runs the method in the ALU an returns in result, this is the operation part
	}
	
	//In store we set the register to whatever was in the opholder and make that equal to the result found in execute (stores the value)
	public void store()		
	{
		int num = 0;
		int hold = 1;
		for(int x = 0; x < 4; x++)
		{
			if(operation[x].getValue() == 1)
			{
				num = num + hold;
			}
			hold = hold * 2;
		}
		if(num == 3)
		{
			//System.out.println("we are jumping");
			longword jump = new longword();
			longword four = new longword();
			longword thirtytwo = new longword();
			thirtytwo.set(8);
			four.set(4);
			bit zero = new bit();
			zero.clear();
			bit one = new bit();
			one.set();
			jump.setBit(15, zero);
			jump.setBit(14, zero);
			jump.setBit(13, zero);
			jump.setBit(12, zero);
			System.out.println("---This is Current Instruction----\n" +tempcurrentInstruction.toString());
			for(int i = 0; i < 12; i++)
			{
				if(tempcurrentInstruction.getBit(i).getValue() == 1)
				{
					jump.setBit(i, one);
				}
				else
				{
					jump.setBit(i, zero);
				}
			}
			//jump now holds the address to jump to
			System.out.println("This is jump: "+jump.getSigned());
			System.out.println("This is jump: "+jump.toString());
			jump.copy(multiplier.multiply(jump, four));
			jump.copy(RippleAdder.subtract(jump, thirtytwo));
			longword temp = new longword();			//just a temp for incrementing
			temp.set(4);
			currentInstruction.copy(mem.read(jump));				//holds the first value in PC, used for register
			//System.out.println("---This is Current Instruction----\n" +currentInstruction.toString());
			PC.copy(RippleAdder.add(PC,  jump));
			
			computer CPU = new computer();
			CPU.decode();
			CPU.execute();
			
		}
		
			//register[getNum(opholder).getSigned()].copy(result);
		
			if(getNum(operation).getSigned() == 0)			//checking is the operation is 0000 because this would indicate a halt 
			{
				on.clear();				//changes the running loop to 0, halting the while loop
				//System.out.println("The loop should stop");
			}
	}
	
	//places value of "value: into the register of regist
	public void move(bit[] operation, bit[] regist, longword value)
	{
		//System.out.println("I am moving");
		//System.out.println("This is the register: "+getNum(regist).getSigned());
		//System.out.println("This goes in the register: "+value.getSigned());
		longword hold = new longword();		//temp longword
		hold.copy(getNum(regist));				//now holds the value of register number in a longword
		System.out.println("This is the value of the register: "+hold.getSigned());
		register[hold.getSigned()].copy(value);	//gets value of longword and sets that to register before setting that register equal to the value sent
	}
	
	/*
	 * Well this is a complete extra method I made but it can call halt and interrupts midwat so I thought it could have use
	 */
	//this was not assigned, please don't take points off
	public void halt(String[] load)			
	{
		//just use store()
		//System.out.println("This is happening inside of preload");
		longword preload = new longword();
		bit setter = new bit();
		int length = load.length;
		int placement = 0;
		
		//System.out.println("I am inside preload");
		
			//opCode instructions
			for(int x = 15; x > 16-length; x--)
			{
				
				if(load[x] == "1")
				{
					setter.set();
					preload.setBit(placement, setter);
				}
				else //if(load[x] == "0")
				{
					setter.clear();
					preload.setBit(placement, setter);
				}
				//will be setting anything not a 0 or a 1 to 0 by default
				placement++;
			}
			//in case the length wasn't 16, this fills it in so it is a complete longword (just 0's to fill)
			if(length != 16)
			{
				setter.clear();
				for(int hold = 16 - length; hold > 0; hold--)
				{
					preload.setBit(hold, setter);
				}
			}
			
			//System.out.println("This is all of preload: " +preload.toString());
			
			/*
			 * Everything is fine up to this point
			 */
			
			int operationhold = 15;
			for(int a = 0; a < 4; a++)			//correctly holds the operation
			{
				operation[a] = new bit();
				operation[a].set(preload.getBit(operationhold).getValue());
				//System.out.println("This is of operation at " +a+ ": " +operation[a]);
				operationhold--;
			}
			int op1hold =11;
			for(int b = 0; b < 4; b++)
			{
				op1.setBit(b, preload.getBit(op1hold));
				op1hold--;
			}
			//System.out.println("This is of op1 at: " +op1.toString());
			
			int op2hold = 7;
			for(int c = 0; c < 4; c++)
			{
				op2.setBit(c, preload.getBit(op2hold));
				op2hold--;
			}
			//System.out.println("This is of op2 at: " +op2.toString());

			int e = 3;
			for(int d = 0; d < 4; d++)
			{
				opholder[d] = new bit();
				opholder[d].set(preload.getBit(e).getValue());
				//System.out.println("This is opholder at"+d+": " +opholder[d]);
				e--;
			}
			execute();
			//result.copy(ALU.doOp(operation, op1, op2));
			//register[getNum(opholder).getSigned()].copy(result);
			//System.out.println("Register number is: " +getNum(opholder).getSigned());
			//System.out.println("The result is: " +result.getSigned());
			//System.out.println("Was halted");
	}
	
	public void preload(String[] preloaded)
	{
		longword preload = new longword();
		longword address = new longword();
		longword thirtytwo = new longword();
		longword read = new longword();
		longword test = new longword();
		address.set(0);		//setting the address to write to to be zero because it has to start there then increment for preload
		thirtytwo.set(32);		//this is what we will be incrementing address by continuously
		bit set = new bit();
		int loadValue = 0;
		String load;
		String subLoad;
		String one = "1";
		int run = 0;
		int length;

		while(run < preloaded.length)
		{
			System.out.println("String "+(run+1)+": " +preloaded[loadValue]);
			load = preloaded[loadValue];
			length = load.length();
			//System.out.println("Length: "+length);
			int num = 0;
			int setter = 15;
			if(length > 16)
			{
				//System.out.println("length too long");
				length = 16;
			}
			for(int x = 16; x > 16-length; x--)
			{
				//System.out.println("num: " +num);
				//System.out.println("main loop is happening");
				if(num+1 > 16)
				{
					//System.out.println("Happened once");
					num--;
				}
				subLoad = load.substring(num, num+1);
				//System.out.println("This is subLoad: " +subLoad);
				if(subLoad.equalsIgnoreCase(one) == true)
				{
					set.set();
					preload.setBit(setter, set);
				}
				else
				{
					//System.out.println("Alsways happening");
					set.clear();
					preload.setBit(setter, set);
				}
				setter--;
				num++;
			}
			if(length != 16)
			{
				set.clear();
				for(int y = 16-length; y > 0; y--)
				{
					preload.setBit(setter, set);
					num++;
					setter--;
				}
			}
			//at this point we should have our preloaded string set into our longword preload
			System.out.println("Preloaded: " +preload.toString());
			
			//this would be a halt
			if(preload.getSigned() == 0)
			{
				break;
			}
			else
			{
				mem.write(address, preload);
				test.set(address.getSigned()/8);
				read.copy(mem.read(test));
				System.out.println("This is read: " +read.toString());
				//need to increment address by 4 so it goes to a new 32 bit section
				address = RippleAdder.add(address, thirtytwo);		//adding 4 to address
				loadValue++;
				run++;
			}
		}
	}
	
	
	
	//This is simply a helper function I made for decode because I did want to put a loop in each of the multiple sections
	//That I used in order to set the register (I can change it if helpers aren't allowed, was just convenient)
	public longword getNum(bit[] operation)
	{
		longword num = new longword();
		bit bt = new bit();

		for(int x = 0; x < 4; x++)
		{

				if(operation[x].getValue() == 1)
				{
					bt.set();
					num.setBit(x, bt);
				}
				else
				{
					bt.clear();
					num.setBit(x, bt);
				}
			
		}
		//System.out.println("This is num: "+num.toString());
		return num;
	}
	
}
