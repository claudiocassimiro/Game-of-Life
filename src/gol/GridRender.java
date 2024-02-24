package gol;

/**
 * A classe GridRender é responsável por renderizar o Jogo da Vida (Game of Life).
 * Ela recebe parâmetros, realiza validações e utiliza um gerador (generator) para
 * aplicar as regras do Jogo da Vida e renderizar o estado resultante.
 *
 * @author Claudio Cassimiro
 * @version 1.0
 * @since 2024-02-24
 */
public class GridRender {
    /**
     * Argumentos recebidos para configuração do Jogo da Vida.
     */
    private String[] args;

    /**
     * Instância de GolParamsValidation para realizar validações nos parâmetros.
     */
    private GolParamsValidation validation;

    /**
     * Construtor da classe GridRender.
     *
     * @param args       Argumentos recebidos para configuração do Jogo da Vida.
     * @param validation Instância de GolParamsValidation para realizar validações.
     */
    public GridRender(String[] args, GolParamsValidation validation) {
        this.args = args;
        this.validation = validation;
    }

    /**
     * Manipula o processo de renderização do grid do Jogo da Vida.
     *
     * @param args       Argumentos recebidos para configuração do Jogo da Vida.
     * @param validation Instância de GolParamsValidation para realizar validações.
     */
    public void handleGridRender(String[] args, GolParamsValidation validation) {
        // Extrai as informações necessárias dos argumentos
        int width = Integer.parseInt(validation.removePrefixedText(args[0]));
        int height = Integer.parseInt(validation.removePrefixedText(args[1]));
        int generation = Integer.parseInt(validation.removePrefixedText(args[2]));
        int speed = Integer.parseInt(validation.removePrefixedText(args[3]));
        String population = validation.removePrefixedText(args[4]);

        // Converte a população para um formato de matriz (grid)
        int[][] parsedPopulationGrid = validation.parsePopulationToGridFormat(population);

        // Configurações do Jogo da Vida
        GolSettings goLSettings = new GolSettings(height, width, speed, generation);

        // Cria um gerador com as regras do Jogo da Vida
        final GolGenerator generator = new GolRules(parsedPopulationGrid);

        // Renderiza o estado resultante usando SwingRenderer
        SwingRenderer.render(generator, goLSettings);
    }
}
