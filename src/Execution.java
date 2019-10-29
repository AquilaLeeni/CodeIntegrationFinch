import java.util.Random;

public class Execution {

	public static void End()//the process that symbolises that the finch is finished
	{
		Main.robot.setLED(0, 0, 0);//the finch led is turned off
		Main.robot.buzz(1, 3000); // the finch robot buzzes
		Main.robot.sleep(1000); // the program sleeps for a 1 second
	}
	public static void Exe()// execution method 
	{
		System.out.println("Execution starts in 5 seconds."); //letting the user know when the execution begins
		Main.robot.sleep(5000); //the program sleeps for 5 seconds
		
		long exe_start = System.currentTimeMillis(); 
		long exe_end = exe_start + (Main.tilt_period*1000); //timer 
		while (System.currentTimeMillis() < exe_end) // while loop timer
		{
			Main.robot.setLED(0, 255, 0); //sets the finch LED to green
			int arrayLength = Main.rec.size(); //finds the number of recorded inputs  
			for(int x=1; x<arrayLength -1;x++) //for the length of the array 
			{					
				Integer exe_number = Main.rec.get(x); //the number of the array is read 
				int five = 5; //sets a value of 5
				Random random = new Random(); // random variable object 
				int y = random.nextInt(51) + 200; //the values are between 200 and 250
					
				switch(exe_number)    //depending on the numbers read a different setting is used
				{
					case 1:
						Main.robot.setWheelVelocities(y, y, five/10);//velocity set to positive 						
						break;
					case 2:
						Main.robot.setWheelVelocities(-y, -y, five/10);//velocity set to negative
						break;
					case 3:
						Main.robot.setWheelVelocities(0, y, five/10);//right wheel only set to positive 
						break;
					case 4:
						Main.robot.setWheelVelocities(y, 0, five/10);//left wheel only set to positive 
						break;
					case 5:
						Main.robot.setWheelVelocities(0, 0, five/10);//wheels set to stop
						break;
				}
				
			}
		}
	System.out.println("Program Finished!");//informs the user program is complete
	End();// the end method is run
	Main.robot.buzz(1, 3000);//finch gives it final beep
	}
}