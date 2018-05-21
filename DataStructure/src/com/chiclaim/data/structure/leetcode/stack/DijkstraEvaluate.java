package com.chiclaim.data.structure.leetcode.stack;

import java.util.Stack;

/**
 * Dijkstra双栈表达式求职
 */
public class DijkstraEvaluate {

    private static double evaluate(String express) {
        Stack<String> ops = new Stack<>();
        Stack<Double> values = new Stack<>();
        String[] cs = express.split(" ");
        for (String s : cs) {
            switch (s) {
                case " ":
                case "(":
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                    ops.push(s);
                    break;
                case ")":
                    String op = ops.pop();
                    double v = values.pop();
                    switch (op) {
                        case "+":
                            v = values.pop() + v;
                            break;
                        case "-":
                            v = values.pop() - v;
                            break;
                        case "*":
                            v = values.pop() * v;
                            break;
                        case "/":
                            v = values.pop() / v;
                            break;
                    }
                    values.push(v);
                    break;
                default:
                    values.push(Double.parseDouble(s));

            }
        }
        return values.pop();

    }


    public static void main(String[] args) {
        double value = evaluate("( 1 + ( ( 2 + 3 ) * ( 4 * 10 ) ) )");
        System.out.println(value);//201.0
    }
}
