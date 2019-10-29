import javax.swing.*;
import java.awt.GridLayout;
import java.awt.*;

public class GUI
{
	public static void welcomeGUI()
	{
		JFrame WelcomeFrame = new JFrame("Welcome!");
		WelcomeFrame.setLayout(new GridLayout());
		
		JLabel WelcomeText = new JLabel("",JLabel.CENTER);
		JLabel WelcomeText2 = new JLabel("",JLabel.CENTER);
		JLabel WelcomeText3 = new JLabel("",JLabel.CENTER);
		String[] choices = {"Start Program","Help!","Exit"};
		JComboBox <String> WelcomeCB = new JComboBox<String>(choices);
		JPanel panel = new JPanel(new GridLayout(0,1,0,5));
		WelcomeText.setFont(new Font(null, Font.PLAIN, 30));
		WelcomeText2.setFont(new Font(null, Font.PLAIN, 30));
		WelcomeText3.setFont(new Font(null, Font.PLAIN, 30));
		WelcomeCB.setFont(new Font(null, Font.PLAIN, 30));
		
		panel.add(WelcomeText);
		panel.add(WelcomeText2);
		panel.add(WelcomeText3);
		panel.add(WelcomeCB,JComboBox.CENTER_ALIGNMENT);		
		
		WelcomeFrame.add(panel);
		
		WelcomeText.setText("Welcome to Finch tilt test 7");
		WelcomeText2.setText("Select 'Start program' to begin!");
		WelcomeText3.setText("Select 'Help' to recieve instructions.");					
		
		WelcomeFrame.setSize(600, 500);
		WelcomeFrame.setVisible(true);
		WelcomeCB.setVisible(true);
		WelcomeFrame.setLocationRelativeTo(null);
		WelcomeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JButton okButton = new JButton("");
		okButton.setFont(new Font(null, Font.PLAIN, 30));
		okButton.setText("OK");
		panel.add(okButton);
		
		okButton.addActionListener(e -> {
		String option = (String) WelcomeCB.getSelectedItem();
			
		if (option == "Start Program")
		{
			WelcomeFrame.dispose();		
			Validation.Valid();
		}
		
		if (option == "Help!")
		{
			Help.HelpPane();
		}
		
		if (option == "Exit")
		{
			int result = JOptionPane.showConfirmDialog(
		            WelcomeFrame,
		            "Are you sure you want to exit?",
		            "Are you sure?",
		            JOptionPane.YES_NO_OPTION);
		 
		        if (result == JOptionPane.YES_OPTION)
		            System.exit(0);
		}
		
		});
	}
}
	