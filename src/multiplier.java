
public class multiplier 
{
	public static longword multiply(longword a, longword b)
	{
		RippleAdder rip = new RippleAdder();
		
		longword result = new longword();
		int pow = 1;					//holds the order of the power
		int count = 0;					//count is used to check if either longword is negative so we can adapt accordingly
		if(a.getBit(31).getValue() == 1)			//if a is negativw
		{
			a = a.not();
			count++;
		}
		if(b.getBit(31).getValue() == 1)		//if be is negative
		{
			b = b.not();
			count++;
		}
		for(int i = 0; i < 31; i++)				//loop going through each value of the second longword
		{
			//System.out.println(b.getBit(i).getValue());
				if(b.getBit(i).getValue() == 1)				//if its a 1 it means we will be multiplying that order by longword a
				{
					for(int y = 0; y < pow; y++)			//adds for the amount of times there is a power of
					{
						result = rip.add(result, a);			//this should add longword a to result the amount of times eqauvelant to the power (bit location)
					}
				}
			pow = pow * 2;
		}
		
		if(count % 2 == 1)								//this will convert the positve back to a negative if it should be one
		{
			result = result.not();
		}
		return result;
	}
}

