package lbycpa2.module3;

import lbycpa2.module3.stack.MyStack;

import java.util.*;

public class TruthTable {
    private static final List<Character> operators = Arrays.asList('∧', '∨', '↑', '↓', '→', '¬', '⇔', '⊻');

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input expression: ");
        String inputExp = scanner.nextLine();

        // TODO: convert postfix to infix
        // String convertedToInfix = inputExp;
        String convertedToInfix = convertToInfix(inputExp);

        List<Character> variables = listVariables(inputExp);
        for (char v : variables) {
            printCell(""+v);
        }
        printCell(convertedToInfix, convertedToInfix.length());
        System.out.println();

        evaluateExpression(inputExp, convertedToInfix, variables);
    }

    private static void evaluateExpression(String expression, String infix, List<Character> vars) {
        boolean[] initialVal = new boolean[vars.size()];
        evaluateExpression(expression, infix, vars, initialVal, 0);
    }

    public static String convertToInfix(String inputExp) {
        /* Converts pq¬∨pq∧→ to (p∨¬q) → (p ∧ q) */
        String s1; // holds temp string
        MyStack<String> operandStack = new MyStack<>(inputExp.length()/2+1);
        for (char c : inputExp.toCharArray()) {
            if (operators.contains(c)) {
                /* if c is an operator */
                if(c == '¬'){
                    /* handles negation expressions */
                    String c3 = operandStack.top();
                    operandStack.pop();
                    s1 = "";
                    if (c3.length() > 2 && c3.charAt(0) != '(') {
                        c3 = "("+c3+")";
                    }
                    s1 = s1.concat(String.valueOf(c));
                    s1 = s1.concat(c3);
                    operandStack.push(s1);
                } else {
                    /* handles normal operations */
                    String c1 = operandStack.top();
                    operandStack.pop();
                    String c2 = operandStack.top();
                    operandStack.pop();
                    s1 = "";
                    if (c1.length() > 2 && c1.charAt(0) != '(') {
                        c1 = "("+c1+")";
                    }
                    if (c2.length() > 2 && c2.charAt(0) != '(') {
                        c2 = "("+c2+")";
                    }
                    s1 = s1.concat(c2);
                    s1 = s1.concat(String.valueOf(c));
                    s1 = s1.concat(c1);
                    operandStack.push(s1);
                }
            } else {
                /* if operand, push to stack */
                operandStack.push(String.valueOf(c));
            }
        }
        return operandStack.top();
    }

    private static void evaluateExpression(String expression, String infix, List<Character> vars, boolean[] initialVal, int assignedFor) {
        if (assignedFor < vars.size()) {
            for (int i = 0; i < 2; i++) {
                initialVal[assignedFor] = i == 0;
                evaluateExpression(expression, infix, vars, initialVal, assignedFor+1);
            }
            return;
        }

        for (boolean b : initialVal) {
            printCell(booleanToTF(b));
        }

        MyStack<Boolean> stack = new MyStack<>(expression.length()/2+1);
        for (char c : expression.toCharArray()) {
            int varIdx = vars.indexOf(c);
            if (varIdx != -1) { // If a variable
                boolean varVal = initialVal[varIdx];
                stack.push(varVal);
            } else { // If an operator
                boolean tos = stack.top(), tos2;
                boolean newVal = tos;
                switch (c) {
                    case '¬' -> {
                        newVal = !tos;
                    }
                    case '∧' -> {
                        stack.pop();
                        tos2 = stack.top();
                        newVal = tos && tos2;
                    }
                    case '∨' -> {
                        stack.pop();
                        tos2 = stack.top();
                        newVal = tos || tos2;
                    }
                    case '→' -> {
                        stack.pop();
                        tos2 = stack.top();
                        newVal = !tos || tos2;
                    }
                    case '↔' -> {
                        stack.pop();
                        tos2 = stack.top();
                        newVal = tos == tos2;
                    }
                }
                stack.pop();
                stack.push(newVal);
            }
        }
        printCell(booleanToTF(stack.top()), infix.length());
        System.out.println();
    }

    private static String booleanToTF(boolean bool) {
        return bool ? "T" : "F";
    }

    private static List<Character> listVariables(String expression) {
        List<Character> vars = new ArrayList<>();
        for (char c : expression.toCharArray()) {
            if (!operators.contains(c) && !vars.contains(c)) {
                vars.add(c);
            }
        }
        return vars;
    }

    private static int getPrecedence(char operator) {
        return switch (operator) {
            case '¬' -> 1;
            case '∧' -> 2;
            case '∨' -> 3;
            case '→' -> 4;
            case '↔' -> 5;
            default -> 0;
        };
    }

    /**
     * Prints a cell in a table
     * @param string Cell contents
     */
    private static void printCell(String string) {
        printCell(string, 1);
    }

    /**
     * Prints a cell in a table with custom width
     * @param string Cell contents
     * @param width Cell width
     */
    private static void printCell(String string, int width) {
        // PRINT SPACES BETWEEN STRING
        StringBuilder stringBuilder = new StringBuilder();

        int spaceLen = Math.round((float) (width - string.length()) / 2);
        stringBuilder.append(" ".repeat(Math.max(0, spaceLen)));

        stringBuilder.append(string);

        spaceLen = (width - stringBuilder.length());
        stringBuilder.append(" ".repeat(Math.max(0, spaceLen)));

        stringBuilder.append(" | ");

        System.out.print(stringBuilder);
    }
}
