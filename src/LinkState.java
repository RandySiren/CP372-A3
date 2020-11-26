import java.util.Scanner;

/**
 * Mandeep Sran - 150368680 November 25th, 2020
 * 
 * CP372 - Computer Networks
 * 
 * Assignment 3 - Link State Routing Algorithm for AS
 */
public class LinkState {

    static int gatewayRouterCount;
    static int[] forwardingTableGatewayRouters;
    static int[][] adjacencyMatrix;

    public static void main(String[] args) {
        // readInput(); // UNCOMMENT THIS AFTER
        ASSIGN_TEMP_VALUES(); // DELETE THIS CALL AFTER
        // printMatrix(); // DELETE THIS CALL AFTER
        generateForwardingTables();
    }

    static int[] computeDijkstrasDistArray(int adjacencyMatrix[][], int gatewayRouter) {
        // Holds the shortest distance from src to dist[i]
        int dist[] = new int[gatewayRouterCount];

        // Boolean array to see if 'i'th router is included in our shortest path tree or
        // in the shortest distance that's already finalized
        boolean routerProcessed[] = new boolean[gatewayRouterCount];

        // Initialize all distances as INFINITE and shortest path tree as false
        for (int i = 0; i < gatewayRouterCount; i++) {
            if (i == gatewayRouter)
                dist[i] = 0;
            else
                dist[i] = Integer.MAX_VALUE;
            routerProcessed[i] = false;
        }

        // Find shortest path for all vertices
        for (int count = 0; count < gatewayRouterCount - 1; count++) {
            // Find minimum distance gateway router from the ones not processed yet
            int u = minDistance(dist, routerProcessed);

            // Mark the picked gateway router as processed
            routerProcessed[u] = true;

            // Update dist value of the adjacent gateway routers
            for (int v = 0; v < gatewayRouterCount; v++)
                if (!routerProcessed[v] && adjacencyMatrix[u][v] != -1 && dist[u] != Integer.MAX_VALUE
                        && dist[u] + adjacencyMatrix[u][v] < dist[v])
                    dist[v] = dist[u] + adjacencyMatrix[u][v];
        }
        return dist;
    }

    static int minDistance(int dist[], boolean sptSet[]) {
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < gatewayRouterCount; v++) {
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }
        return min_index;
    }

    /**
     * Helper function to generate forwarding table for each individual router
     */
    static void generateFowardingTable(int gatewayRouter) {
        // Dijkstra's implementation
        int[] minDist = computeDijkstrasDistArray(adjacencyMatrix, gatewayRouter);
        System.out.println("To\tCost\tNext Hop");
        for (int i = 0; i < minDist.length; i++) {
            if (i == gatewayRouter)
                continue;
            System.out.println(i + "\t" + minDist[i] + "\t" + "-1");
        }
    }

    /**
     * Generate forwarding tables for each table from the array of routers
     */
    static void generateForwardingTables() {
        for (int gatewayRouter : forwardingTableGatewayRouters) {
            System.out.println("Forwarding Table for " + (gatewayRouter + 1));
            generateFowardingTable(gatewayRouter);
            System.out.println();
        }
    }

    /**
     * Print the matrix with some formatting
     */
    static void printMatrix() {
        for (int[] i : adjacencyMatrix) {
            for (int j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    /**
     * DELETE THIS METHOD AFTER ONLY FOR TESTING PURPOSES
     */
    static void ASSIGN_TEMP_VALUES() {
        gatewayRouterCount = 6;
        adjacencyMatrix = new int[][] { { 0, 1, 10, -1, -1, 2 }, { 10, 0, 1, -1, -1, -1 }, { 1, 10, 0, -1, -1, -1 },
                { -1, -1, 2, 0, 1, 10 }, { -1, -1, -1, 10, 0, 1 }, { -1, -1, -1, 1, 10, 0 } };
        forwardingTableGatewayRouters = new int[] { 1, 4 };
    }

    /**
     * Reading input from System using Scanner
     */
    static void readInput() {
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

        // Read gateway routers we generate forwarding tables for
        String line = scanner.nextLine();
        String[] lineArray = line.split(" ");
        forwardingTableGatewayRouters = new int[lineArray.length];
        for (int i = 0; i < lineArray.length; i++) {
            forwardingTableGatewayRouters[i] = Integer.parseInt(lineArray[i]) - 1;
        }

        scanner.close();
    }

}