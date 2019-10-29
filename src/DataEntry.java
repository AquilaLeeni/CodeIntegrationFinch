
//import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

//import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class DataEntry extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JLabel lblTaskFinch;
	private JLabel lblNumberOfSections;
	private JLabel label;
	private JButton button;
	private JSpinner spinner;
	private JSpinner spinner1;
	private Finch myF;
	private JLabel lblText;
	public long executionTimer;
	public int spinval;
	public int spinval1;

	public DataEntry(Finch newmyF) {
		myF = newmyF;
		try {
			this.setVisible(true);
		}catch(Exception ex) {
			
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTaskFinch = new JLabel("Task 4: Finch Zig Zag");
		lblTaskFinch.setFont(new Font("Consolas", Font.BOLD, 50));
		lblTaskFinch.setBounds(155, 29, 600, 73);
		contentPane.add(lblTaskFinch);
		
		lblNumberOfSections = new JLabel("Number of Sections");
		lblNumberOfSections.setFont(new Font("Consolas", Font.BOLD, 20));
		lblNumberOfSections.setBounds(87, 150, 208, 30);
		contentPane.add(lblNumberOfSections);
		
		label = new JLabel("Length of sections (cm)");
		label.setFont(new Font("Consolas", Font.BOLD, 20));
		label.setBounds(488, 150, 280, 30);
		contentPane.add(label);
		
		JButton Noofsectionsinfo = new JButton("Information");
		Noofsectionsinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog (null, "Please enter a valid integer input between 2 and 10, this is representative of the number of sections travelled within the zig zag", "INFO", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		Noofsectionsinfo.setBounds(117, 288, 134, 50);
		contentPane.add(Noofsectionsinfo);
		
		JButton Lengofsectionsinfo = new JButton("Information");
		Lengofsectionsinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog (null, "Please enter a valid integer input between 20 and 80, that will be used as the length of a section being travelled within the zig zag in cm", "INFO", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		Lengofsectionsinfo.setBounds(557, 288, 134, 50);
		contentPane.add(Lengofsectionsinfo);
		
		
		
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(2, 2, 10, 2));
		spinner.setFont(new Font("Consolas", Font.BOLD, 30));
		spinner.setBounds(101, 200, 150, 65);
		contentPane.add(spinner);
		spinval = (Integer) spinner.getValue();
		
		JSpinner spinner1 = new JSpinner();
		spinner1.setFont(new Font("Consolas", Font.BOLD, 30));
		spinner1.setModel(new SpinnerNumberModel(20, 20, 80, 1));
		spinner1.setBounds(539, 200, 150, 65);
		contentPane.add(spinner1);
		spinval1 = (Integer) spinner1.getValue();
		
		button = new JButton("Submit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getActionCommand().equals("Submit")) 
				{
					executionTimer = System.currentTimeMillis();
					spinval = (Integer) spinner.getValue();
					spinval1 = (Integer) spinner1.getValue();
					System.out.println("Button1 has been clicked");
					System.out.println(spinval + " is the value of spinner 1");
					System.out.println(spinval1 + " is the value of spinner 2");
					ZigZag zig = new ZigZag(spinval, spinval1,myF, executionTimer);
					closewindow();
				}
			}
		});
		button.setBounds(348, 376, 134, 50);
		contentPane.add(button);
		}
	public long calculateTime(long finishTime)
	{
		long result = 0;
		
		result = (executionTimer - finishTime) / 1000; 
		
		return result;
		
	}
	public void setExectutionTimer(long start)
	{
		executionTimer = start;
	}
	public void closewindow()
	{
		this.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		}
	}
	
