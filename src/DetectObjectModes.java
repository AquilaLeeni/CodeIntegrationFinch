import java.util.Random;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class DetectObjectModes {

	private static Finch myFinch = new Finch(); //Initialise Finch object
	
	public void LevelCheck() //Method to check if Finch is level and instruct user
	{
		while (!myFinch.isFinchLevel())//If Finch is not level
		{
			myFinch.saySomething("Place Finch on level surface to start"); //Finch says something
			myFinch.sleep(3000);
		}
	}
	
	public int Curious_Finch()//Curious Finch method
	{
		int objectsEncountered = 0; //Sets objectsEncountered to 0
		while (!myFinch.isBeakUp()) //While the finch's beak is NOT up
		{
			long fiveSecTime = System.currentTimeMillis(); //Store the current time in fiveSecTime
			long elapsedTime = 0; //Set elapsedTime to 0
			
			while (elapsedTime<5000) //Runs following code while elapsedTime is less than 5 seconds
			{
				//Moves finch straight with green LED
				myFinch.setWheelVelocities(100, 100);
				myFinch.setLED(0, 255, 0);
				
				if (myFinch.isObstacle()) //If an object is detected
				{
					objectsEncountered +=1; //Increments objectsEncountered by 1
					finchMovements(); //Calls finchMovements method
					elapsedTime = 0; //Sets elapsedTime back to 0
				}
				
				else
				{
					//If no objects are detected, sets elapsedTime to current time subtracted by fiveSecTime
					elapsedTime = System.currentTimeMillis() - fiveSecTime; 
				}
				
			}
			
			//Once elapsedTime is greater than 5 seconds,  wheels are stopped and LED to red for 1 second
			myFinch.stopWheels();
			myFinch.setLED(255, 0, 0);
			myFinch.sleep(1000);
			
			//Finch turns right for 1 second
			myFinch.setWheelVelocities(50, -50, 1000);
		}	
		return objectsEncountered; //When the finch beak is up, objectsEncountered is returned
	}
	
	public void finchMovements() //Method for finch movements called in curious finch
	{
		//While there is an object on both sides of the finch, set LED to red and stop the wheels
		while (myFinch.isObstacleRightSide() == true && myFinch.isObstacleLeftSide() == true) 
		{
			myFinch.setLED(255, 0, 0);
			myFinch.stopWheels();
		}
				
		//While there is an object on the left side only, set LED to green & turn finch left & buzz finch
		while (myFinch.isObstacleRightSide() == false && myFinch.isObstacleLeftSide() == true) 
		{
			myFinch.setLED(0, 255, 0);
			myFinch.setWheelVelocities(-50, 50);
			myFinch.buzz(1000, 1000);
		} 
				
		//While there is an object on the right side only, set LED to green & turn finch right & buzz finch
		while (myFinch.isObstacleRightSide() == true && myFinch.isObstacleLeftSide() == false) 
		{
			myFinch.setLED(0, 255, 0);
			myFinch.setWheelVelocities(50, -50);
			myFinch.buzz(1000, 1000);
		} 
	}
	
	public int Scaredy_Finch() //Method for scaredy finch
	{
		int objectsEncountered = 0; //Sets objectsEncountered to 0
		while (!myFinch.isBeakUp())  //While the finch's beak is NOT up
		{
						
			//Moves finch straight with green LED
			myFinch.setWheelVelocities(100, 100);
			myFinch.setLED(0, 255, 0);	
			
			//If finch detects obstacle, increments objectsEncountered by 1
			if (myFinch.isObstacle())
			{
				objectsEncountered +=1;
				myFinch.saySomething("Object detected"); //Finch to say 'object detected'
				
				//Finch is stopped, LED to red, buzz for one second, backs up finch 
				myFinch.stopWheels();
				myFinch.buzz(1000, 1000);
				myFinch.setLED(255, 0, 0);
				myFinch.sleep(500);
				myFinch.setWheelVelocities(-100, -100, 1000);
				
				Random rand = new Random(); //Creates new instance of random object 
				//Random number between 1 and 2 to determine which direction to turn
				int randNoToTurn = rand.nextInt(2) + 1;
				
				//If random number is 1, turns finch left
				if (randNoToTurn==1)
				{
					myFinch.setWheelVelocities(-50, 50, 1000);
				}
				
				//Else turns finch right
				else 
				{
					myFinch.setWheelVelocities(50, -50, 1000);
				}
			}
		}
		
		return objectsEncountered; //When the finch beak is up, objectsEncountered is returned
	}
	
	public void disconnectFinch()
	{
		myFinch.quit();
	}
}
