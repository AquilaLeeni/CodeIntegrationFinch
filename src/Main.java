import java.util.ArrayList;                            //imports the methods for the array list
import edu.cmu.ri.createlab.terk.robot.finch.Finch;	   //finch methods

public class Main {

	static int tilt_period;                            //the integer tilt input value used as a timer
	static ArrayList<Integer> rec = new ArrayList<Integer>(); // the storage array for inputs 
	                              
	
	public static Finch robot = null;          //the finch method is initialised
	

	public static void main(String[] args)
	{
		robot = new Finch();				// the finch object is created name "robot"
		GUI.welcomeGUI();                   //the first method is called to run
		
	}
}