import java.io.*;
import java.lang.String;

public class project2
{
	//I feel like 4 global variables isn't "too much"
	//I also think that this is a similar structure to a state machine, but isn't quite one because every step isn't completely individual
	
	static String totalCheck ="";
	static String repeat = null;
	static String eitherText = null;
	static String requiredText = null;
	
    public static void main (String args[])
    {
    	String operation = args[0];		//correctly holds value as string
    	
    	/*
    	 * If there is a + used in the command
    	 */
    	if(operation.indexOf("+") != -1)
    	{
    		System.out.println("Operation contains a +");
    		int charPlace = operation.indexOf("+");
    		
    		//System.out.println(charPlace);			//holds the location of the + in the string
    		
    		repeat = operation.substring(0, charPlace);			//this is what must occur once or more
    		//System.out.println(repeat);				//holds the part of the string that is to be found repeating in the file
    		
    		if(charPlace != operation.length()-1)			//this would mean that the + is not the last character in the string
    		{
    			int beginBracket = operation.indexOf("[");
    			int endBracket = operation.indexOf("]");
    			if(beginBracket != -1 && beginBracket == charPlace + 1)		//checks if there is a beginning bracket
    			{
    				if(endBracket != -1 && endBracket > beginBracket)		//checks if there is an ending bracket
    				{
    					eitherText = operation.substring(beginBracket+1, endBracket);		//this is whats in the brackets
    					//System.out.println(eitherText);
    				}
    			}
    			else		//this would be if there isn't a bracket and only letters
    			{
    				requiredText = operation.substring(charPlace+1, operation.length());
    				System.out.println("There are no brackets " +requiredText);
    			}
    		}
    	}
    	/*
    	 * If there is a * used in the command
    	 */
    	else if(operation.indexOf("*") != -1)			//returns where the first occurance of the character was
    	{
    		System.out.println("Operation contains a *");
    		int charPlace = operation.indexOf("*");
    		
    		//System.out.println(charPlace);			//holds the location of the * in the string
    		
    		repeat = operation.substring(0, charPlace);			//this is what must occur once or more
    		//System.out.println(repeat);				//holds the part of the string that is to be found repeating in the file
    		
    		if(charPlace != operation.length()-1)			//this would mean that the + is not the last character in the string
    		{
    			int beginBracket = operation.indexOf("[");
    			int endBracket = operation.indexOf("]");
    			if(beginBracket != -1 && beginBracket == charPlace + 1)		//checks if there is a beginning bracket
    			{
    				if(endBracket != -1 && endBracket > beginBracket)		//checks if there is an ending bracket
    				{
    					eitherText = operation.substring(beginBracket+1, endBracket);		//this is whats in the brackets
    					//System.out.println(eitherText);
    				}
    			}
    			else		//this would be if there isn't a bracket and only letters
    			{
    				requiredText = operation.substring(charPlace+1, operation.length());
    				System.out.println("There are no brackets " +requiredText);
    			}
    		}
    	}
    	else			//this means there is no + or *
    	{
    		requiredText = operation.substring(0, operation.length());			//contains what must occur in the text
    		System.out.println("There were no + or * " +requiredText);
    	}
    	
    	
    	
    	/*
    	 * This is where the file reading begins
    	 * After this point we need to start checking the file based on the input conditions
    	 */
        String filename = args[1];
        String line;
        int count = 0;
        try  
        {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            //int[] isRepeat = new int[2];
            while((line = br.readLine()) != null)
            {
            	count++;
                System.out.println(line);
                
                //This holds what either must happen zero or one + times
                int[] isRepeat = Repeating(repeat, line, requiredText);
                if(isRepeat[1] != 0 && operation.contains("+"))		//this is for + only
                {
                	/*
                	 * Will be implementing the second step of checking for [ ] and what they contain
                	 */
                	System.out.println("Match found on line "+count+", starting at position "+(isRepeat[0]+1)+" and ending at position "+(isRepeat[0] + isRepeat[1])+": "+totalCheck);
                	//System.out.println("Total number of letters repeated "+isRepeat[1]);
                    //System.out.println("Location that the String begins after is: "+isRepeat[0]);
                    //System.out.println("Location that the string ends at is: "+(isRepeat[0] + isRepeat[1]));
                }
                else
                if(operation.contains("*"))
                {
                	if(isRepeat[1] != 0)
                	{
                		//System.out.println(isRepeat[1]);
                    	System.out.println("Match found on line "+count+", starting at position "+(isRepeat[0]+1)+" and ending at position "+(isRepeat[0] + isRepeat[1])+": "+totalCheck);
                	}
                	else
                	{
                		//this means it had a * AND there was no repeat found, now we need either the [] or the required
                		//copy and paste the section in the isRepeat and make a new method called eitherOr and have
                		//it return if it is one: either or, and if it found them
                		
                		//this means eitherText happens and not required
                		if(eitherText != null)
                		{
                			for(int i = 0; i < line.length(); i++)			//this runs through the whole text file line
                	    	{
                				for(int a = 0; a < eitherText.length();a++)
                				{
                					if(eitherText.charAt(a) == line.charAt(i))
                					{
                						System.out.println("Match found on line "+count+", starting at position "+(i - 1)+" and ending at position "+(i)+": "+eitherText.charAt(a));
                						i = line.length();
                						break;
                					}
                				}
                	    	}
                			
                		}
                		else	//this means requiredText happens
                		if(requiredText != null)
                		{
                			int index = 0;
                			String check = "";
                			for(int i = 0; i < line.length(); i++)			//this runs through the whole text file line
                	    	{
                				if(requiredText.charAt(index) == line.charAt(i))
                				{
                					check = check + line.charAt(i);
                					index++;
                				}
                				else
                				{
                					check = "";
                					index = 0;
                				}
                				
                				if(check.equalsIgnoreCase(requiredText))
                				{
                                	System.out.println("Match found on line "+count+", starting at position "+(i - check.length()+1)+" and ending at position "+(i+1)+": "+check);
                					break;
                				}
                	    	}
                		}
                		else
                		System.out.println("There wasn't a match found for the repeating section");
                	}
                }
                else
                {
                	continue;
                }

            }
                        br.close();
            //System.out.println("This is where the magic happens");
        } 
        catch (Exception e) 
        {
            System.out.println("Shits broken");
        }

    }

    public static int[] Repeating(String repeat, String line, String required)
    {
    	int[] result = new int[2];
    	String check ="";
    	int index = 0;
    	int numRepeat = 0;
    	//System.out.println("This is what is being repeated: "+repeat);
    	for(int i = 0; i < line.length(); i++)			//this runs through the whole text file line
    	{
    		char c = line.charAt(i);
    		//System.out.println("This is c at "+i+": "+c);
			//System.out.println("This is check:" +check);
			//System.out.println("This is repeat:" +repeat);

    		if(check.equalsIgnoreCase(repeat) == false)
    		{
    			if(c == repeat.charAt(index))			//checks if the char is the same as the one in repeat at the point index
        		{
    				//means that we will be entering the first character of the string
    				if(check == "" && numRepeat == 0)
    				{
    					result[0] = i;
    				}
  
    				//System.out.println(c);
        			//System.out.println("This is index: "+index);
        			check = check + c;
        			index++;
        		}
    			else
    			{
    				if(numRepeat >= 1)
    				{
    					for(int k = 0; k < numRepeat;k++)
    					{
    						result[1] = result[1] + repeat.length();
    					}

    					result[1] = result[1] + check.length();
    					totalCheck = totalCheck + check;
    					
    					if(eitherText != null)
    					{
    						int tempCheck = totalCheck.length();
        					/* this is where I will be implementing the either text
        					 * none of this will happen is eitherText length is 0
        					 */
        					
        					for(int e = 0; e < eitherText.length(); e++)
        					{
        						if((i) < line.length())
        						{
        							if(eitherText.charAt(e) == line.charAt(i))
        							{
        								totalCheck = totalCheck + eitherText.charAt(e);
        								result[1] = result[1] + 1;
        								return result;
        							}
        						}
        					}
        					
        					//this means that nothing in eitherCheck was found
        					if(tempCheck == totalCheck.length())
        					{
        						totalCheck = "";
        						check = "";
        						index = 0;
        						numRepeat = 0;
        						result[0] = 0;
        						result[1] = 0;
        					}
    					}
    					else
    						if(requiredText != null)
        					{
        						String checker = "";
            					/* this is where I will be implementing the either text
            					 * none of this will happen is eitherText length is 0
            					 */
            					
            					for(int e = 0; e < requiredText.length(); e++)
            					{
            						if((i+e) < line.length())
            						{
            							if(requiredText.charAt(e) == line.charAt(i+e))
            							{
            								checker = checker + requiredText.charAt(e);
            								result[1] = result[1] + 1;
            							}
            						}
            						else
            						{
            							break;
            						}
            					}
            					
            					//this means that nothing in eitherCheck was found
            					if(checker.equalsIgnoreCase(requiredText))
            					{
            						totalCheck = totalCheck + checker;
            						//System.out.println("It is all there");
            						break;
            					}
            					else
            					{
            						totalCheck = "";
            						check = "";
            						index = 0;
            						numRepeat = 0;
            						result[0] = 0;
            						result[1] = 0;
            					}
        					}
    					else
    					{
    						//System.out.println("Either text is zero");
        					break;
    					}
    				}
    				else
    				{
    					numRepeat = 0;
    				}
    				check = "";
    				index = 0;
    			}
    		}
    		else
    		{
    			//System.out.println("This worked atleast once");
    			i--;
    			totalCheck = totalCheck + check;
    			check = "";
    			index = 0;
    			numRepeat++;
    		}
    	}    	
    	
    	return result;
    }
}