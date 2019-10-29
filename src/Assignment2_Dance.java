import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class Assignment2_Dance {
	static Finch finch = new Finch(); //Creates Finch variable for program
	static ArrayList<String> hexNums = new ArrayList<String>(); //creates an array called hexNums
	
	public static void main(String args[]) throws Exception
	{
		boolean againLoop = true; //creates againLoop and makes it true
		while (againLoop==true) //while againLoop is true
		{
			
			String hexdec_num; //creates variable hexdec_num
		    
		    Scanner scan = new Scanner(System.in); //creates new variable for the user to input
			
		    System.out.print("/n/nEnter Hexadecimal Number of length 2: "); //prints message
		    hexdec_num = scan.nextLine(); //asks for their input
		    
		    while ((hexdec_num.length()>2 || hexdec_num.length()<2) || (TestHex(hexdec_num)==false)) //while their input length is not equal to 2 OR TestHex with their input is false
		    {
		    	System.out.println("Hex number must be 2 characters AND a Hex value: "); //print error message
		    	hexdec_num = scan.nextLine(); //ask for another input
		    }
		    	   
		    hexNums.add(hexdec_num); //once their input is valid, adds their input to the array
		    
		    // convert hexadecimal to decimal  
		    int dec_num = hex_to_decimal(hexdec_num); //calls hex_to_decimal function
		    
		    // convert decimal to binary
		    String bin_num = decimal_to_binary(dec_num); //calls decimal_to_binary function
		    
		    // convert decimal to octal
		    String octal = toOctal(dec_num); //calls toOctal function
		    
		    int wheelSpeed = finchSpeed(octal); //sets wheelSpeed equal to the call of finchSpeed
		    int greenLED = (dec_num%80)+60; //sets greenLED equal to remainder of dec_num divided by 80, + 60
		    int blueLED = (dec_num + greenLED) / 2; //sets blueLED to the average of dec_num and greenLED
		    
		    //prints all the information
		    System.out.println(hexdec_num+", "+octal+", "+dec_num+", "+bin_num+", speed = "+wheelSpeed+", LED colour (red "+ dec_num+", green "+greenLED+", blue "+blueLED+")");
		    
		    finch.setLED(dec_num, greenLED, blueLED); //sets finch LED to colours worked out
		    
		    finch.saySomething("Dancing begins");
		    for (int i=bin_num.length()-1;i>=0;i--) //use FOR loop to loop through binary string right to left
		    {
		    	char c = bin_num.charAt(i); //gets every character (1 or 0) and makes it equal to c
		    	if (c=='1') //if c is equal to 1
		    	{
		    		finch.setWheelVelocities(wheelSpeed, wheelSpeed, 1000); //set wheels to forward with speed calculated for 1 second
		    	}
		    	else //if it's not 1 then it's 0
		    	{
		    		finch.setWheelVelocities(-wheelSpeed, -wheelSpeed, 1000); //set wheels to backward with speed calculated for 1 second
		    	}
		    	    		
		    }
		    finch.setLED(0,0,0); //turn LED off
		    finch.saySomething("Dancing has ended");
		    
		    System.out.println("/n/nWould you like to enter another hex number? (YES or NO)"); //Ask if they want another hex number
		    String again = scan.nextLine(); //asks for their input
		    if (again.equals("NO")) //If they type 'NO'
		    {
		    	againLoop = false; //set againLoop to false
		    }
		    else
		    {
		    	againLoop = true; //else, set it to true
		    }
	    
		}
		//Once they type 'NO', it adds the hex numbers to a text file
		Writing_Data(hexNums); //calls function with array 
	}
	
	public static boolean TestHex(String value) { //Function to test if their input is Hex
		value = value.toUpperCase(); //Makes their input uppercase
        boolean test = false; //sets test to false
        String digits = "0123456789ABCDEF"; //creates string of hex values
        
        for (int i = 0; i < value.length(); i++) //For loop to loop through their hex number
        {
        	char c = value.charAt(i); //gets one character at a time of their input
        	
        	int d = digits.indexOf(c); //Tries to find where their input is in the String digits
        	
        	if (d>0) { //if index is bigger than 0
        		test = true; //set test to true
        	}
        	else { //If it cannot find an index, it returns -1
        		test = false; //set test to false
        		break; //break out of For loop
        	}
        }
        return test; //return true or false
    }
	
	public static int hex_to_decimal(String s) //Function to turn hex to decimal
    {
             String digits = "0123456789ABCDEF";
             s = s.toUpperCase();
             int val = 0;
             for (int i = 0; i < s.length(); i++)
             {
                 char c = s.charAt(i);
                 int d = digits.indexOf(c);
                 val = 16*val + d;
             }
             return val;
    }
	
	public static String decimal_to_binary(int n) //Function to turn decimal to binary
	{
		int count = 0;
        String x = "";
        String reversedString = "";
        while(n > 0)
        {
            int a = n % 2;
            if(a == 1)
            {
                count++;
            }
            x = x + "" + a;
            n = n / 2;
        }
        for(int i = x.length() - 1; i >= 0; i--) //for loop to loop through the srting right to left
        {
            reversedString = reversedString + x.charAt(i); //adds each character to reservsedString
        }
        return reversedString;
        
	}
	
	public static String toOctal(int decimal) //Function to turn decimal to octal
	{    
	    int rem; //declaring variable to store remainder
	    
	    String octal=""; //Declaring variable to store octal  
	    //declaring array of octal numbers  
	    
	    char octalchars[]={'0','1','2','3','4','5','6','7'};  
	    //writing logic of decimal to octal conversion   
	    
	    while(decimal>0)  
	    {  
	       rem=decimal%8;   
	       octal=octalchars[rem]+octal;   
	       decimal=decimal/8;  
	    }  
	    return octal;  
	} 
	
	public static int finchSpeed (String octal) //function to calculate speed of finch
	{
		int intOctal = Integer.parseInt(octal); //turns the octal number from string to integer
		
		if (intOctal < 60) //if octal is less than 60
		{
			intOctal = intOctal + 30; 
			return intOctal; //add 30 to octal and return it
		}
		
		else if (intOctal >255) //if it is more than 255 (max speed of finch)
		{
			intOctal = 255;
			return intOctal; //make it equal 255 and return it
		}
		
		else { return intOctal;} //otherwise return the original value
	}
	
	public static void Writing_Data(ArrayList<String> HexArray) throws IOException //Function to write to file
	{
		FileWriter writerhandle = new FileWriter ("H:\\My Documents\\Labs\\CS1702\\hexFile.txt"); //New writerhandle variable used to say where to store
		BufferedWriter bw = new BufferedWriter(writerhandle); //New bw variable used to write data
		
		String arrayAsString = HexArray.toString(); //Turns array to a string
		bw.write(arrayAsString); //writes the string to file
	
		//closes writer and handle
		bw.close();
		writerhandle.close();
		
		
	}
	

}
