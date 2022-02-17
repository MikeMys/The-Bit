
public class multiplier_test 
{
	public void runTests()
	{
		multiplier multi = new multiplier();
		
		longword word1 = new longword();
		longword word2 = new longword();
		longword word3 = new longword();
		
		word1.set(105130);;
		word2.set(168);
		
		word3 = multi.multiply(word1, word2);
		System.out.println("This is word3: " +word3.getSigned());
		System.out.println(word3.toString());
		
		word1.set(-756100);;
		word2.set(2651);
		
		word3 = multi.multiply(word1, word2);
		System.out.println("This is word3: " +word3.getSigned());
		System.out.println(word3.toString());
		
		word1.set(9872);
		word2.set(-5432);
		
		word3 = multi.multiply(word1, word2);
		System.out.println("This is word3: " +word3.getSigned());
		System.out.println(word3.toString());
		
		word1.set(-9876);;
		word2.set(-5432);
		
		word3 = multi.multiply(word1, word2);
		System.out.println("This is word3: " +word3.getSigned());
		System.out.println(word3.toString());
	}
}
