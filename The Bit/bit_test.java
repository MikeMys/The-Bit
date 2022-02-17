//bit_test
//Michael Mysliwiec

public class bit_test
{
  public static void main (String args[])
  {
      bit bit1 = new bit();
      bit bit2 = new bit();

      bit1.set(1);
      System.out.println("The current value of bit 1 is: " +bit1.getValue());

      bit1.toggle();
      System.out.println("The value of bit1 has changed to: " +bit1.getValue());

      bit1.set();
      System.out.println("This should set bit1 to 1, bit1 is equal to: " +bit1.getValue());

      bit1.clear();
      System.out.println("This should set bit1 to 0, bit1 is equal to: " +bit1.getValue());

      System.out.println("The current value of bit1 is: " +bit1.getValue());

      bit1.set();
      bit2.clear();
      System.out.println("Doing AND between a 0 and a 1 should result in: " +bit1.and(bit2).getValue());  //should be 0

      bit1.set();
      bit2.set();
      System.out.println("Doing AND between a 1 and a 1 should result in: " +bit1.and(bit2).getValue());  //should be 1


      bit1.set();
      bit2.clear();
      System.out.println("Doing OR between a 0 and a 1 should result in: " +bit1.or(bit2).getValue());  //should be 1

      bit1.clear();
      bit2.clear();
      System.out.println("Doing OR between a 0 and a 0 should result in: " +bit1.or(bit2).getValue());  //should be 0

      bit1.set();
      bit2.clear();
      System.out.println("Doing XOR between a 0 and a 1 should result in: " +bit1.xor(bit2).getValue());  //should be 1

      bit1.set();
      bit2.set();
      System.out.println("Doing XOR between a 1 and a 1 should result in: " +bit1.xor(bit2).getValue());  //should be 0

      bit1.set();
      System.out.println("Performing NOT on a 1 shoulld result in: " +bit1.not().getValue());    //should be 0

      bit1.set(2);  //should show error
      
      System.out.println("The bit as a string is: " +bit1.toString());
    }
  }