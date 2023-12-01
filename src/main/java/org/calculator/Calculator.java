package org.calculator;

import java.util.*;

public class Calculator implements AbstractCalculator {
    private TreeNode formula;
    private ArrayList<Character> vars;
    private ArrayList<Double> values;

    public Calculator() {
        vars = new ArrayList<>();
        values = new ArrayList<>();
    }

    private int priority(String str) {
        return switch (str) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            case "^" -> 3;
            case "sin", "cos", "tg", "ctg" -> 4;
            default -> -1;
        };
    }

    private boolean isDigit(char ch) {
        String digits = "0123456789.";
        return digits.indexOf(ch) != -1;
    }

    private boolean isLetter(String str) {
        String letters = "qwertyuiopasdfghjklzxcvbnm";
        return letters.contains(str);
    }

    private boolean isOperator(String string) {
        return string.equals("+") || string.equals("-") || string.equals("*") || string.equals("/") || string.equals("^");
    }

    private boolean isFunction(String string) {
        return string.equals("sin") || string.equals("cos") || string.equals("tg") || string.equals("ctg");
    }

    private String infixToPostfix(String expression) {
        String result = "", operator = "";

        Deque<String> stack = new ArrayDeque<>();

        for (int i = 0; i < expression.length(); ++i) {
            char c = expression.charAt(i);

            if (isDigit(c) || isLetter(String.valueOf(c)) && (i == expression.length()-1 || !isLetter(expression.substring(i+1, i+2)))) {
                if (isLetter(String.valueOf(c)) && !vars.contains(c))
                    vars.add(c);
                result += c;
            }
            else {
                if (!result.endsWith(" "))
                    result += " ";
                if (c == '(')
                    stack.push(String.valueOf(c));
                else if (c == ')') {
                    while (!stack.isEmpty() && !stack.peek().equals("(")) {
                        result += stack.peek() + " ";
                        stack.pop();
                    }
                    stack.pop();
                    if (!stack.isEmpty() && isFunction(stack.peek())) {
                        result += stack.peek() + " ";
                        stack.pop();
                    }
                } else {
                    operator = String.valueOf(c);
                    if (isLetter(operator))
                        while (i < expression.length() && isLetter(expression.substring(i+1, i+2))) {
                            operator += expression.substring(i+1, i+2);
                            i++;
                        }
                    while (!stack.isEmpty() && priority(operator) <= priority(stack.peek())) {
                        result += stack.peek() + " ";
                        stack.pop();
                    }
                    stack.push(operator);
                }
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek().equals("("))
                return "";
            result += " " + stack.peek();
            stack.pop();
        }

        result = result.stripLeading();
        result = result.stripTrailing();

        return result;
    }

    @Override
    public void input(String expression) {
        String postfix = infixToPostfix(expression);

        Scanner scanner = new Scanner(System.in);
        for (Character var : vars) {
            System.out.print("\n" + var + " = ");
            values.add(scanner.nextDouble());
        }

        if (postfix.equals(""))
            System.out.println("Выражение содержит ошибку");
        else {
            Stack<TreeNode> st = new Stack<>();
            TreeNode temp;

            int oldIndex = -1, newIndex = 0;
            String term;

            while (newIndex != -1) {
                newIndex = postfix.indexOf(' ', oldIndex + 1);
                term = postfix.substring(oldIndex + 1, newIndex == -1 ? postfix.length() : newIndex);

                if (isOperator(term)) {
                    temp = new TreeNode(term);
                    temp.createLeftChild(st.pop());
                    temp.createRightChild(st.pop());
                    st.push(temp);
                } else if (isFunction(term)) {
                    temp = new TreeNode(term);
                    temp.createLeftChild(st.pop());
                    st.push(temp);
                } else
                    st.push(new TreeNode(term));

                oldIndex = newIndex;
            }
            formula = st.pop();
        }
    }

    private boolean isVar(String var) {
        return var.length() == 1 && vars.contains(var.charAt(0));
    }

    private double solveNode(TreeNode node) {
        if (node == null)
            return 0;
        double term1, term2 = 0;
        String element = node.getData();
        if (!isOperator(element) && !isFunction(element))
            if (isVar(element))
                return values.get(vars.indexOf(element.charAt(0)));
            else
                return Double.parseDouble(element);
        term1 = solveNode(node.getLeftChild());
        if (!isFunction(element))
            term2 = solveNode(node.getRightChild());
        switch (element) {
            case "+" -> {
                return term2 + term1;
            }
            case "-" -> {
                return term2 - term1;
            }
            case "*" -> {
                return term2 * term1;
            }
            case "/" -> {
                return term2 / term1;
            }
            case "sin" -> {
                return Math.sin(term1);
            }
            case "cos" -> {
                return Math.cos(term1);
            }
            case "tg" -> {
                return Math.tan(term1);
            }
            case "ctg" -> {
                return 1 / Math.tan(term1);
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public double solve() {
        return solveNode(formula);
    }
}