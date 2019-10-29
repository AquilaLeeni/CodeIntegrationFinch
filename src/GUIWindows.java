import javax.swing.JOptionPane;

public class GUIWindows {
	
	public String mainWindowMenu() //Method to get user's mode choice via a drop-down list
	{
		Object [] selectionValues = {"Curious Finch", "Scaredy Finch", "Help", "Quit"}; //Creates an array to hold the possible choices. Has to be object to match parameter of InputDialog
		
		//Creates input for user using drop-down menu
		String modeChoice = (String) JOptionPane.showInputDialog(null,"Welcome to the Detect Object Program\n\nChoose which mode to run:\n\n","Detect Object - Main Window",JOptionPane.INFORMATION_MESSAGE, null,selectionValues, null);
		
		return modeChoice; //Return their choice
	}
	
	public void helpWindow() //Method for window to give user help on how the program works
	{
		JOptionPane.showMessageDialog(null, "•For Curious Finch, the robot will follow an object.\n"
				+ "•For Scaredy Finch, the robot will avoid objects.\n"
				+ "•Once you select a mode, the robot will start moving\n"
				+ "•To stop the program, place the robot on it's tail.", "Detect Object - Help", JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	public void logOfExecution(String mode, long startTime, int objects)
	{
		long executionTime = (System.currentTimeMillis() - startTime)/1000; //Subtracts startTime from current time and divide by 1000 for seconds
		
		//Displays information about execution in a pop up window
		JOptionPane.showMessageDialog(null, "Mode of Finch = " + mode + "\n"
				+ "Duration of execution = " + executionTime + " seconds\n"
				+ "Objects encountered = " + objects, "Detect Object - Log of execution", JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	public void exitingProgram()
	{
		JOptionPane.showMessageDialog(null, "Exiting program...", null, JOptionPane.WARNING_MESSAGE);
	}
	
}
