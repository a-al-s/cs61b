/** Class that prints the Collatz sequence starting from a given number.
 *  @author YOUR NAME HERE
 */
public class Collatz {

    /** This method prints Collatz sequence. The sequnce determine the next value based on the previous one
     *  if the previous value:
     *      even: divide it by 2
     *      odd: multiply it by 3 then add 1
     *  The sequence considered over when it reached 1 */
    public static int nextNumber(int n) {
        if (n % 2 == 0) {
            return n / 2;
        } else {
            return (n * 3) + 1;
        }
        
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.print(n + " ");
        while (n != 1) {
            n = nextNumber(n);
            System.out.print(n + " ");
        }
        System.out.println();
    }
}

