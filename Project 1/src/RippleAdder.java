//Michael Mysliwiec

public class RippleAdder
{
	public static longword add(longword a, longword b)			//think its done? need to test
	{
		
		longword result = new longword();
		int carry = 0;
		bit bt = new bit();
		//produces various cases if a and b are holding a 1 or a 0 and proceeds accordingly
		for(int x = 0; x < 32;x++)
		{
			if(a.getBit(x).getValue() == 1)					//if a[x] = 1
			{
				if(b.getBit(x).getValue() == 1)				//if both a and b are 1 (add to next step)
				{
					if(carry == 1) 							//1 + 1 + 1 = 11
					{
						bt.set();
						result.setBit(x, bt);				//setting result to 1
					}
					else									//1 + 1 + 0 = 10
					{
						bt.clear();
						result.setBit(x, bt);				//if carry was 0 previously, spot in result will be 0 and new carry will be 1
						carry = 1;
					}
				}
				else
				{
					if(b.getBit(x).getValue() == 0)
					{
						if(carry == 1)						//1 + 0 + 1 = 10
						{
							bt.clear();
							result.setBit(x,  bt);				//set bit to 0
							carry = 1;
						}
						else								//1 + 0 + 0 = 01
						{
							bt.set();
							result.setBit(x,  bt);		//set bit to 1
							carry = 0;
						}
					}
				}
			}
			else
			{
				if(a.getBit(x).getValue() == 0)
				{
					if(b.getBit(x).getValue() == 1)
					{
						if(carry == 1)				//0 + 1 + 1 = 10
						{
							bt.clear();
							result.setBit(x, bt);		//set bit to 0
							carry = 1;
						}
						else						//0 + 1 + 0 = 01
						{
							bt.set();
							result.setBit(x, bt);		//set bit to 1
							carry = 0;
						}
					}
					else
					{
						if(b.getBit(x).getValue() == 0)
						{
							if(carry == 1)			//0 + 0 + 1 = 01
							{
								bt.set();
								result.setBit(x, bt);		//set bit to 1
								carry = 0;
							}
							else					//0 + 0 + 0 = 00
							{
								bt.clear();
								result.setBit(x, bt);		//set bit to 0
								carry = 0;
							}
						}
					}
				}
			}
		}	//end of for
		return result;
	}
	
	public static longword subtract(longword a, longword b)
	{
		RippleAdder rip = new RippleAdder();
		longword result = new longword();
		
		if(a.getSigned() >= 0)				//a is positive
		{
			if(b.getSigned() >= 0)			//b is positive
			{
				result = rip.add(a, b.not());	//positive minus positive is same as positive plus negative
			}
			else							//b is negative
			{
				result = rip.add(a, b.not());		//positive minus negative is same as addition
			}
		}
		else								//a is negative
		{
			if(b.getSigned() >= 0)			//b is positive
			{
				result = rip.add(a.not(), b);		//negative minus positive is same as two positives being added then negated
				result = result.not();
			}
			else							//b is negative
			{
				result = rip.add(a, b.not());		//negative minus negative is same as plus a positive
			}
		}
		
		return result;
	}
}
