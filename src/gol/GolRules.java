package gol;

/**
 * A classe GolRules implementa as regras do Jogo da Vida (Game of Life) e serve como um
 * gerador para calcular a próxima geração do jogo. Ela mantém o estado atual do grid e
 * aplica as regras do Jogo da Vida para calcular a próxima geração.
 *
 * <p>
 * Esta classe inclui métodos para converter a matriz em uma
 * representação de String plana, calcular a próxima geração do Jogo da Vida e contar o
 * número de vizinhos vivos de uma célula específica.
 * </p>
 *
 * @author Claudio Cassimiro
 * @version 1.0
 * @since 2024-02-24
 */
public class GolRules implements GolGenerator {
    /**
     * O grid que representa o estado atual do Jogo da Vida.
     */
    private int[][] grid;

    /**
     * Construtor da classe GolRules.
     *
     * @param grid O grid inicial que representa o estado atual do Jogo da Vida.
     */
    public GolRules(int[][] grid) {
        this.grid = grid;
    }

    /**
     * Converte uma matriz para uma String plana representando o grid.
     *
     * @param matrix A matriz a ser convertida.
     * @return Uma representação String do grid.
     */
    // Converts a matrix to a flat String
    // [0,0,0][1,1,1][0,1,0] => "...\nXXX\n.X."
    private String toGridString(int[][] matrix) {
        StringBuilder grid = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                grid.append(matrix[i][j] == 1 ? "X" : ".");
            }
            grid.append("\n");
        }
        return grid.toString();
    }

    /**
     * Calcula a próxima geração do Jogo da Vida e retorna como uma String plana.
     *
     * @param generation O número da geração atual.
     * @return A representação String da próxima geração.
     */
    @Override
    public String getNextGenerationAsString(long generation) {
        // Verifica se não é a geração inicial
        if (generation != 0) {
            // STEP 7. Calcula a próxima geração
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    int livingNeighbors = this.livingNeighborsCounter(grid, i, j);
                    if (grid[i][j] == 1 && livingNeighbors < 2) { // Aplica a regra de Morte por Solidão
                        grid[i][j] = 0;
                    } else if (grid[i][j] == 1 && livingNeighbors > 3) { // Aplica a regra de Morte por Superpopulação
                        grid[i][j] = 0;
                    } else if (grid[i][j] == 1 && (livingNeighbors == 2 || livingNeighbors == 3)) { // Aplica a regra de Sobrevivência
                        grid[i][j] = 1;
                    } else if (grid[i][j] == 0 && livingNeighbors == 3) { // Aplica a regra Nascimento
                        grid[i][j] = 1;
                    }
                }
            }
        }
        // STEP 8. Retorna a próxima geração (como uma String)
        return toGridString(grid);
    }

    /**
     * Conta o número de vizinhos vivos de uma célula específica no grid.
     *
     * @param grid O grid atual.
     * @param i    A posição da linha da célula.
     * @param j    A posição da coluna da célula.
     * @return O número de vizinhos vivos.
     */
    private int livingNeighborsCounter(int[][] grid, int i, int j) {
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
