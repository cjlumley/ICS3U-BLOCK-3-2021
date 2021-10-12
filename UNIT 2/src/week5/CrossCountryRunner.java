package week5;

import java.util.Scanner;

public class CrossCountryRunner {
   public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      /**
       * Runs process runner for all three runners
       */
      processRunner(in);
      processRunner(in);
      processRunner(in);
      in.close();
   }

   /**
    * 
    * @param in
    */
   private static void processRunner(Scanner in) {
      /**
       * Creates Strings for the Names, time and calculations
       */
      
      String firstName, lastName; 
      String mileOne, mileTwo, finishTime;
      String splitTwo, splitThree;

      /**
       * Creates a promt for the user to create the value for the strings
       */
      firstName = promptUser("Enter Runner's First Name: ", in);
      lastName = promptUser("Enter Runner's Last Name: ", in);
      mileOne = promptUser("Enter Mile One Time (XX:XX.XX): ", in);
      mileTwo = promptUser("Enter Mile Two Time (XX:XX.XX): ", in);
      finishTime = promptUser("Enter Finish Time (XX:XX.XX): ", in);

      /**
       * Subtracts the times from the variables
       */
      splitTwo = subtractTimes(mileTwo, mileOne);
      splitThree = subtractTimes(finishTime, mileTwo);

      System.out.println(firstName + " " + lastName + "'s Split Times:");
      System.out.println("Split One: " + mileOne);
      System.out.println("Split Two: " + splitTwo);
      System.out.println("Split Three: " + splitThree);
   }

   private static String promptUser(String prompt, Scanner in) {
      /**
       * Promps the user for input
       */
      System.out.print(prompt);     
      return in.nextLine();
   }

   private static String subtractTimes(String endTime, String startTime) {
      /**
       * Subracts the times by calling on the stringEndtime to convert it to seconds and then back in convertToTime
       */
      double endInSeconds = convertToSeconds(endTime);
      double startInSeconds = convertToSeconds(startTime);
      double diffInSeconds = endInSeconds - startInSeconds;
      return convertToTime(diffInSeconds);
   }

   private static String convertToTime(double diffInSeconds) {
      /**
       * reverts doubles back to time in a XX:XX.XX Format
       */
      return String.format("%d:%06.3f", getMinutes(diffInSeconds), getSeconds(diffInSeconds));
   }

   private static Object getSeconds(double secondsTotal) {
      /**
       * Returns the seconds that remain from minutes
       */
      double seconds = secondsTotal % 60;
      return seconds;
   }

   private static Object getMinutes(double secondsTotal) {
      /** 
       * returns the minutes without the remaing seconds
       */
      int minutes = (int)(secondsTotal/60);
      return minutes;
   }

   private static double convertToSeconds(String time) {
      /**
       * Returns the value of the string after the colon 
       */
      int colonBreak = time.indexOf(":");
      double seconds = Double.valueOf(time.substring(colonBreak+1));
      double minutes = Double.valueOf(time.substring(0, colonBreak)); 
      return (minutes * 60) + seconds;
   }
}