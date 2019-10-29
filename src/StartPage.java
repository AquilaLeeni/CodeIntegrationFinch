
//Start Page

//import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

//import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
//import java.awt.EventQueue;

//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class StartPage extends JFrame {

	private JPanel contentPane;
	Finch myF = new Finch();

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartPage frame = new StartPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StartPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Start Program and Check Level");
		btnNewButton.setFont(new Font("Consolas", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					checkIfLevel();
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(270, 445, 298, 130);
		contentPane.add(btnNewButton);
		
		JLabel lblTaskFinch = new JLabel("Task 4: Finch Zig Zag Application!");
		lblTaskFinch.setFont(new Font("Consolas", Font.BOLD, 40));
		lblTaskFinch.setBounds(12, 110, 858, 95);
		contentPane.add(lblTaskFinch);
		
		JLabel label = new JLabel("Assignment 2 - Premkumar Vyas");
		label.setFont(new Font("Consolas", Font.BOLD, 40));
		label.setBounds(12, 206, 858, 95);
		contentPane.add(label);
		
		JLabel lblGroup = new JLabel("1741433 - Group Red05");
		lblGroup.setFont(new Font("Consolas", Font.BOLD, 40));
		lblGroup.setBounds(12, 314, 733, 48);
		contentPane.add(lblGroup);
	}
	public void checkIfLevel() {
		boolean check = myF.isFinchLevel();
		DataEntry ent = new DataEntry(myF);
		if(check == false)
		{
			System.out.println("This finch is not level");
			myF.saySomething("This finch is not level");
			ent.setVisible(false);
		}
		else 
		{
			System.out.println("The finch is level");
			myF.saySomething("This finch is level");
			closeWindow();
			ent.setVisible(true);;
		}
		
	}
	public void closeWindow() {
		this.setVisible(false);
	}

}
