public class Homework {
    private static boolean isKReducible(int n, int k) {
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
    public static void runProblem(String[] args) {
        long startTime = System.nanoTime();
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
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Running time: " + duration / 1000 + " milliseconds");
    }
}
