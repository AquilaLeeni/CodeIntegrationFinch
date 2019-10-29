import edu.cmu.ri.createlab.terk.robot.finch.Finch;

//import java.awt.EventQueue;
//import java.awt.Font;
import java.util.Random;

//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
import java.io.FileWriter;
//import java.io.IOException;
//import java.io.OutputStreamWriter;
import java.io.File;
//import java.io.FileOutputStream;
import java.io.BufferedWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ZigZag {
	Random rand = new Random();
	int randspeed = rand.nextInt(154)+ 101;
	private int amountOfZag;
	private int lengthOfZag;
	private Finch myF;
	private long time;
	private long result;
	private double strlinedist;
	private int totdist;
	ZigZag(int newZag, int newLength, Finch newmyF, long newTime){
		myF = newmyF;
		amountOfZag = newZag;
		lengthOfZag = newLength;
		time = newTime;
		System.out.println("The value for NEWTIME is " + time);
		DrawZigZag();
	} //1 unit of finch speed = 0.005cm/s
	
	
	public void DrawZigZag() {
		myF.saySomething("Initiating finch zig zag application");
		System.out.println("Drawing");
		//int value = rand.nextInt(50);
		//myF.setWheelVelocities(255, 255);
		System.out.println(amountOfZag + " this is amount of zag");
		System.out.println(lengthOfZag + " this is amount of length of zag");
		int time = (int) (lengthOfZag / (randspeed * 0.005)*44);
		//random speed 
		//myF.setWheelVelocities(randspeed,randspeed,time);
		System.out.println("The speed is " + randspeed);
		myF.setWheelVelocities(100,-100,400);
		int i;
		for(i = 1; i <= amountOfZag; i ++ )
		{
			if(i % 2 == 0)
			{
				myF.setLED(0,255,0);
				myF.buzz(150, (time*10));
				myF.setWheelVelocities(randspeed,randspeed,time);
				RightTurn();
			}
			else
			{
				myF.setLED(255,0,0);
				myF.buzz(262, (time*10));
				myF.setWheelVelocities(randspeed,randspeed,time);
				LeftTurn();
			}
		}
		twist();
		myF.saySomething("Zig Zag Pattern Completed");
		RetraceZigZag();
	}
	public void RetraceZigZag() {
		myF.saySomething("Initiating finch zig zag application");
		System.out.println("Drawing");
		//int value = rand.nextInt(50);
		//myF.setWheelVelocities(255, 255);
		System.out.println(amountOfZag + " this is amount of zag");
		System.out.println(lengthOfZag + " this is amount of length of zag");
		int time = (int) (lengthOfZag / (randspeed * 0.005)*44);
		//random speed 
		//myF.setWheelVelocities(randspeed,randspeed,time);
		System.out.println("The speed is " + randspeed);
		myF.setWheelVelocities(100,-100,400);
		int i;
		for(i = 1; i <= amountOfZag; i++ )
		{
			if(i % 2 == 0)
			{
				myF.setLED(0,255,0);
				//myF.buzz(200, (time*100));
				myF.setWheelVelocities(randspeed,randspeed,time);
				RightTurn();
			}
			else
			{
				myF.setLED(255,0,0);
				//myF.buzz(262, (1/*time*100*/));
				myF.setWheelVelocities(randspeed,randspeed,time);
				LeftTurn();
			}
		}
		//twist();
		myF.saySomething("Zig Zag Pattern Completed");
		calculateTime(System.currentTimeMillis());
		mathcalc(lengthOfZag, amountOfZag);
		WriteLog();
		EndPage end = new EndPage();
		end.setVisible(true);
	}
	public long calculateTime(long finishTime)
	{	
		this.result = (finishTime - time) / 1000; 
		System.out.println("The result of the result " + result);
		return result;
		
	}
	public void LeftTurn() {
		myF.setWheelVelocities(-100,100,900);
		myF.saySomething("Left Turn Initiated", 2000);
	}
	
	public void RightTurn() {
		myF.setWheelVelocities(100,-100,800);
		myF.saySomething("Right Turn Initiated", 2000);
	}

	public void twist() {
		myF.setWheelVelocities(-200,200,900);
	}
	public double mathcalc(int a, int b)
	{
		int c = 2*(a^2);
		double onesect = java.lang.Math.sqrt(c);
		int  nosec = b/2;
		this.strlinedist = onesect * nosec;
		this.totdist = a * b;
		System.out.println("the value of the straight line distance is " + strlinedist + " cm");
		return strlinedist;
	}
	public void WriteLog() 
	{
	File output = new File("OUTPUT.txt");

	FileWriter writer; //Setting FileWriter object named as writer
	try {
	 writer = new FileWriter(output); //Using the file named output
	 //@SuppressWarnings("resource")
	BufferedWriter bf = new BufferedWriter(writer);
	 
	 Date currentDate = new Date();
	 bf.write("--------------------------------------OUTPUT TXT VALUES----------------------------------- ");
	 bf.newLine();
	 bf.write("TASK 4 FINCH ZIG ZAG OUTPUT DATA: ");
	 bf.newLine();
	 bf.newLine();
	 SimpleDateFormat currentHourFormat = new SimpleDateFormat("hh:mm:ss");
	 bf.write("Below is the current time in HH:MM:SS format: ");
	 bf.newLine();
	 bf.write(currentHourFormat.format(currentDate));
	 bf.newLine();
	 bf.write(lengthOfZag + " this is amount of length of section");
	 bf.newLine();
	 bf.write(amountOfZag + " this is number section");
	 bf.newLine();
	 bf.write(result + " this is the time taken in process");
	 bf.newLine();
	 bf.write(strlinedist + " this is the straight line distance between the start and finish");
	 bf.newLine();
	 bf.write(totdist + " this is the total distance traversed");
	 bf.newLine();
	 bf.write("--------------------------------------OUTPUT TXT VALUES----------------------------------- ");
	 bf.flush();
	 bf.close();
	 }
	catch (Exception e) {
		e.printStackTrace();
	}
	
	}
}