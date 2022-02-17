
public class memory 
{
private bit[] mem = new bit[8192];
	
	public memory()			//Done
	{	
		for(int a =0; a < 8192;a++)		//creating the memory
		{
			mem[a] = new bit();
		}
	}
	
	
	
	public longword read(longword address)
	{
		//sending an address (longword) which is an index into the bits
		//like a pointer
		longword result = new longword();
		int num = address.getSigned();
		int placement = num * 8;		//supposed to be [0 - 31], then [8 - 39], 8 bit seperation is accounted for with each addition of address
		//System.out.println("Its happening here");

		System.out.println("This is placement: " +placement);
		for(int x = 0; x < 32; x++)
			{
				//System.out.println("This is mem @ x: " + mem[placement+x].toString());
				result.setBit(x, mem[placement+x]);			//setting the result to the appropriate spot of the addresss
			}
		
		return result;		
		
	}
	
	public void write(longword address, longword value)		//address is where its going and value is what is being placed
	{
		int num = address.getSigned();	
		//getting the placement 
		//write in value at position address
		for(int x = 0; x < 32; x++)
		{
			if(num+x < 8192)
			{
				//System.out.println("This is value @ x: "+value.getBit(x).getValue());
				if(mem[num+x] != null)
				{
					if(value.getBit(x).getValue() == 1)
					{
						mem[num+x].set();			//sets the bit to 1	
					}
					else
					{
						mem[num+x].clear();			//sets the bit to 0
					}
				}
			}
		}
	}
}
