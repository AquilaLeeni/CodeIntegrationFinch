import javax.swing.JOptionPane;


public class CodeIntegration {
	public static void main(String args[]) throws Exception
	{

		Object [] selectionValues = {"Task 1: Search for Light", "Task 2: Draw Shape", "Task 3: Navigate", "Task 4: Zigzag", "Task 5: Detect Object", "Task 6: Dance", "Task 7: Tilt Control", "Quit"}; 
		
		//Creates input for user using drop-down menu
		String mode = (String) JOptionPane.showInputDialog(null,"Assignment Code Integration\n\nChoose which mode to run:\n\n","Code Integration - Main Window",JOptionPane.INFORMATION_MESSAGE, null,selectionValues, null);
		
		if (mode.equals("Task 1: Search for Light")){
			SearchForLight.main(args);
		}
		
		else if (mode.equals("Task 2: Draw Shape")){
			FinchDrawShapes.main(args);
		}
		
		else if (mode.equals("Task 3: Navigate")){
			myFinchNavigationSystem.main(args);
		}
		
		else if (mode.equals("Task 4: Zigzag")){
			StartPage.main(args);
		}
		
		else if (mode.equals("Task 5: Detect Object")){
			mainClass.main(args);
			}
		
		else if (mode.equals("Task 6: Dance")){
			Assignment2_Dance.main(args);
		}
		
		else if (mode.equals("Task 7: Tilt Control")){
			Main.main(args);
		}
		
		else if (mode.equals("Quit"))
		{
			System.exit(0);
		}

	}
}
