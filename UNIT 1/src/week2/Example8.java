package week2;

public class Example8 {
    public static void main(String[] args) {
        final int NUM_MARKS = 5;

        int markOne = 82, markTwo = 89, markThree = 45, markFour = 98, markFive = 99;

        double average = (double) (markOne + markTwo + markThree + markFour + markFive) / NUM_MARKS;
        
        System.out.println("The average is: " + average);

        int x = (int) 3.7; // chops off the 7
        
    
    }
}
