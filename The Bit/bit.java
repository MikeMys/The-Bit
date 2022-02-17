//bit
//Michael Mysliwiec

public class bit implements Ibit
{
 private int value;   //the bit

 public bit()
 {
  value = 0;
 }

 //set the value of the bit
 public void set(int value)
 {
  if(value == 0)
  {
   this.value = value;		//sets the private value to be equal to the one set by the user (0)
  }
  else
   if(value == 1)
   {
    this.value = value;		//sets the private value to be equal to the one set by the user (1)
   }
   else
   {
    System.out.println("The bit can be set to only either 0 or 1");			//make sure it isn't set to anything else
   }
 }

 //change value from 0 to 1 and 1 to 0
 public void toggle()
 {
  if(value == 1)
  {
   value = 0;				//changes the bit from 1 to 0
  }
  else
   if(value == 0)
   {
    value = 1;				//changes the bit from 0 to 1
   }
   else
    System.out.println("The bit was not set correctly");		//under the random scenario that bit wasn't equal to 1 or 0
 }

 //sets the bit to 1
 public void set()
 {
  value = 1;			//changes bit to 1
 }

 //sets the bit to 0
 public void clear()
 {
  value = 0;			//changes bit to 0
 }

 //returns the current value
 public int getValue()
 {
  return value;			//returns the numerical value of the bit
 }

 //performs "and" on two bits and returns a new bit set to the result
 //@Override
 public bit and(bit other)
 {
  bit result = new bit(); // create new bit for the result
  if(value == 0)
  {
   result.clear();   //because antying and 0 is zero
  }
  else
  {
   if(other.getValue() == 0)
   {
    result.clear(); //anything and 0 is zero
   }
   else
    result.set(); //sets the final bit to 1
  }
  return result; //the new bit result
 }

 //performs "or" on tewo bits and returns a new set to the result
 public bit or(bit other)
 {
  bit result = new bit();	//create new bit for result
  
  if(value == 1)
  {
   result.set();			//if either is equal to 1 then it is 1
  }
  else
  {
   if(other.getValue() == 1)
   {
    result.set();			//if either is equal to 1 then it is 1
   }
   else
    result.clear();			//if neither is equal to 1 then it is 1
  }

  return result;
 }

 //performs "xor" on teo bits and returns a new bit set to the result
 public bit xor(bit other)
 {
  bit result = new bit();
  if(value == 1)
  {
   if(other.getValue() == 0)
    result.set();						//if they are the same then it is 0, otherwise it is 1
   else
    result.clear();
  }
  else
  {
   if(other.getValue() == 1)
    result.set();
   else
    result.clear();
  }
  return result;
 }

 //performs not"on the existing bit, returing the result as a new bit
 public bit not()
 {
  bit result = new bit();
  if(value == 1)
  {
	  result.clear();					//should just make 0 to 1 and 1 to 0
	  return result;
  }
  else 
  {
	  result.set();						//should just make 1 to 0 and 0 to 1
  }

  return result;
 }

 @Override
 public String toString()
 {
  return value+"";						//return the value as a string??
 }

}