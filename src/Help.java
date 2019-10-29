import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Help 
{

	public static void HelpPane()
	{
	JFrame HelpFrame = new JFrame("Help!"); //The main frame of the GUI is created with title name "Help!"
	HelpFrame.setLayout(new GridLayout());  //Grid layout property is added to the Frame
	
	JPanel panelHelp = new JPanel(new GridLayout(0,1,0,5)); //the panelHelp panel is given a grid layout with one column
	
	JLabel HelpText = new JLabel("In this program you will be to tilt the finch in 5 different orientations",JLabel.CENTER); //the JLabels have a text value added and been given a center format
	JLabel HelpText2 = new JLabel("Each tilt will have a corresponding movement.",JLabel.CENTER);
	JLabel HelpText3 = new JLabel("Forward tilt = Forward Motion",JLabel.CENTER);
	JLabel HelpText4 = new JLabel("Backwards tilt = Backwards Motion",JLabel.CENTER);
	JLabel HelpText5 = new JLabel("Left tilt = Left turn",JLabel.CENTER);
	JLabel HelpText6= new JLabel("Right tilt = Right turn",JLabel.CENTER);
	JLabel HelpText7 = new JLabel("Resting the finch level = No movement",JLabel.CENTER);
	JLabel HelpText8 = new JLabel("==============================================",JLabel.CENTER);
	JLabel HelpText9 = new JLabel("In order to use this program properly try to tilt the finch as perfectly as possible.",JLabel.CENTER);
	JLabel HelpText10 = new JLabel("If you don't tilt the finch perfectly an additional 0.5 seconds will be added to the timer to compensate for the missed recording.", JLabel.CENTER);
	
	panelHelp.add(HelpText);         //the JLabels are added to the panel
	panelHelp.add(HelpText2);
	panelHelp.add(HelpText3);
	panelHelp.add(HelpText4);
	panelHelp.add(HelpText5);
	panelHelp.add(HelpText6);
	panelHelp.add(HelpText7);
	panelHelp.add(HelpText8);
	panelHelp.add(HelpText9);
	panelHelp.add(HelpText10);
	HelpFrame.add(panelHelp);
	
	HelpFrame.setSize(800, 500);     //the Frame Size is set 
	HelpFrame.setVisible(true);		 //the frame is set to visible 
	
	HelpFrame.setLocationRelativeTo(null); // the frame is set to be in the centre of the screen
	HelpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //when the "X" button is selected the window is disposed
	

	}
}
