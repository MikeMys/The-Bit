
public class RippleAdder_Test 
{
	public void runTests()
	{
		RippleAdder rip = new RippleAdder();
		
		longword word1 = new longword();
		longword word2 = new longword();
		longword word3 = new longword();
		
		word1.set(349525);
		word2.set(174762);
		
		System.out.println("This is word1: " +word1.toString());
		System.out.println("This is word1: " +word1.getSigned());
		System.out.println("This is word2: " +word2.toString());
		System.out.println("This is word2: " +word2.getSigned());
		
		word3 = rip.add(word1, word2);
		
		System.out.println("This is the addition of word1 and word2: " +word3.toString());
		System.out.println("This is the addition:   " +word3.getSigned());
		
		word1.set(349525);
		word2.set(-174762);
		
		System.out.println("This is word1: " +word1.toString());
		System.out.println("This is word1: " +word1.getSigned());
		System.out.println("This is word2: " +word2.toString());
		System.out.println("This is word2: " +word2.getSigned());
		
		word3 = rip.add(word1, word2);
		
		System.out.println("This is the addition of word1 and word2: " +word3.toString());
		System.out.println("This is the addition:   " +word3.getSigned());
		
		word1.set(9000000);
		word2.set(8000001);
		
		System.out.println("This is word1: " +word1.toString());
		System.out.println("This is word1: " +word1.getSigned());
		System.out.println("This is word2: " +word2.toString());
		System.out.println("This is word2: " +word2.getSigned());
		
		word3 = rip.subtract(word1, word2);
		
		System.out.println("This is the subtraction of word1 and word2: " +word3.toString());
		System.out.println("This is the subtraction: " +word3.getSigned());
		
		word1.set(70000000);
		word2.set(-10000010);
		
		System.out.println("This is word1: " +word1.toString());
		System.out.println("This is word1: " +word1.getSigned());
		System.out.println("This is word2: " +word2.toString());
		System.out.println("This is word2: " +word2.getSigned());
		
		word3 = rip.subtract(word1, word2);
		
		System.out.println("This is the subtraction of word1 and word2: " +word3.toString());
		System.out.println("This is the subtraction: " +word3.getSigned());
		
		word1.set(-65656656);
		word2.set(4321121);
		
		System.out.println("This is word1: " +word1.toString());
		System.out.println("This is word1: " +word1.getSigned());
		System.out.println("This is word2: " +word2.toString());
		System.out.println("This is word2: " +word2.getSigned());
		
		word3 = rip.subtract(word1, word2);
		
		System.out.println("This is the subtraction of word1 and word2: " +word3.toString());
		System.out.println("This is the subtraction: " +word3.getSigned());
		
		word1.set(-524287);
		word2.set(-16383);
		
		System.out.println("This is word1: " +word1.toString());
		System.out.println("This is word1: " +word1.getSigned());
		System.out.println("This is word2: " +word2.toString());
		System.out.println("This is word2: " +word2.getSigned());
		
		word3 = rip.subtract(word1, word2);
		
		System.out.println("This is the subtraction of word1 and word2: " +word3.toString());
		System.out.println("This is the subtraction: " +word3.getSigned());
		
		System.out.println("\n\n\n");
		
		word1.set(-578);
		word2.set(-967);
		
		System.out.println("longword1: " + word1.toString());
		System.out.println("longword1: " + word1.getSigned());
		System.out.println("longword2: " + word2.toString());
		System.out.println("longword2: " + word2.getSigned());
		word3 = RippleAdder.subtract(word1,  word2);
		System.out.println("longword3: " + word3.toString());
		System.out.println("longword3: " + word3.getSigned());
	}
}
