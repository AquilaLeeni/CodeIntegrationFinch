public class Recording {

	public static void Rec()
	{	
		int a = 255, b = 0, c = 0; //integer values for the LED 

		System.out.println("Recording starts in 5 seconds."); //lets the user know recording begins in 5 seconds
		Main.robot.sleep(5000);	//the program stops for 5 seconds to accommodate for the user
		
		long rec_start = System.currentTimeMillis(); // the current system time 
		long rec_end = rec_start + (Main.tilt_period*1000); // the timer made by the time inputed
		
		while (System.currentTimeMillis() < rec_end)// while loop that works real time
		{
			Main.robot.setLED(a, b, c);//the integer values are used 
			Main.robot.sleep(500);//the robot is slept for 0.5 seconds for first orientation measurement

			if (Main.robot.isBeakUp() == true)//if the beak is forward the value reads true
			{
				System.out.println("forwards");//outputs the associated movement to the orientation
				Main.rec.add(1);     //1 which equals forward is added the the recording array
				Main.robot.buzz(1, 100);  // the finch beeps 

			}
			if (Main.robot.isBeakDown() == true)// each orientation boolean has an associated 
			{									// movement and value
				System.out.println("backwards");
				Main.rec.add(2);
				Main.robot.buzz(1, 100);

			}
			if (Main.robot.isLeftWingDown() == true)
			{
				System.out.println("left");
				Main.rec.add(3);
				Main.robot.buzz(1, 100);

			}
			if (Main.robot.isRightWingDown() == true)
			{
				System.out.println("right");
				Main.rec.add(4);
				Main.robot.buzz(1, 100);

			}
			if (Main.robot.isFinchLevel() == true)
			{
				System.out.println("stop");
				Main.rec.add(5);
				Main.robot.buzz(1, 100);

			}
			else
			{
				rec_end += 50;      // to account for the imperfect sensor system if there isn't a reading
			}						// the user is given an extra 0.5 seconds to make up for the missing reading
			
		}
		Execution.Exe();
		
	}
	
}