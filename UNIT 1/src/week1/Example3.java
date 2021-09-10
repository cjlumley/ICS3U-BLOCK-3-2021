package week1;

public class Example3 {
    public static void main(String[] args) {
        /* Compile Error because double quote 
        encapsulate a String Litteral and we added extra quotes*/

        System.out.println("This is \"very\" important"); // Escape the quote by creating an escape sequence using \"
        //System.out.println("This is \very important"); // Ap cares about \" \n \\
        System.out.println("This is \\very important");
        System.out.println("This\nis\nvery\nimportant");
    }
    
}
