package app;

import java.util.HashMap;

// import java.util.Stack;
// import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        test2();
    }

    public static void test1() {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 20; i++) {
            stack.push(i);
        }
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack);
        System.out.println(stack.top());
        System.out.println(stack);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
        System.out.println(stack);
    }

    public static void test2() {
        String str1 = "{{[()]}}";
        System.out.println(str1 + ":" + (isValid2(str1) ? "true" : "false"));
        String str2 = "{{[()]}}}";
        System.out.println(str2 + ":" + (isValid2(str2) ? "true" : "false"));
        String str3 = "{{]}}";
        System.out.println(str3 + ":" + (isValid2(str3) ? "true" : "false"));
    }

    // 判断有效括号
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '{' || s.charAt(i) == '[' || s.charAt(i) == '(') {
                stack.push(s.charAt(i));
            } else if (s.charAt(i) == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                if (stack.pop() == '{') {

                } else {
                    return false;
                }
            } else if (s.charAt(i) == ']') {
                if (stack.isEmpty()) {
                    return false;
                }
                if (stack.pop() == '[') {

                } else {
                    return false;
                }
            } else if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                if (stack.pop() == '(') {

                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static boolean isValid2(String s) {

        HashMap<Character, Character> map = new HashMap<>();
        map.put('{', '}');
        map.put('[', ']');
        map.put('(', ')');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (map.containsKey(c)) {
                stack.push(c);
            } else if (map.containsValue(c)) {
                if (stack.isEmpty()) {
                    return false;
                }
                if (c == map.get(stack.pop())) {

                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            String str = tokens[i];
            if (str.equals("+")) {
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                stack.push((num2 + num1) + "");

            } else if (str.equals("-")) {
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                stack.push((num2 - num1) + "");
            } else if (str.equals("*")) {
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                stack.push((num2 * num1) + "");
            } else if (str.equals("/")) {
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                stack.push((num2 / num1) + "");
            } else {
                stack.push(str);
            }

        }

        return Integer.parseInt(stack.pop());
    }
    // 简易计算器？？？？？
    // public int calculate(String s) {
    // Stack<Character> stack = new Stack<>();
    // Integer result = 0;
    // StringBuilder str2 = new StringBuilder();
    // for (int i = 0; i < s.length(); i++) {
    // Character c = s.charAt(i);
    // StringBuilder str = new StringBuilder();
    // if (c == '(') {
    // stack.push(c);
    // } else if (c==')') {
    // stack.pop();
    // if (stack.isEmpty()) {
    // result = calculate(str.toString());
    // }
    // }else{
    // if (!stack.isEmpty()) {
    // str.append(c);
    // }else{
    // str2.append(c);
    // }
    // }
    // }
    // str2.append(result);
    // }
}