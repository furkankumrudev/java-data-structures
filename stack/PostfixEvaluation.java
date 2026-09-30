import java.util.Stack;

public class PostfixEvaluation {
    // Method to evaluate value of a postfix expression
    static int evaluatePostfix(String exp) {
        // Create a stack
        Stack<Integer> stack = new Stack<>();

        // Scan all characters one by one
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            // If the scanned character is an operand (number), push it to the stack
            if (Character.isDigit(c)) {
                stack.push(c - '0'); // Convert char to int
            }
            // If the scanned character is an operator, pop two elements and apply the operator
            else {
                int val1 = stack.pop();
                int val2 = stack.pop();

                switch (c) {
                    case '+':
                        stack.push(val2 + val1);
                        break;
                    case '-':
                        stack.push(val2 - val1);
                        break;
                    case '*':
                        stack.push(val2 * val1);
                        break;
                    case '/':
                        stack.push(val2 / val1);
                        break;
                }
            }
        }
        return stack.pop();
    }

    // Driver program to test the above function
    public static void main(String[] args) {
        String exp = "231*+9-"; // Example expression
        System.out.println("Postfix evaluation: " + evaluatePostfix(exp));
    }
}
