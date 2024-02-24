package gol;

public class GolRules implements GolGenerator {
    private int[][] grid;


    public GolRules(int[][] grid) {
        this.grid = grid;
    }

    private void invertValues(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = matrix[i][j] == 1 ? 0 : 1;
            }
        }
    }

    // Converts a matrix to a flat String
    // [0,0,0][1,1,1][0,1,0] => "...\nXXX\n.X."
    private String toGridString(int[][] matrix) {
        String grid = new String("");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                grid += (matrix[i][j] == 1 ? "X" : ".");
            }
            grid += "\n";
        }
        return grid;
    }

    @Override
    public String getNextGenerationAsString(long generation) {
        if (generation != 0) {
            // STEP 7. We calculate the next generation
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    int livingNeighbors = this.livingNeighborsCounter(grid, i, j);
                    if (grid[i][j] == 1 && livingNeighbors < 2) { // Aplica a primeira regra
                        grid[i][j] = 0;
                    } else if (grid[i][j] == 1 && livingNeighbors > 3) { // Aplica a segunda regra
                        grid[i][j] = 0;
                    } else if (grid[i][j] == 1 && (livingNeighbors == 2 || livingNeighbors == 3)) { // Aplica a terceira regra
                        grid[i][j] = 1;
                    } else if (grid[i][j] == 0 && livingNeighbors == 3) { // Aplica a quarta regra
                        grid[i][j] = 1;
                    }
                }
            }
        }
        // STEP 8. We return the next generation (as a String)
        return toGridString(grid);
    }

    private int livingNeighborsCounter (int[][] grid, int i, int j) {
        int rows = grid.length;
        int cols = grid[0].length;
        int livingNeighbors = 0;

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                int neighborsI = i + x;
                int neighborsJ = j + y;

                // Verificar se o vizinho está dentro dos limites da matriz e não é a própria célula
                if (neighborsI >= 0 && neighborsI < rows && neighborsJ >= 0 && neighborsJ < cols && !(x == 0 && y == 0)) {
                    livingNeighbors += grid[neighborsI][neighborsJ];
                }
            }
        }

        return livingNeighbors;
    }
}
