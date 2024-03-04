import java.util.Arrays;

public class Bonus {
    static class WheelGraph {
        int nodes;
        int[] cycleCount = new int[101];
        int[][] adjacencyMatrix = new int[101][101];
        /**
         * Builds a wheel graph with n nodes, 0 being the central one.
         * @param n the number of nodes.
         */
        public WheelGraph(int n) {
            nodes = n;
            for(int i=0;i<n;i++)
            {
                adjacencyMatrix[0][i] = adjacencyMatrix[i][0] = 1;
                for(int j=1;j<n;j++)
                    adjacencyMatrix[i][j] = 0;
            }
            adjacencyMatrix[0][0] = 0;
            for(int i=1;i<n;i++)
            {
                int nextNode = i+1;
                if(i == n-1) nextNode = 1;
                adjacencyMatrix[i][nextNode] = adjacencyMatrix[nextNode][i] = 1;
            }
        }

        /**
         * prints the adjacency matrix of a wheel graph Wn
         */
        public void printAdjacencyMatrix() {
            for (int i = 0; i < nodes; i++) {
                for (int j = 0; j < nodes; j++)
                    System.out.print(adjacencyMatrix[i][j] + " ");
                System.out.println(" ");
            }
        }

        public void dfs(int currentNode, boolean[] visited, int startingNode, int[] cycle, int cycleLength) {
            visited[currentNode] = true;
            cycle[cycleLength++] = currentNode;
            for(int i=0;i<nodes;i++) {
                if(adjacencyMatrix[currentNode][i] == 1) {
                    // i can make this path even longer
                    if(!visited[i]) {
                        dfs(i, visited, startingNode, cycle, cycleLength);
                        visited[i] = false;
                    }
                    // i can go to the starting node from here (meaning I can register this as a cycle)
                    else if(i == startingNode && cycleLength >=2 && cycle[cycleLength-2]!=startingNode){
                        // i have this many cycles of a certain length (not really, but we can calculate it)
                        cycleCount[cycleLength] ++;
                    }
                }
            }
            cycleLength--;
        }

        public void countCycles() {
            for(int i=0;i<nodes;i++)
            {
                boolean[] visited = new boolean[200];
                for(int j=0;j<nodes;j++)
                    visited[j] = false;
                int []cycle = new int[100];
                dfs(i, visited, i, cycle, 0);
            }
        }

        public void checkCycles() {
            System.out.println("Predicted number of cycles: " + (nodes * nodes - 3 * nodes + 3));
            countCycles();
            int poz = 3;
            System.out.print("Actual number: ");
            int sum = 0;
            // let's add all the cycles that we added
            while(cycleCount[poz]>0) {
                int x = cycleCount[poz];
                // we must remember though, that for every cycle of length k, we added it k times (its rotations as well)
                x /= (poz);
                x /= 2;
                sum += x;
                poz++;
            }
            System.out.println(sum);
        }
    }

    public static void runProblem() {
        WheelGraph g = new WheelGraph(15);
        g.printAdjacencyMatrix();
        g.checkCycles();
    }
}
