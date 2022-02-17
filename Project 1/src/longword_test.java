
public class longword_test 
{
	public void runTests()
	{
		longword word1 = new longword();
		longword word2 = new longword();
		bit bt = new bit();
		bt.set();
		
		System.out.println("Whole word1: " +word1.toString());
		System.out.println("Word1 using, getBit 3: " +word1.getBit(3).toString());
		word1.set(1212);
		System.out.println("Whole word1: " +word1.toString());
		System.out.println("Word1 using, getBit 10: " +word1.getBit(10).toString());
		
		System.out.println();
		word1.set(1);
		System.out.println("Whole word1: " +word1.toString());
		word1.setBit(8, bt);
		System.out.println("Word1 using, setBit 8 (once): " +word1.toString());
		
		System.out.println();
		word1.setBit(24, bt);
		word1.setBit(3, bt);
		word1.setBit(30, bt);
		System.out.println("Word1 using, setBit 3, 24, 30 (multiple): " +word1.toString());
		
		System.out.println();
		word1.set(1111111);
		word2.set(22222221);
		System.out.println("This is word1: " +word1.toString());
		System.out.println("This is word2: " +word2.toString());
		
		System.out.println();
		System.out.println("Word1 and word2 using, and: " +word1.and(word2).toString());
		System.out.println("Word1 and word2 using, or : " +word1.or(word2).toString());
		System.out.println("Word1 and word2 using, xor: " +word1.xor(word2).toString());
		
		System.out.println();
		word1.set(120);
		System.out.println("Word 1 is: " +word1.toString());
		System.out.println("Word1 using, not:" +word1.not().toString());
		
		System.out.println();
		word1.set(8191);
		System.out.println("This is word1: " +word1.toString());
		System.out.println("This is word1 rightshifted 3:" +word1.rightShift(4).toString());
		System.out.println("This is word1 leftshifted 3: " +word1.leftShift(3).toString());
		
		System.out.println();
		word1.set(1548353);
		System.out.println("This is word1: " +word1.toString());
		System.out.println("This is word1 rightshifted 7:" +word1.rightShift(7).toString());
		System.out.println("This is word1 leftshifted 6: " +word1.leftShift(6).toString());
		
		System.out.println();
		System.out.println("This word1: " +word1.toString());
		System.out.println("This is the unsigned value of word1: " +word1.getUnsigned());
		System.out.println("This is the signed value of word1: " +word1.getSigned());
		
		word2.set(-6666525);
		System.out.println("Binary of word2: " +word2.toString());
		System.out.println("This is the unsigned value of word1: " +word2.getUnsigned());
		System.out.println("This is the signed value of word1: " +word2.getSigned());
		
		word1.set(111111);
		System.out.println("Word1 : " +word1.toString());
		word2.set(1);
		System.out.println("Word2 : " +word2.toString());
		word2.copy(word1);
		System.out.println("Word2 using copy" +word2.toString());
		
		
	}
}