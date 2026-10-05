class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char op = '+';

        for (int i = 0; i <= s.length(); i++) {
            char c = (i == s.length()) ? '+' : s.charAt(i);

            if (c == ' ') continue;

            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else {
                if (op == '+') stack.push(num);
                else if (op == '-') stack.push(-num);
                else if (op == '*') stack.push(stack.pop() * num);
                else if (op == '/') stack.push(stack.pop() / num);

                op = c;
                num = 0;
            }
        }

        int ans = 0;
        while (!stack.isEmpty())
            ans += stack.pop();

        return ans;
    }
}