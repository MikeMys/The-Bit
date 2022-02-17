
public class ALU 
{
	public static longword doOp(bit[] operation, longword a, longword b)
	{	
		
		longword result = new longword();
		RippleAdder rip = new RippleAdder();
		multiplier multi = new multiplier();
		int hold = 1;
		int num = 0;
		for(int x = 0; x < 4; x++)
		{
			if(operation[x].getValue() == 1)
			{
				num = num + hold;
			}
			hold = hold * 2;
		}
		System.out.println(num);
		if(num == 8)				//and
		{
			//System.out.println("This is longword a: "+a.toString());
			//System.out.println("This is longword b: "+b.toString());
			result = a.and(b);
		}
		else
		if(num == 9)				//or
		{
			result = a.or(b);
		}
		else
		if(num == 10)				//xor
		{
			result = a.xor(b);
		}
		else
		if(num == 11)				//not a
		{
				result = a.not();
		}
		else
		if(num == 12)				//a will be leftshifted by b amount
		{
			result = a.leftShift(b.getSigned());
		}
		else
		if(num == 13)				//a will be rightshifted by b amount
		{
			result = a.rightShift(b.getSigned());
		}
		else
		if(num == 14)				//a will be added by b amount
		{
			result = rip.add(a,b);
		}
		else
		if(num == 15)				//a will be subtracted by b amount
		{
			result = rip.subtract(a,b);
		}
		else
		if(num == 7)				//a will be multiplied by b amount
		{
			result = multi.multiply(a,b);
		}
		else
		if(num == 0)
		{
				System.out.println("The loop was stopped");
		}
		else
		if(num == 3)
		{
			//jump
			System.out.println("I am Jumping");
		}
		else
		if(num == 2)
		{
				System.out.println("Done Printing");
		}
		else
		if(num == 1)
		{
			//moving
			System.out.println("Moving");
			computer CPU = new computer();

			bit[] temp = new bit[4];
			int reg = 15;
			for(int i = 0; i < 4; i++)
			{	temp[i] = new bit();
				if(a.getBit(15).getValue() == 1)
				{
					temp[i].set();
				}
				else
				{
					temp[i].clear();
				}
				reg--;
				//System.out.println("This is happening");
			}
			CPU.move(operation, temp, b);
		}
		else
		if(num == 4)
		{
				System.out.println("Comparing");
		}
		else if(num == 5)
		{
			System.out.println("Branching");
		}
		else if(num == 6)
		{
			System.out.println("I am either popping, pushing, returning or calling");
		}
		else				//safe case
		{
			System.out.println("The operation doesn't match :(");
		}
		
		return result;
	}
}
