import java.util.ArrayList;

public class Assembler 
{
	public static String[] assemble(String[] input)
	{
		//accepts an english string that we will convert to
		//binary and then make a test that will just check if it works
		int lineNum = 0;
		
		int length = input.length;
		System.out.println(length);
		//String[] instruction = new String[3*length];		//will hold the instructions of each line (3 for each)
		ArrayList<String> instruction = new ArrayList<String>();
		int[] instructionNum = new int[length];
		int temp;
		int begin = 0;
		//line should have 3 Strings in it, if not take first 3??
		while(lineNum < length)
		{
			int count = 1;
			String line = input[lineNum];
			boolean space = true;
			while(space == true)
			{
				
				if(line.indexOf(" ") > 0)
				{
					temp = line.indexOf(" ");
					//this puts from beginning of string to the space inside instruction
					instruction.add(line.substring(0, temp));
					//if the space (temp) is at 4, begin is now at 5
					begin = temp + 1;
					line = line.substring(begin);
					count++;
				}
				else
				{
					instructionNum[lineNum] = count;
					instruction.add(line.substring(0));
					space = false;
				}
			}
			lineNum++;
		}
		
		/*
		 * Switch Statement here that allows for the varying cases
		 */
		String[] result = new String[instruction.size()];
		for(int r = 0; r < instruction.size(); r++)
		{
			//this will be where all the cases are, lowercase or accident prevention
			//switch works
			switch(instruction.get(r).toLowerCase())
			{
			//commands
				case "and":
					//System.out.println("and");
					result[r] = "1000";
					break;
				case "or":
					//System.out.println("or");
					result[r] = "1001";
					break;
				case "xor":
					//System.out.println("xor");
					result[r] = "1010";
					break;
				case "not":
					//System.out.println("not");
					result[r] = "1011";
					break;
				case "leftshift":
					//System.out.println("leftshift");
					result[r] = "1100";
					break;
				case "rightshift":
					//System.out.println("rightshift");
					result[r] = "1101";
					break;
				case "add":
					//System.out.println("add");
					result[r] = "1110";
					break;
				case "subtract":
					//System.out.println("subtract");
					result[r] = "1111";
					break;
				case "multiply":
					//System.out.println("multiply");
					result[r] = "0111";
					break;
				case "move":
					//System.out.println("move");
					result[r] = "0001";
					break;
				case "jump":
					System.out.println("Switch worked");
					result[r] = "0011";
					break;
				case "halt":
					System.out.println("We are halting");
					result[r] = "0000000000000000";
				case "interrupt":
					System.out.println("Interrupting");
					result[r] = "0010000000000000";
					break;
				case "compare":
					System.out.println("Comparing");
					result[r] = "0100";
					break;
				case "branchifgreater":
					result[r] = "010110";
					break;
				case "branchifless":
					result[r] = "010101";
					break;
				case "branchifnotequal":
					result[r] = "010100";
					break;
				case "branchifequal":
					result[r] = "010111";
					break;
				case "push":
					result[r] = "011000000000";
					break;
				case "pop":
					result[r] = "011010000000";
					break;
				case "return":
					result[r] = "0110110000000000";
					break;
				case "call":
					result[r] = "011010";
					break;
					
				default: if(Character.isDigit(instruction.get(r).charAt(0)) == true || instruction.get(r).charAt(0) == '-')
				{
					if(result[r-1] == "0011")
					{
						System.out.println("Jumping");
						result[r] = isNumber(instruction.get(r), 1);
					}
					else if(result[r-1] == "010110" || result[r-1] == "010101" || result[r-1] == "010111" || result[r-1] == "010100")
					{
						System.out.println("Branching");
						result[r] = isNumber(instruction.get(r), 2);
					}
					else if(result[r-1] == "011010")
					{
						System.out.println("calling");
						result[r] = isNumber(instruction.get(r), 3);
					}
					else
					{
						System.out.println("This is a number");
						result[r] = isNumber(instruction.get(r), 0);
					}
				}
			}
			if(instruction.get(r).toLowerCase().charAt(0) == 'r' && Character.isDigit(instruction.get(r).charAt(1)) == true)
			{
				result[r] = isRegister(instruction.get(r));
			}
			if(result[r] == null)
			{
				result[r] = "1111";
			}
			//I dont use register 0
			
		}
		String[] total = new String[length];
		for(int i = 0; i < length; i++)
		{
			total[i] = "";
		}
		int y = 0;
		int num = 0;
		for(int x = 0; x < result.length; x++)
		{
			if(y == instructionNum[num])
			{
				y = 0;
				num++;
			}
			total[num] = total[num] + result[x];
			y++;
		}

		
		return total;
	}
	
	//used to check the number of the register and return it in binary form 13 -> 1101
	//Do this in place of 15 cases in a switch
	public static String isRegister(String str)
	{
		String result = "";
		int[] arry = new int[4];		//holds the "bits" so they can be placed into the string backwards (for correct order)
		int temp;
		int length = str.length();		
		String number = str.substring(1, length);		//if it gets to this method then it is for a register so we take the whole thing minus the initial r to get the number needed
		int num = Integer.parseInt(number);
		
		if(num > 15)
		{
			//we are only working with 16 registers: 0-15, so cant have any greater
			System.out.println("Register "+num+" is unavailable");
			return result = result + "0000";
		}
		for(int i = 0; i < 4; i++)
		{
			temp = num%2;
			//simple sets to 1 or 0 depending on the mod
			if(temp == 1)
			{
				arry[i] = 1;
			}
			else
			{
				arry[i] = 0;
			}
			num = num/2;
		}

		//inverting the array into the string
		for(int x = 3; x >= 0; x--)
		{
			result = result + arry[x];
		}
		
		return result;
	}
	
	//method used to check if the user has input a number, ex: move R1 15, would return 15 in a string
	/*
	 * I hope you dont mind me using the toBinaryString, it didnt say I couldn't but if its an issue I could honestly
	 * just go look in its code and put that here instead, I just tried to make it simpler
	 */
	public static String isNumber(String str, int jump)
	{
		//only have space for 8 bits so max is -127 -> 127
		String result = "";
		int number = Integer.parseInt(str);			//ex: 123
		//System.out.println("This is number: "+number);
		String tempResult = Integer.toBinaryString(number);			//this sets it to binary but doesnt add the extra zeroes, also is 32 bit based
		//System.out.println("This is the length of result: "+tempResult.length());
		//this checks if it meets the 8 bit length
		int length = tempResult.length();
		
		if(jump == 0)
		{
			System.out.println("I AM 8");
			if(length < 8)
			{
				for(int i = 0; i < 8-length; i++)
				{
					result = result + "0";
				}
			}
			else
				if(length == 32)
				{
					//takes only the "first" 8 bits of the result
					tempResult = tempResult.substring(24, 32);
				}
			//adds tempResult to result for proper placement if there were < 8 bits in the binaryToString
			result = result + tempResult;
			//error messaging (kind of not "error" because it still returns something
			if(number > 127)
			{
				System.out.println("The number input: "+number+", was greater than what was alotted, so the first 8 bits of it will be used instead");
			}
			else
				if(number < -127)
				{
					System.out.println("The number input: "+number+", was less than what was alotted, so the first 8 bits of it will be used instead");
				}
		}
		else
		{
			if(jump == 1)
			{
				System.out.println("I AM 12");
				if(length < 12)
				{
					for(int i = 0; i < 12-length; i++)
					{
						result = result + "0";
					}
				}
				else
					if(length == 32)
					{
						//takes only the "first" 8 bits of the result
						tempResult = tempResult.substring(20, 32);
					}
				//adds tempResult to result for proper placement if there were < 8 bits in the binaryToString
				result = result + tempResult;
				//error messaging (kind of not "error" because it still returns something
				if(number > 1023)
				{
					System.out.println("The number input: "+number+", was greater than what was alotted, so the first 12 bits of it will be used instead");
				}
			}
			else if(jump == 2)
			{
				System.out.println("I AM 9");
				if(number < 0)
				{
					result = result + "1";
				}
				else
				{
					result = result + "0";
				}
				if(length < 9)
				{
					for(int i = 0; i < 9-length; i++)
					{
						result = result + "0";
					}
				}
				else
					if(length == 32)
					{
						//takes only the "first" 8 bits of the result
						tempResult = tempResult.substring(23, 32);
					}
				//adds tempResult to result for proper placement if there were < 8 bits in the binaryToString
				result = result + tempResult;
				//error messaging (kind of not "error" because it still returns something
				if(number > 255)
				{
					System.out.println("The number input: "+number+", was greater than what was alotted, so the first 9 bits of it will be used instead");
				}
				else if(number < -255)
				{
					System.out.println("The number input: "+number+", was less than what was alotted, so the first 9 bits of it will be used instead");
				}
			}
			else if(jump == 3)
			{
				System.out.println("I AM 10");
				if(length < 10)
				{
					for(int i = 0; i < 10-length; i++)
					{
						result = result + "0";
					}
				}
				else
					if(length == 32)
					{
						//takes only the "first" 8 bits of the result
						tempResult = tempResult.substring(21, 32);
					}
				//adds tempResult to result for proper placement if there were < 8 bits in the binaryToString
				result = result + tempResult;
				//error messaging (kind of not "error" because it still returns something
				if(number > 1023)
				{
					System.out.println("The number input: "+number+", was greater than what was alotted, so the first 10 bits of it will be used instead");
				}
			}
		}
		return result;
	}
}