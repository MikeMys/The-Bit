
public class ALU_test 
{
	public void runTests()
	{
		//ALU test = new ALU();
		
		bit[] bt = new bit[4];
		for(int x = 0; x < 4; x++)
		{
			bt[x] = new  bit();
		}
		
		longword word1 = new longword();
		longword word2 = new longword();
		longword result = new longword();
		
		
		bt[0].clear();			//0
		bt[1].clear();			//0
		bt[2].clear();			//0
		bt[3].set();			//1
		
		word1.set(1165);
		word2.set(2536);
		System.out.println("Word1: " +word1.toString());
		System.out.println("Word2: " +word2.toString());
		
		result = ALU.doOp(bt, word1, word2);
		
		System.out.println("This is using and: " +result.toString());
		
		bt[0].set();			//1
		bt[1].clear();			//0
		bt[2].clear();			//0
		bt[3].set();			//1
		
		word1.set(1165);
		word2.set(2536);
		System.out.println("Word1: " +word1.toString());
		System.out.println("Word2: " +word2.toString());
		
		result = ALU.doOp(bt, word1, word2);
		
		System.out.println("This is using or: " +result.toString());
		
		bt[0].clear();			//0
		bt[1].set();			//1
		bt[2].clear();			//0
		bt[3].set();			//1
		
		word1.set(1165);
		word2.set(2536);
		System.out.println("Word1: " +word1.toString());
		System.out.println("Word2: " +word2.toString());
		
		result = ALU.doOp(bt, word1, word2);
		
		System.out.println("This is using xor: " +result.toString());
		
		bt[0].set();			//1
		bt[1].set();			//1
		bt[2].clear();			//0
		bt[3].set();			//1
		
		word1.set(1165);
		word2.set(2536);
		System.out.println("Word1: " +word1.toString());
		System.out.println("Word2: " +word2.toString());
		
		result = ALU.doOp(bt, word1, word2);
		
		System.out.println("This is using not: " +result.toString());
		
		bt[0].clear();			//0
		bt[1].clear();			//0
		bt[2].set();			//1
		bt[3].set();			//1
		
		word1.set(1165);
		word2.set(3);
		System.out.println("Word1: " +word1.toString());
		System.out.println("Word2: " +word2.toString());
		
		result = ALU.doOp(bt, word1, word2);
		
		System.out.println("This is using LeftShift: " +result.toString());
		
		bt[0].set();			//1
		bt[1].clear();			//0
		bt[2].set();			//1
		bt[3].set();			//1
		
		word1.set(1165);
		word2.set(3);
		System.out.println("Word1: " +word1.toString());
		System.out.println("Word2: " +word2.toString());
		
		result = ALU.doOp(bt, word1, word2);
		
		System.out.println("This is using RightShift: " +result.toString());
		
		bt[0].clear();			//0
		bt[1].set();			//1
		bt[2].set();			//1
		bt[3].set();			//1
		
		word1.set(1165);
		word2.set(3646);
		System.out.println("Word1: " +word1.toString());
		System.out.println("Word2: " +word2.toString());
		
		result = ALU.doOp(bt, word1, word2);
		
		System.out.println("This is using add: " +result.toString());
		
		bt[0].set();			//1
		bt[1].set();			//1
		bt[2].set();			//1
		bt[3].set();			//1
		
		word1.set(1165);
		word2.set(354);
		System.out.println("Word1: " +word1.toString());
		System.out.println("Word2: " +word2.toString());
		
		result = ALU.doOp(bt, word1, word2);
		
		System.out.println("This is using subtract: " +result.toString());
		
		bt[0].set();			//1
		bt[1].set();			//1
		bt[2].set();			//1
		bt[3].clear();			//0
		
		word1.set(1165);
		word2.set(35);
		System.out.println("Word1: " +word1.toString());
		System.out.println("Word2: " +word2.toString());
		
		result = ALU.doOp(bt, word1, word2);
		
		System.out.println("This is using multiply: " +result.toString());
		
	}
}
