
public class Main {

    public static int fib(int num) {

        if (num <= 1) {
            return num;
        } else {
            return fib(num - 1) + fib(num - 2);
        }
    }

    public static int fib2(int num) {
        if (num <= 1) {
            return num;
        } else {
            int a = 0;
            int b = 1;
            int c = 0;
            for (int i = 0; i < num - 1; i++) {
                c = a + b;
                a = b;
                b = c;
            }
            return c;
        }

    }

    public static void main(String[] args) {
        // System.out.println("Hello word");
        // for (int i = 0; i < 40; i++) {
        // System.out.println(fib(i));
        // }
        int n = 44;
        TimeTool.check("递归", new TimeTool.Task() {
            @Override
            public void execute() {
                System.out.println(fib(n));
            }
        });

        TimeTool.check("循环", new TimeTool.Task() {
            @Override
            public void execute() {
                System.out.println(fib2(n));

            }
        });
    }
}