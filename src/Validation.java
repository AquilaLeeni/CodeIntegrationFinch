import java.util.InputMismatchException;
import java.util.Scanner;

public class Validation 
{

	public static void Valid() //validation method
	{
		int i; 
		i = 0;
		
		while(i == 0) //until i is changed this while loop will continue 
		{
			try
			{
			Scanner input = new Scanner(System.in); //scanner reads user inputs 
			System.out.println("How long would you like to record inputs for?");
			System.out.println("This value should be between 1 and 20 seconds");
			
			
			Main.tilt_period =  input.nextInt(); //tilt_period is retrieved from the main class
			}									//user's next input is read
			catch(InputMismatchException E)
			{	
				System.out.println("Only numbers can be used.");
			}
			
			if (1<=Main.tilt_period && Main.tilt_period<=20)// if the value is between 1 and 20 
			{												// the value of i is changed breaking the while loop
				i = 1;
				System.out.println("Valid value.");			// the user is informed of their choice and its validity 
				System.out.println("You selected "+Main.tilt_period+".");
					
				Recording.Rec();
			}
			else
			{	
				System.out.println("Invalid value.");        // if the value is anything else the user is made aware 
				System.out.println("Value must be between 1 and 20.");// that their value is invalid for the program
				
				
			}
		
		
		}
	}
}
