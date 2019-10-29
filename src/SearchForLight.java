/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;




/**
 *
 * @author Alex
 */
//private variables

public class SearchForLight {

    //creating class variables 
    private static int countTimes = -1;
    private static int lightDetectedCount = 0;
    private static long secondTimer = 0;
    private static long executionTimer = 0;
    private static final ArrayList <Integer> lightSensorValues = new ArrayList <Integer>();
    private static final ArrayList <Integer> lightSensorValuesBeginning = new ArrayList <Integer>();
      
    
    //Functions
    
    //calculate the time in mills or seconds
    private static long calculateTime(long start, long finish, boolean option)
    {
        long result = 0;

        //time difference in mill
        if(option == true)
        {
            result = finish - start;
        } 
        //time difference in seconds
        else if(option == false)
        {
            result = (finish - start) / 1000;
        }
        return result;
    }
    
    //call this function when you want the finch to follow light
    private static void followLight(Finch myFinch, int left, int right)
    {
        int result;
        
        if(left > right)
        {
           result = (left - right);
           //has to be opposite
           myFinch.setWheelVelocities(0,result, 1000);
           myFinch.setWheelVelocities(100,100,1000);
           myFinch.setLED(left,0,0);
        }
        else if(left < right)
        {
           result = (right - left);
           //has to be opposite
           myFinch.setWheelVelocities(result,0,1000);
           myFinch.setWheelVelocities(100,100,1000);
           myFinch.setLED(right,0,0);
        }
        else 
        {
           
           myFinch.setWheelVelocities(255,255,1000);
        }

        
    }
    //light source has not been detected by finch
    private static void lightNotBeenDetected(Finch myFinch)
    {
         myFinch.setLED(Color.green);
         myFinch.saySomething(FinchCommands.finchCommand2(),2000);
         System.out.println(FinchCommands.finchCommand2());
         myFinch.setWheelVelocities(50,50,500);
    }
    //light source has not been detected in five seconds
    private static void lightNotBeenDetectedIn5(Finch myFinch)
    {
         myFinch.setLED(Color.green);
         myFinch.saySomething(FinchCommands.finchCommand3(),2000);
         System.out.println(FinchCommands.finchCommand3());
         myFinch.sleep(1000);     
         myFinch.setWheelVelocities(0, 255, 1000);
    }
    //light source has been detected
    private static void lightSourceHasBeenDetected(Finch myFinch)
    {
        secondTimer = System.currentTimeMillis();
        lightDetectedCount++;
        
        int left = myFinch.getLeftLightSensor();
        int right = myFinch.getRightLightSensor();
                
        //get light sensor values an add to array
        lightSensorValues.add(left);
        lightSensorValues.add(right);
                
        
        myFinch.saySomething(FinchCommands.finchCommand1(),2000);
        System.out.println(FinchCommands.finchCommand1());
        
        followLight(myFinch,left,right);
    }
    //Terminate the program
    private static void programTerminate(Finch myFinch)
    {
        myFinch.buzz(262,1000);
        myFinch.saySomething(FinchCommands.finchCommand4(),1500);
        System.out.println(FinchCommands.finchCommand4());
        myFinch.quit();
        System.exit(0);
    }
    //When count is greater than one
    private static void countGreaterThan1(Finch myFinch)
    {
        executionTimer = System.currentTimeMillis();
        int left = myFinch.getLeftLightSensor();
        int right = myFinch.getRightLightSensor(); 
        lightSensorValuesBeginning.add(left);
        lightSensorValuesBeginning.add(right);   
    }
   //user input function 1 (overloading)  
   private static int userInput(Finch myFinch, String command, String value1, String value2)
   {
        int result;
        Scanner input = new Scanner(System.in);
        System.out.println(command);
        String userInput = input.nextLine();
        
        //the input was correct
        if(userInput.equals(value1) || userInput.equals(value2))
        {
            result = 1;
        }
        //Option 1: the input was incorrect - it must be correct
        else
        {
            myFinch.saySomething("incorrect input", 1000);
            System.out.println("The input you entered was not recgonised. Use must type " + value1 + " or " + value2);
            result = 2; 
        }
        return result;
   }
   //user input function 2 (overloading) 
   private static int userInput(Finch myFinch, String command, String value1, String value2, String value3, String value4)
   {    
        int result = 0;
        Scanner input = new Scanner(System.in);
        System.out.println(command);
        String userInput = input.nextLine();
        
        if(userInput.equals(value1) || userInput.equals(value2))
        {
            result = 1;
        }
        else if(userInput.equals(value3) || userInput.equals(value4))
        {
            result = 2;  
        }
        else
        {
            myFinch.saySomething("incorrect input", 1000);
            System.out.println("The input you entered was not recognised. Use must type '" + value1 + "' or '" + value2 + "' for yes or '" + value3 + "' or '" + value4 + "' for no.");
        }
        
        return result;
   }
   //Returns the number based on input by user
   private static void lastOutput(Finch myFinch)
   {
        int input1 = userInput(myFinch,"Would you like to see a log of the execution, if so enter: 'Y' if not enter: 'N'","Y","y","N","n");
        if(input1 == 1)
        {
            logOfEexcution();
            programTerminate(myFinch);
        }
        else if(input1 == 2)
        {
            programTerminate(myFinch);  
        }
        else
        {
            lastOutput(myFinch);
            //incorrect value was entered
        } 
   }
   //Gets elements within lightSensorValuesBeginning
   private static String elementsInLSVB()
   {
     
       String returnString;
  
       if(lightSensorValuesBeginning.isEmpty() == false)
       {
            int result1 = lightSensorValuesBeginning.get(0);
            int result2 = lightSensorValuesBeginning.get(1);

            returnString = "Light sensor values at begining: Left" + " " + result1 + " " + "Right" + " " + result2;
       }
       else
       {
           returnString = "Light sensor values at begining: Left 0 Right: 0";
       }
       return returnString;
   }
   //Gets elements within lightSensorValues
   private static String elementsInLSV()
   {  

    String highLow;
    
    if(lightSensorValues.isEmpty() == false)
    {
       
       int max = lightSensorValues.get(0);
       int low = lightSensorValues.get(0);
        
        for(int i=0;i < lightSensorValues.size();i++)
       {
           if(lightSensorValues.get(i) > max)
           {
               max = lightSensorValues.get(i);
           }
       }
       
       for(int i=0;i < lightSensorValues.size();i++)
       {
           if(lightSensorValues.get(i) < low)
           {
               low = lightSensorValues.get(i);
           }
       }
      highLow = ("The highest light sensor value was:" + " " + max + " " + "The lowest light sensor value was:" + " " + low + " ");
    }
    else
    {
      highLow = ("The highest light sensor value was: 0 The lowest light sensor value was: 0");
    }  
       return highLow;
   }
   //Returns log of execution
   private static void logOfEexcution()
   {
       System.out.println(elementsInLSVB());
       System.out.println(elementsInLSV());
       System.out.println(calculateTime(executionTimer,System.currentTimeMillis(),false) + " " + "seconds");
       System.out.println("A light source was detected" + " " + lightDetectedCount + " " + "times");
   }
   
     
    
  public static void main(final String[] args)
  {
  //create finch object
  Finch myFinch = new Finch();
  
  int i = 0;
  while(i == 0) 
  { 
    //program will begin if user input is correct
    if(userInput(myFinch,"Place finch on level surface and press 'S' to begin","S","s") == 1)
    {
      secondTimer = System.currentTimeMillis();
      //while finch is level and not on tail 
      while(myFinch.isFinchLevel() == true && myFinch.isBeakUp() == false)
      {
        countTimes++;
        
        if(countTimes >= 1)
        {
            //LightSource has not been detected
            if(myFinch.getLeftLightSensor() <= 10 && myFinch.getRightLightSensor() <= 10)
            {
                //no light source detected in five seconds
                if((calculateTime(secondTimer, System.currentTimeMillis(),true) > 5000)) //(lightDetectedCount >= 1)
                {   
                    lightNotBeenDetectedIn5(myFinch);
                    secondTimer = System.currentTimeMillis();
                }
                //no light source has been detected
                else
                {
                    lightNotBeenDetected(myFinch);
                }
            }
            // A light source has been detected
            else
            {
                lightSourceHasBeenDetected(myFinch); 
            }
        }
        //Initialise the finch 
        else
        {
            countGreaterThan1(myFinch);
        }
        
      }
      //End program
      myFinch.buzz(262, 1000);
      lastOutput(myFinch);
    }
      
   }
  }  
}


   



    

