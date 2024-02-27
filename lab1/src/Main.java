import java.lang.Math;
public class Main {

    public static int control(int n) {
        while(n > 10) {
            int sum = 0;
            while (n > 0) {
                sum += n % 10;
                n /= 10;
            }
            n = sum;
        }
        return n;
    }

    public static void compulsory () {
        System.out.println("Hello World!");

        String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n = (int) (Math.random() * 1_000_000);
        n *= 3;
        n += Integer.parseInt("10101", 2);
        n += Integer.parseInt("FF", 16);
        n*=6;
        int result = control(n);
        System.out.println("Willy-nilly, this semester I will learn " + languages[result]);
    }

    public static boolean isKReducible(int n, int k) {
        int[] foundNumbers = new int[100];
        int length = 0;
        while (n!=k && n>1) {
            int sum = 0;
            while(n>0) {
                sum = sum + (n%10)*(n%10);
                n/=10;
            }
            n = sum;
            for (int i=0;i<length;i++) {
                if(foundNumbers[i] == n) {
                    return false;
                }
            }
            foundNumbers[length++] = n;
        }
        return n == k;
    }
    public static void homework(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int k = Integer.parseInt(args[2]);
        StringBuilder resultString = new StringBuilder();
        for(int i = a;i<=b;i++) {
            boolean result = isKReducible(i,k);
            if(result)
                resultString.append(i).append(" ");
        }
        System.out.println(resultString);
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        compulsory();
        homework(args);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Running time: " + duration / 1000000 + " milliseconds");
    }
}