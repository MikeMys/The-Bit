import java.util.Arrays;

public class longword implements ILongword 
{
	private bit[] arr = new bit[32];
	
	public longword()			//Done
	{	
		for(int a =0; a<32;a++)
		{
			arr[a] = new bit();
		}
	}
	
	//get the bit that is in position i
	public bit getBit(int i)		//Done
	{
		return arr[(31-i)];
	}

	//sets the value of bit in location i
	public void setBit(int i, bit value)		//Done
	{
		arr[(31-i)].set(value.getValue());
	}
	
	//performs "and" on the two longwords and returns a new one
	public longword and(longword other)			//Done
	{
		longword result = new longword();	//returning longword
		int y = 31;						//allows the bits to be set in the correct order
		for(int x = 0; x < 32;x++)		//loop running through each bit
		{
			result.setBit(y, arr[x].and(other.arr[x]));	//uses and from bit
			y--;						//runnning opposite of the loop numerically
		}
		
		return result;
	}
	
	//performs "or" on the two longwords and returns a new one
	public longword or(longword other)			//Done
	{
		longword result = new longword();		//returning longword
		int y = 31;						//allows bits to be set in the correct order
		for(int x = 0;x < 32 ;x++)		//runs through each bit
		{
			result.setBit(y, arr[x].or(other.arr[x]));	//uses or from bit
			y--;						//runnning opposite of the loop numerically
		}
		
		return result;
	}
	
	//performs "xor" on the two longwords and returns a new one
	public longword xor(longword other)			//Done
	{
		longword result = new longword();		//longword that is returned
		int y = 31;						//allows bits to be set in correct order
		for(int x = 0;x < 32;x++)		//runs though each bit
		{
			result.setBit(y, arr[x].xor(other.arr[x]));		//uses xor from bit
			y--;					//runnning opposite of the loop numerically
		}
		return result;
	}
	
	public longword not()						//Done
	{
		bit bt = new bit();
		longword result = new longword();		//returning longword
		int y = 31;						//allows the bits to be set in order
		for(int x = 0;x < 32;x++)		//runs through each bit
		{
			result.setBit(y,  arr[x].not());
			y--;					//runnning opposite of the loop numerically
		}
		for(int a = 0;a < 32; a++)
		{
			if(result.getBit(a).getValue() == 0)			//adds 1 after the not (twos compliment)
			{
				bt.set();
				result.setBit(a, bt);
				break;
			}
			else
			{
				if(result.getBit(a).getValue() == 1)
				{
					bt.clear();
					result.setBit(a, bt);
				}
			}
		}
		return result;
	}
	
	public longword rightShift(int amount)			//Done
	{
		longword result = new longword();		//returning longword
		int hold;
		int num = 31;			//runs opposite of loop to correctly set bits
		for(int x = 0;x < amount; x++)			//runs though each bit
			{
				result.arr[x].clear();			//sets to zero if its rightshifted out
				num--;							//opposite of loop
			}
		for(int y = amount; y < 32; y++)
		{
			hold = y-amount;
			result.setBit(num, arr[hold]);		//sets the bits where they should be if you moved according to the new zeroes
			num--;
		}
		return result;
	}
	
	public longword leftShift(int amount)			//Done
	{
		longword result = new longword();		//returning longword
		int hold;
		int num = 0;			//runs opposite of the loop to correctly set bits
		for(int x = 31;x > 31-amount;x--)		//runs through each bit
		{
			result.arr[x].clear();			//sets to zero if its leftshifed in
			num++;						//opposite of loop
		}
		for(int y = 31 - amount;y >= 0;y--)
		{
			hold = y + amount;
			result.setBit(num, arr[hold]);		//sets the bits where they should be if you moved according to the new zeroes
			num++;
		}
		return result;
	}
	
	@Override
	public String toString()			//array toString, 
	{									//MAYBE DONE
		String result = "";
		
		result = Arrays.deepToString(arr);			//sets the bit array to an ordered list of strings seperated by commas
		
		return result;
	}
	
	public long getUnsigned()					//NOT Done
	{
		long result = 0;
		long hold = 1;			//holds the nth power
		for(int x = 31;x >= 0; x--)
		{	
			if(arr[x].getValue() == 1)		//if its equal to 1
			{
				result = result + hold;		//adding up the digit
			}
			hold = hold * 2;		//equivelant to 2 to the nth
		}
		return result;
	}
	
	public int getSigned()						//NOT DONE
	{
		int result = 0;
		int hold=1;			//holds nth power
		
		if(arr[0].getValue() == 0)				//if the sign bit is positive (0) then whole bit is positive
		{
			for(int x = 31;x >= 0; x--)
			{
				if(arr[x].getValue() == 1)
				{
					result = result + hold;			//adding up the digit
				}
				hold = hold * 2;			//equivelant to nth power
			}
		}
		else					//if first bit is (1) then number is negative
		{
			for(int x = 31;x >= 0; x--)
			{
				if(arr[x].not().getValue() == 1)				//does the not and if its equal to 1 then count position
				{
					result = result + hold;			//adding up the digit
				}
				hold = hold * 2;			//holds to power of 2 to the n
			}
			result = result * -1;			//always has to return negative if in the loop (needs to be when signed)
			result-=1;						//since its negated it always has to have 1 binary added (twos complement) -> which is equivelant to a minus 1
		}
	
		return result;
	}
	
	//copies the values of the bits from another longword into this one
	public void copy(longword other)			//Done
	{
		//bit hold = new bit();
		for(int x = 0;x < 32; x++)			//runs through each digit
		{
			if(other.arr[x].getValue() == 1)			//if its a 1 in the other array the current on becomes 1 otherwise becomes 0, supposed to mimic it
			{
				arr[x].set();			//set 1
			}
			else
			{
				arr[x].clear();			//set 0
			}
		}
	}
	
	//set the value of the bits of this longword
	public void set(int value)			//think of twos compliment and negatives
	{									//maybe actually done
		//imagine number -1246 to binary
		if(value >= 0)				//if its positive
		{
			int hold = value;			//just placeholder
			int temp;
			for(int x = 31;x >= 0; x--)
			{
				temp = hold%2;				//temportarily gets the remainder (mod)
				if(temp == 1)				//if it would have a 1 in the binary place (not 0)
				{
					arr[x].set();			//sets it to 1
				}
				else
				{
					arr[x].clear();			//if not set to 0
				}
				if(hold == 1)				//this means if hold is at 1 -> stops infinite loop
					hold = 0;
				else
					hold = hold/2;			//if its not one then does the division that the mod represents and puts it in placeholder value
			}
		}
		else					//means the digit is negative
		{
			int hold = value * -1;			
			int temp;
			for(int x = 31;x >= 0; x--)
			{
				temp = hold%2;				//if it would have a 1 in the binary (mod)
				if(temp == 1)				//if its a 1 then it sets to 0
				{
					arr[x].clear();
				}
				else						//if its a 0 it sets to 1
				{	
					arr[x].set();
				}
				if(hold == 1)
					hold = 0;
				else
					hold = hold/2;			//divides by 2/ going down a power
			}
			if(arr[31].getValue() == 1)		//adding the 1 for twos compliment
			{
				for(int a = 31;a >= 0;a--)
				{
					arr[a].clear();			//recursively checking if there is a 1 in the next binary -> if so makes a 0 and carries 1 over
					if(arr[a-1] != null && arr[a-1].getValue() == 1)
					{
						continue;			//just checkin if its a true value
					}
					else
					{
						arr[a-1].set();		//accounts for the carried 1 when recursion ends
						break;
					}
				}
			}
			else
				arr[31].set();		//if there is in fact a 0 in the first place it has 1 added for compliment rule
		}
	}
}
