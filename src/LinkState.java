import java.util.Scanner;

public class LinkState {

    static int gatewayRouterCount;
    static int startGatewayRouter;
    static int endGatewayRouter;
    static int[][] adjacencyMatrix;

    public static void main(String[] args) {
        readInput();
        printMatrix();
    }

    static void printMatrix() {
        for (int[] i : adjacencyMatrix) {
            for (int j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    /**
     * Reading input from System using Scanner
     */
    static void readInput() {
        try {
            Scanner scanner = new Scanner(System.in);

            // Read gateway router count
            gatewayRouterCount = Integer.parseInt(scanner.nextLine());

            // Read adjacency matrix of all gateway routers
            adjacencyMatrix = new int[gatewayRouterCount][];
            for (int i = 0; i < gatewayRouterCount; i++) {
                String line = scanner.nextLine();
                String[] lineArray = line.split(" ");
                int[] adjacencyMatrixRow = new int[gatewayRouterCount];

                // Parse individual values and store in our matrix row
                for (int j = 0; j < lineArray.length; j++) {
                    adjacencyMatrixRow[j] = Integer.parseInt(lineArray[j]);
                }
                adjacencyMatrix[i] = adjacencyMatrixRow;
            }

            // Read start and end gateway routers of path route
            String line = scanner.nextLine();
            startGatewayRouter = Integer.parseInt(line.split(" ")[0]);
            endGatewayRouter = Integer.parseInt(line.split(" ")[1]);

            scanner.close();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
            System.exit(0);
        }
    }

}