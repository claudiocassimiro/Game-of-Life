package gol;

import java.util.ArrayList;

/**
 * A classe Main é a classe principal que inicia a execução do Jogo da Vida.
 * Ela realiza a validação dos argumentos, configura instâncias necessárias
 * para o Jogo da Vida e inicia o processo de renderização do grid.
 *
 * @author Claudio Cassimiro
 * @version 1.0
 * @since 2024-02-24
 */
public class Main {
    /**
     * O método principal que inicia a execução do programa.
     *
     * @param args Argumentos fornecidos ao programa.
     */
    public static void main(String[] args) {
        // Verifica se foram fornecidos argumentos
        if (args.length == 0) {
            System.out.println("Adicione parâmetros ao array args!");
            return;
        }

        // Cria uma instância de GolParamsValidation para validar e processar os argumentos
        GolParamsValidation validation = new GolParamsValidation(args);

        // Realiza a validação dos argumentos
        validation.validateArgs();

        // Realiza a validação específica dos inputs e obtém os resultados
        boolean[] validations = validation.validateInputs();

        // Renderiza os resultados da validação
        ArrayList<String> validationMessages = validation.renderResultOfValidation(validations);

        // Verifica mensagens de validação e encerra o programa se houver algum parâmetro inválido
        for (String message : validationMessages) {
            if (message.contains("inválido")) {
                System.out.println("Foi encontrado um parâmetro inválido, conserte-o e rode o programa novamente");
                System.exit(1);
            }
        }

        // Cria uma instância de GridRender para renderizar o Jogo da Vida
        GridRender gridRender = new GridRender(args, validation);

        // Inicia o processo de renderização do grid do Jogo da Vida
        gridRender.handleGridRender(args, validation);
    }
}
