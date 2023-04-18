import java.util.*;

public class Calculator {
    private static final Map<String, Integer> PRIORITY = new HashMap<>();

    static {
        PRIORITY.put("+", 1);
        PRIORITY.put("-", 1);
        PRIORITY.put("*", 2);
        PRIORITY.put("/", 2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an expression: ");
        String expression = scanner.nextLine();
        System.out.println("Result: " + calculate(expression));
    }

    public static double calculate(String expression) {
        Stack<Double> values = new Stack<>();
        Stack<String> operators = new Stack<>();

        StringTokenizer tokenizer = new StringTokenizer(expression, "+-*/ ", true);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            if (token.isEmpty()) continue;

            if (PRIORITY.containsKey(token)) {
                while (!operators.isEmpty() && PRIORITY.get(token) <= PRIORITY.get(operators.peek())) {
                    processOperation(values, operators);
                }
                operators.push(token);
            } else {
                values.push(Double.parseDouble(token));
            }
        }

        while (!operators.isEmpty()) {
            processOperation(values, operators);
        }

        return values.pop();
    }

    private static void processOperation(Stack<Double> values, Stack<String> operators) {
        double right = values.pop();
        double left = values.pop();
        String operator = operators.pop();

        switch (operator) {
            case "+":
                values.push(left + right);
                break;
            case "-":
                values.push(left - right);
                break;
            case "*":
                values.push(left * right);
                break;
            case "/":
                values.push(left / right);
                break;
        }
    }
}
