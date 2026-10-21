/* Description: Checks whether (), [] and {} delimiters in a user-entered
   sequence are properly matched and nested, using a stack. Displays a
   step-by-step trace of every push, peek and pop.

   Programmed by: <Christian S. Bantasan> <BSIT> <48079> <Data Structures and Algotrithms>

   Last Modified: October 20, 2026

   Version: 1.0

   Acknowledgements: <list your sources / AI tool and the prompt you used>
*/

import java.util.Scanner;
import java.util.Stack;

public class DelimiterChecker {


    static boolean isOpening(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    static boolean isClosing(char c) {
        return c == ')' || c == ']' || c == '}';
    }

    static boolean matches(char open, char close) {
        return (open == '(' && close == ')')
                || (open == '[' && close == ']')
                || (open == '{' && close == '}');
    }

    static String show(Stack<Character> stack) {
        if (stack.isEmpty()) return "(empty)";
        StringBuilder sb = new StringBuilder();
        for (char c : stack) sb.append(c).append(' ');
        return sb.toString().trim();
    }

    static boolean check(String input) {
        Stack<Character> stack = new Stack<>();
        System.out.println("Input: " + (input.isEmpty() ? "(empty)" : input));

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (isOpening(c)) {
                stack.push(c);
                System.out.println("Read: " + c);
                System.out.println("Operation: PUSH " + c);
                System.out.println("Stack: " + show(stack));

            } else if (isClosing(c)) {
                System.out.println("Read: " + c);

                if (stack.isEmpty()) {
                    System.out.println("Operation: isEmpty -> true");
                    System.out.println("Stack: " + show(stack));
                    System.out.println("Result: Invalid - closing delimiter "
                            + c + " has no matching opening delimiter.");
                    return false;
                }

                char top = stack.peek();
                System.out.println("Operation: PEEK -> " + top);

                if (matches(top, c)) {
                    stack.pop();
                    System.out.println("Operation: POP " + top);
                    System.out.println("Stack: " + show(stack));
                } else {
                    System.out.println("Stack: " + show(stack));
                    System.out.println("Result: Invalid - mismatched delimiters: "
                            + c + " does not match the top of the stack " + top + ".");
                    return false;
                }
            }
        }

        if (stack.isEmpty()) {
            System.out.println("Result: Valid - all delimiters matched and stack is empty.");
            return true;
        }
        System.out.println("Result: Invalid - unmatched opening delimiter remains: "
                + show(stack));
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a sequence (press Enter for empty): ");
        String input = sc.hasNextLine() ? sc.nextLine().trim() : "";
        boolean ok = check(input);
        System.out.println(ok ? "\nFinal answer: Valid" : "\nFinal answer: Invalid");
        sc.close();
    }
}