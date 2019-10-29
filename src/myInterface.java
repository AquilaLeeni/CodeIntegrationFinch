
import javax.swing.JOptionPane;

public class myInterface {

public static String myMenu()
{
	Object[] options = {"Forwards - F", "Backwards - B", "Right - R", "Left - L", "Retrace - T", "Write Log - W", "Quit - Q"};
	String input = (String)JOptionPane.showInputDialog(null, "Select your command below: ","NAVIGATION MENU \n", JOptionPane.PLAIN_MESSAGE, null, options, "Forwards - F");
	if (input == null || input.length() == 0) input = "Quit - Q";
	return(input);

}
public static int speedInput(int speed)
{
	try{
    speed = Integer.parseInt(JOptionPane.showInputDialog(null, 
   "Enter movement speed in seconds between 1 to 200: ",
   "Speed Input",
   JOptionPane.PLAIN_MESSAGE));
	}catch (Exception e){ //When user presses cancel, 0 is given and when 0 is given the program gets an error; therefore exception helps fix this error
		JOptionPane.showMessageDialog(null, "Either you did not input an integer or you pressed cancel"
				+ "\n0 was stored in speed value.");
	}
	return speed;

}

public static int movementDurationInput(int duration)
{
 try{
 duration = Integer.parseInt(JOptionPane.showInputDialog(null,
   "Enter duration of movement in Seconds between 1000 to 6000: \n"
   + "1000 = 1 second",
   "Duration Input",
   JOptionPane.PLAIN_MESSAGE));
 }catch(Exception e){
	 JOptionPane.showMessageDialog(null, "Either you did not input an integer or you pressed cancel"
				+ "\n0 was stored in duration value.");
 }
 return duration;
}

public static int retraceInput(int amount)
{
	try{
    amount = Integer.parseInt(JOptionPane.showInputDialog(null,
   "Enter number of retraces to retrace",
   "Retrace Input",
   JOptionPane.PLAIN_MESSAGE));
	}catch(Exception e){
		 JOptionPane.showMessageDialog(null, "Either you did not input an integer or you pressed cancel"
					+ "\n0 commands was given to finch to retrace.");
	}
 return amount;
}
}