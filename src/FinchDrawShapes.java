/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.io.*;
import javax.swing.JOptionPane;
import java.math.RoundingMode;
/**
 *
 * @author space_000
 */


public class FinchDrawShapes extends LogData {
    private static Finch myf = new Finch();
    private boolean sizesWork;
    private double[] triangleAngles = new double[3]; 
    FinchDrawShapes(){
        
    }
    public static void main(String[] args) {
        new drawShapeWindow().setVisible(true);
    }
    //Function will draw a triangle sides between 20cm and 80cm
    public void drawTriangle(int x, int y, int z, TriangleWindow win) throws IOException{
        boolean drawing = false;
        this.sizesWork = checkTriangle(x, y, z);
        if(this.sizesWork){
            anglesOfTriangle(x,y,z);
            LogData(x,y,z,triangleAngles);
            win.setVisible(false);
            drawing = true;
            //0.25cm/s with speed of 50 on each wheel.
            moveFinch(x,y,z, drawing);
            new drawShapeWindow().setVisible(true);
        }else{
        	JOptionPane.showMessageDialog(win, "Cannot Create Triangle With These Values");
            win.setTitle("Cannot Create Triangle");
        }
    }
    //Function will draw a Rectangle sides between 20cm and 80cm
    public void drawRectangle(int x, int y, RectangleWindow win) throws IOException{
        boolean drawing = true;
        //Using the class LogData class
        LogData(x,y);
        win.setVisible(false);
        moveFinch(x,y,drawing);
        
        new drawShapeWindow().setVisible(true);
    }
    public void moveFinch(int x, int y, int z, boolean drawing){
        double elapsedTimeinSeconds = 0;
        int[] lengthArray = new int[3];
        //This is so it makes it easier when I loop in a for loop.
        lengthArray[0] = x;
        lengthArray[1] = y;
        lengthArray[2] = z;
       
        while(drawing){  
            for(int i = 0; i < 3; i++){
                long start = System.nanoTime();
                while(elapsedTimeinSeconds < 0.25 * lengthArray[i]){
                      	myf.setWheelVelocities(50, 50);
                       long end  = System.nanoTime();
                       long elapsedTime = end - start;
                       elapsedTimeinSeconds = (double) elapsedTime / 1_000_000_000;
                }
                myf.stopWheels();
                elapsedTimeinSeconds = 0;
                start = System.nanoTime();
                while(elapsedTimeinSeconds < triangleAngles[i] / 57.23  ){
                    myf.setWheelVelocities(150,0);  
                    long end  = System.nanoTime();
                    long elapsedTime = end - start;
                    elapsedTimeinSeconds = (double) elapsedTime / 1_000_000_000; 
                }
                myf.stopWheels();
                
            }
            drawing = false;
        }
        myf.buzz(440, 2000);

    }
    //Here is another overloaded function which is used for drawing a rectangle
    public void moveFinch(int x, int y,boolean drawing){
        //Elapsed time is used to get the amount time that has passed for each side has been drawn and to turn on a corner/angel
        double elapsedTimeinSeconds = 0;
        //This array has 3 elements which will store the sizes of the array.
        int[] lengthArray = new int[2];
        lengthArray[0] = x;
        lengthArray[1] = y;
        //This will be set to false when the finch has completed.
        while(drawing){
            for(int z = 0; z < 2; z++){
                for(int i = 0; i < 2; i++){
                long start = System.nanoTime();
                myf.stopWheels();
                elapsedTimeinSeconds = 0;
                //from the calculations which I got when measuring how long it takes the finch to move 1 cm at 50 speed.
                //I got 0.25cm/s and this will times to length of the current side the finch is drawing.
                    while(elapsedTimeinSeconds < 0.25 * lengthArray[i]){
                        myf.setWheelVelocities(50, 50);
                        long end = System.nanoTime();
                        long elapsedTime = end - start;
                        elapsedTimeinSeconds = (double) elapsedTime / 1_000_000_000;
                    }
                    myf.stopWheels();
                    start = System.nanoTime();
                    //Resets the elasped time for turning a curner.
                    elapsedTimeinSeconds = 0;
                    //this is for the turning the finch which will happen 4 times.
                    while(elapsedTimeinSeconds < 90 / 57.23  ){
                        myf.setWheelVelocities(150,0);  
                        long end = System.nanoTime();
                        long elapsedTime = end - start;
                        //This is to convert the elapsed time to seconds
                        elapsedTimeinSeconds = (double) elapsedTime / 1_000_000_000; 
                    }
                    myf.stopWheels();
                }
            }
            //This is so the while loop can break.
            drawing = false;
        }
       myf.buzz(440, 2000);
    }
    public boolean checkTriangle(int x, int y, int z){
        boolean sizesWork = true;
        if(x + y <= z){
            sizesWork = false;
        }
        if(x + z <= y){
            sizesWork = false;
        }
        if(y + z <= x){
            sizesWork = false;
        }
        return sizesWork;
    }
    public boolean finchIsLevel(){
       if(myf.isFinchLevel()){
            return true;
        }else{
            return false;
        }
    }
    public void anglesOfTriangle(int x, int y, int z){
        double tempValue;
        //Perimeter
        //Working out angle x.
        tempValue = (Math.pow(y, 2) + Math.pow(z, 2) - Math.pow(x, 2)) / (2 * y * z);
        tempValue = Math.toDegrees(Math.acos(tempValue));
        this.triangleAngles[0] = Math.round(tempValue);
        //Working out angle y
        tempValue = (Math.pow(x, 2) + Math.pow(z, 2) - Math.pow(y, 2)) / (2 * x * z);
        tempValue = Math.toDegrees(Math.acos(tempValue));
        this.triangleAngles[1] = Math.round(tempValue);
        //Working out angle z.
        tempValue = (Math.pow(y,2) + Math.pow(x, 2) - Math.pow(z, 2)) / (2 * x * y);
        tempValue = Math.toDegrees(Math.acos(tempValue));
        this.triangleAngles[2] = Math.round(tempValue);
        //All Angles will be outputted to a 2 d.p
    }
    //For Rectangle

    public void programQuit(){
        myf.quit();
    }
    
    public double[] getTriangleAngles(){
        return this.triangleAngles;
    } 
    
    public void setSizesWork(boolean newsizesWork){
        this.sizesWork = newsizesWork;
    }
    public boolean getSizesWork(){
        return this.sizesWork;
    }
}
