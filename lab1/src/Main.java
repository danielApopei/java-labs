import java.lang.Math;
public class Main {

    public static int control(int n) {
        while(n > 10) {
            int sum = 0;
            while(n>0) {
                sum += n%10;
                n/=10;
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
        n += 0b10101;
        int hexaVal = 0xFF;
        n+=hexaVal;
        n*=6;
        int result = control(n);
        System.out.println("Willy-nilly, this semester I will learn " + languages[result]);
    }

    public static void main(String[] args) {
        compulsory();
    }
}