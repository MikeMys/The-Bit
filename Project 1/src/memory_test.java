
public class memory_test 
{
	public void runTests()
	{
		memory run = new memory();
		longword addy = new longword();
		longword val = new longword();
		
		addy.set(0);
		val.set(57635);

		run.write(addy, val);
		addy.set(0);			//it is multiplied by 8 in read
		System.out.println("This is reading at placement \n" +run.read(addy).toString());
		System.out.println("This is the end of memory \n");
		
		addy.set(1);
		val.set(128);
		run.write(addy, val);
		addy.set(0);
		System.out.println("This is reading at placement \n" +run.read(addy).toString());
		
	}
}
