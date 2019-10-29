
import javax.swing.JOptionPane;

public class mainClass {
	//MAIN PROGRAM
	public static void main (String args[])
	{
		//Initialise variables to use in the main program
		String modeChoice = ""; 
		long startTime = 0;
		int objectsEncountered = 0;
		
		DetectObjectModes detectObject = new DetectObjectModes(); //Creates object of DetectObjectModes
		GUIWindows GUIObject = new GUIWindows(); //Creates object of GUIWindows
		
		JOptionPane.showMessageDialog(null, "Place Finch on level surface to start", "To start program", JOptionPane.INFORMATION_MESSAGE); //Output instruction to user
		detectObject.LevelCheck(); //Calls method to check if Finch is level and instruct user
		
		do //Runs the following code as the user needs to make a choice first
		{
			modeChoice = GUIObject.mainWindowMenu(); //Calls method to display menu and get user's choice
			startTime = System.currentTimeMillis(); //Stores the current time in startTime
			
			if (modeChoice.equals("Curious Finch")) //Checks if the user's choice is "Curious Finch"
			{
				objectsEncountered = detectObject.Curious_Finch(); //If true, it calls Curious Finch method and stores in objectsEncountered
				break;
			}
			
			else if (modeChoice.equals("Scaredy Finch")) //Checks if the user's choice is "Scaredy Finch"
			{
				objectsEncountered = detectObject.Scaredy_Finch(); //If true, it calls Sacredy Finch method and stores in objectsEncountered
				break;
			}
			
			else if (modeChoice.equals("Help")) //Checks if the user's choice is "Help"
			{
				GUIObject.helpWindow(); //If true, it calls helpWindow method
			}
			
			else //If none of the above conditions are true, moceChoice must equal "Quit"
			{
				GUIObject.exitingProgram();; //Outputs message to notify user
				System.exit(0); //Uses system.exit and returns 0 by convention
			}
			
		} while (!modeChoice.equals("Quit")); //Needs to loop in case user chooses "Help" so window will be open
		
		detectObject.disconnectFinch(); //Calls method to disconnect Finch
		
		
		//To get user's log choice via a pop up window
		String logChoice = (String) JOptionPane.showInputDialog(null,"Do you want to see the log of execution?\n\n(Yes or No)\n\n","Detect Object - Log of Execution",JOptionPane.INFORMATION_MESSAGE, null, null, null);
		if (logChoice.equals("Yes") || logChoice.equals("yes")) //If the user's input is 'Yes' or 'yes'
		{
			GUIObject.logOfExecution(modeChoice, startTime, objectsEncountered); //Calls logOfExecution method with parameters
		}
		
		else
		{
			GUIObject.exitingProgram();; //If not 'Yes' or 'yes' then outputs message to exit program
		}
		
		
	}
	
}
