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

    public static int isKReductible(int k, int n) {
        while (n!=k && n>0) {
            int sum = 0;
            while(n>0) {
                sum = sum + (n%10)*(n%10);
                n/=10;
            }
            n = sum;
        }
        if(n==k)
            return 1;
        else return 0;
    }
    public static void homework(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int k = Integer.parseInt(args[2]);
        System.out.println(a + b + k);
    }

    public static void main(String[] args) {
        compulsory();
    }
}