package week1;

/**
 * Variables - Used to store Data
 * Primitive data types - (int / double / char / boolean)
 * Assignment / Assignment Operator
 */
public class Example4 {
    public static void main(String[] args) {
       int markOne, markTwo, markThree; // Declared 3 integer variables

       markOne = 75;
       markTwo = 80;
       markThree = 87;

       int average;

       average = (markOne + markTwo + markThree) / 3;
       
       System.out.println(average);
       System.out.println("The average is: " + average);

    }
}
