
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EndPage extends JFrame {

	private JPanel contentPane;

	public EndPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProgramComplete = new JLabel("Program Complete!");
		lblProgramComplete.setFont(new Font("Consolas", Font.BOLD, 50));
		lblProgramComplete.setBounds(190, 57, 483, 60);
		contentPane.add(lblProgramComplete);
		
		JLabel lblAllTelemetryFor = new JLabel("All telemetry for the finch have been printed to an external text file called OUTPUT.txt");
		lblAllTelemetryFor.setFont(new Font("Consolas", Font.BOLD, 17));
		lblAllTelemetryFor.setBounds(12, 184, 858, 125);
		contentPane.add(lblAllTelemetryFor);
		
		JLabel lblWouldYouLike = new JLabel("Would you like to Try Again?");
		lblWouldYouLike.setFont(new Font("Consolas", Font.BOLD, 25));
		lblWouldYouLike.setBounds(226, 322, 392, 119);
		contentPane.add(lblWouldYouLike);
		
		JButton btnNewButton = new JButton("Try Again");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				StartPage start = new StartPage();
				start.setVisible(true);
				closeWindow();
			}
		});
		btnNewButton.setBounds(177, 454, 222, 60);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				closeWindow();
				System.exit(0);
			}
		});
		btnNewButton_1.setBounds(480, 454, 222, 60);
		contentPane.add(btnNewButton_1);

	}
	public void closeWindow() {
		this.setVisible(false);
	}
}
