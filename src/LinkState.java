import java.util.Scanner;

public class LinkState {

    static int gatewayRouterCount;
    static int[][] adjacencyMatrix;

    public static void main(String[] args) {

    }

    /**
     * Reading input from System using Scanner
     */
    public static void readInput() {
        Scanner scanner = new Scanner(System.in);
        gatewayRouterCount = scanner.nextInt();

        adjacencyMatrix = new int[gatewayRouterCount][];

        // Read adjacency matrix of all routers
        for (int i = 0; i < gatewayRouterCount; i++) {
            String line = scanner.nextLine();
            String[] lineArray = line.split(" ");
            int[] adjacencyMatrixRow = new int[gatewayRouterCount];

            // Parse individual values and store in our matrix row
            for (int j = 0; j < lineArray.length;) {
                adjacencyMatrixRow[j] = Integer.parseInt(lineArray[j]);
            }
        }
        scanner.close();
    }

}