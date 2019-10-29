
import javax.swing.JOptionPane;
import java.util.ArrayList;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;
//Below are the imports for creating & writing to TXT file
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.BufferedWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


public class myFinchNavigationSystem {

public static ArrayList<Integer> DRT = new ArrayList<Integer>(); //Creating ArrayList's which will store values and later make use of them throughout my methods
public static ArrayList<Integer> SS = new ArrayList<Integer>(); 
public static ArrayList<Character> Trace = new ArrayList<Character>();
public static Finch denisFinch;

public static void main(String[] args)
{
	denisFinch = new Finch();
	String s = "";
	do { //This loop & case will run the menu until command Q is selected
		s = myInterface.myMenu();
		switch(s)
		{
		case "Forwards - F":
			Forwards(denisFinch,myInterface.speedInput(0), myInterface.movementDurationInput(0)); //Passing 0, because we don't want to store anything to these values yet
			break;
		case "Backwards - B":
			Backwards(denisFinch, myInterface.speedInput(0), myInterface.movementDurationInput(0));
			break;
		case "Right - R":
			Right(denisFinch, myInterface.speedInput(0), myInterface.movementDurationInput(0));
			break;
		case "Left - L":
			Left(denisFinch, myInterface.speedInput(0), myInterface.movementDurationInput(0));
			break;
		case "Retrace - T":
			int amount = myInterface.retraceInput(0); //Storing input to amount to later compare to trace size in retrace method
			Retrace(denisFinch, amount);
			break;
		case "Write Log - W":
			WriteLog();
			break;
		default:
			denisFinch.buzz(440, 1000);
			JOptionPane.showMessageDialog(null, "You have quit the program");
			break; 
			}
		}
	while (!(s.equals("Quit - Q")));
 denisFinch.buzz(440, 1000);
 denisFinch.quit();
}
public static void WriteLog() 
{
	File output = new File("OUTPUT.txt");
	
	FileWriter writer; //Setting FileWriter object named as writer
	try {
		writer = new FileWriter(output); //Using the file named output
		BufferedWriter bf = new BufferedWriter(writer);
		
		Date currentDate = new Date();
		SimpleDateFormat currentHourFormat = new SimpleDateFormat("hh:mm:ss");
		bf.write("Below is the current time in HH:MM:SS format: ");
		bf.newLine();
		bf.write(currentHourFormat.format(currentDate));
		bf.newLine();
		bf.write("Each abbreviation represents the command executed below: ");
		bf.newLine();
		for (Character str : Trace) { //Outputting Trace's ArrayList's contents. 
			bf.write(str + ":");
		}
		
		bf.flush(); //Flushes out data for new start when program is restarted
		bf.close(); 
		
		JOptionPane.showMessageDialog(null, "Something has been written to file");
		} catch (IOException e) {
			System.err.print("ERROR");
			}
	}

public static void Forwards(Finch denisFinch, int speed, int duration)
{ 
	if (speed > 0 & speed <= 200 && duration > 0 && duration <= 6000)
	{
		denisFinch.setWheelVelocities(speed, speed, duration);
		Trace.add('F'); //Adding abbreviation to Trace ArrayList to be later used within retrace program
		SS.add(speed);  //Adding the input of speed input to SS ArrayList to be later used within retrace program
		DRT.add(duration); //Adding the input of duration input to DRT ArrayList to be later used within retrace program
	}
	else {
		JOptionPane.showMessageDialog(null, "Integer was outside given available range"); //Error message
		}

}

public static void Left(Finch denisFinch, int speed, int duration)
{	
	if (speed > 0 & speed <= 200 && duration > 0 && duration <= 6000)
{
		Trace.add('L');
		DRT.add(duration);
		SS.add(speed);
		denisFinch.setWheelVelocities(0, 80, 2000); //go left
		denisFinch.setWheelVelocities(speed, speed, duration);
		}
	else {
	 JOptionPane.showMessageDialog(null, "Integer was outside given available range");
 }
}
public static void Right(Finch denisFinch, int speed, int duration) {
	if (speed > 0 & speed <=200 && duration > 0 && duration <= 6000)
	{
		Trace.add('R');
		DRT.add(duration);
		SS.add(speed);
		denisFinch.setWheelVelocities(80, 0, 2000); //go right
		denisFinch.setWheelVelocities(speed, speed, duration);
		}
	else {
		JOptionPane.showMessageDialog(null, "Integer was outside given available range");
	 }
 }
public static void Backwards(Finch denisFinch, int speed, int duration) {
	if (speed > 0 & speed <= 200 && duration > 0 && duration <= 6000)
	{
		denisFinch.setWheelVelocities(speed*-1, speed*-1, duration);
		Trace.add('B'); 
		DRT.add(duration);
		SS.add(speed*-1);
		//speed*-1 makes a positive integer to negative
 }
	else {
		JOptionPane.showMessageDialog(null, "Integer was outside given available range");
		}
}
public static void Retrace(Finch denisFinch, int amount) {
 if(amount > Trace.size()) { //Checking to see if Trace size is bigger than what user has input in amount
  JOptionPane.showMessageDialog(null, "Input was bigger or smaller than commands executed", "ERROR", JOptionPane.PLAIN_MESSAGE);
 }else
  for(int i = 0; i < amount; i++) { 
   switch(Trace.get(i)) { //Performs whatever character commands abbreviations have been inserted into Trace and we're getting this in a linear way
    case 'F':
     denisFinch.setWheelVelocities(SS.get(i), SS.get(i), DRT.get(i)); //Getting data like speed and duration from SS and DRT ArrayList's to be used within setting correct velocities.
     break;
    case 'L':
     denisFinch.setWheelVelocities(0, 80, 2000);
     denisFinch.setWheelVelocities(SS.get(i), SS.get(i),DRT.get(i));
     break;
    case 'R':
     denisFinch.setWheelVelocities(80, 0, 2000);
     denisFinch.setWheelVelocities(SS.get(i), SS.get(i), DRT.get(i));
     break;
    case 'B':
     denisFinch.setWheelVelocities(SS.get(i),SS.get(i),DRT.get(i));
     break;
   }
  }
 }

}