package gol;

import java.util.*;

/**
 * A classe GolParamsValidation é responsável por validar e processar os parâmetros fornecidos
 * para o Jogo da Vida (Game of Life). Ela verifica a validade dos parâmetros, formata e extrai
 * informações relevantes, gerando mensagens de validação e retornando valores processados.
 *
 * <p>
 * Esta classe inclui métodos para validar largura, altura, geração, velocidade e população do
 * grid do Jogo da Vida. Também fornece métodos para renderizar mensagens de validação e converter
 * a representação da população em um formato de matriz.
 * </p>
 *
 * @author Claudio Cassimiro
 * @version 1.0
 * @since 2024-02-24
 */
public class GolParamsValidation {
    /**
     * Array de argumentos fornecidos para o Jogo da Vida.
     */
    private String[] args;

    /**
     * Construtor da classe GolParamsValidation.
     *
     * @param args Array de argumentos fornecidos para o Jogo da Vida.
     */
    public GolParamsValidation(String[] args) {
        this.args = args;
    }

    /**
     * Valida os argumentos e imprime mensagens para parâmetros ausentes.
     */
    public void validateArgs() {
        for (String arg : this.args) {
            if (arg.length() <= 2) {
                switch (arg.substring(0, 2)) {
                    case "w=":
                        System.out.println("width = [Não Presente]");
                        break;
                    case "h=":
                        System.out.println("height = [Não Presente]");
                        break;
                    case "g=":
                        System.out.println("generation = [Não Presente]");
                        break;
                    case "s=":
                        System.out.println("speed = [Não Presente]");
                        break;
                    case "p=":
                        System.out.println("population = [Não Presente]");
                }
            }
        }
    }

    /**
     * Remove o texto prefixado de uma variável.
     *
     * @param variable A variável da qual remover o texto prefixado.
     * @return A variável sem o texto prefixado.
     */
    public String removePrefixedText(String variable) {
        String aux = variable.substring(0, 2);
        return variable.replace(aux, "");
    }

    /**
     * Valida a largura do grid.
     *
     * @param width A largura fornecida.
     * @return true se a largura for válida, false caso contrário.
     */
    public boolean validateGridWidth(String width) {
        String[] validWidths = {"10", "20", "40", "80"};

        String formatedWidthValue = width.replace("w=", "");

        return Arrays.asList(validWidths).contains(formatedWidthValue);
    }

    /**
     * Valida a altura do grid.
     *
     * @param height A altura fornecida.
     * @return true se a altura for válida, false caso contrário.
     */
    public boolean validateGridHeight(String height) {
        String[] validHeights = {"10", "20", "40"};

        String formatedHeightValue = height.replace("h=", "");

        return Arrays.asList(validHeights).contains(formatedHeightValue);
    }

    /**
     * Valida o valor de geração.
     *
     * @param generation A geração fornecida.
     * @return true se a geração for válida, false caso contrário.
     */
    public boolean validateGenarationValue(String generation) {
        try {
            String formatedGenerationValue = generation.replace("g=", "");

            int generationInt = Integer.parseInt(formatedGenerationValue);

            return generationInt >= 0;
        } catch (NumberFormatException e) {
            System.out.println("O número de geração deve ser um inteiro maior que zero");
            return false;
        }
    }

    /**
     * Valida a população do grid.
     *
     * @param population A população fornecida.
     * @return true se a população for válida, false caso contrário.
     */
    public boolean validatePopulation(String population) {
        String formatedPopulationValue = population.replace("p=", "");

        if (formatedPopulationValue.equals("rnd")) return true;

        for (String value : formatedPopulationValue.split("")) {
            if (!Objects.equals(value, "#") && !Objects.equals(value, "0") && !Objects.equals(value, "1")) {
                return false;
            }
        }
        return true;
    }

    /**
     * Valida a velocidade.
     *
     * @param speed A velocidade fornecida.
     * @return true se a velocidade for válida, false caso contrário.
     */
    public boolean validateSpeed(String speed) {
        String formatedSpeed = speed.replace("s=", "");

        int speedInt = Integer.parseInt(formatedSpeed);

        return speedInt >= 250 && speedInt <= 1000;
    }

    /**
     * Renderiza mensagens de validação e retorna uma lista de mensagens.
     *
     * @param validations Um array de validações correspondentes aos argumentos.
     * @return Uma lista de mensagens de validação.
     */
    public ArrayList<String> renderResultOfValidation(boolean[] validations) {
        ArrayList<String> validationMessages = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            if (args[i].length() > 2) {
                switch (args[i].substring(0, 2)) {
                    case "w=":
                        String validationWidth = validations[i] ? "width = " + removePrefixedText(this.args[i]) : "width = [inválido]";
                        validationMessages.add(validationWidth);
                        System.out.println(validationWidth);
                        break;
                    case "h=":
                        String validationHeight = validations[i] ? "height = " + removePrefixedText(this.args[i]) : "height = [inválido]";
                        validationMessages.add(validationHeight);
                        System.out.println(validationHeight);
                        break;
                    case "g=":
                        String validationGeneration = validations[i] ? "generation = " + removePrefixedText(this.args[i]) : "generation = [inválido]";
                        validationMessages.add(validationGeneration);
                        System.out.println(validationGeneration);
                        break;
                    case "s=":
                        String validationSpeed = validations[i] ? "speed = " + removePrefixedText(this.args[i]) : "speed = [inválido]";
                        validationMessages.add(validationSpeed);
                        System.out.println(validationSpeed);
                        break;
                    case "p=":
                        String validationPopulation = validations[i] ? "population = " + removePrefixedText(this.args[i]) : "population = [inválido]";
                        validationMessages.add(validationPopulation);
                        System.out.println(validationPopulation);
                        break;
                    default:
                        System.out.println("Parâmetro não esperado");
                }
            }
        }

        return validationMessages;
    }

    /**
     * Valida todos os inputs e retorna um array de booleanos representando a validade de cada input.
     *
     * @return Um array de booleanos indicando a validade de cada input.
     */
    public boolean[] validateInputs() {
        boolean widthIsValid = validateGridWidth(this.args[0]);
        boolean heightIsValid = validateGridHeight(this.args[1]);
        boolean generationIsValid = validateGenarationValue(this.args[2]);
        boolean speedIsValid = validateSpeed(this.args[3]);
        boolean populationIsValid = validatePopulation(this.args[4]);

        boolean[] validations;
        validations = new boolean[]{
                widthIsValid,
                heightIsValid,
                generationIsValid,
                speedIsValid,
                populationIsValid
        };

        return validations;
    }

    /**
     * Converte a representação da população em um formato de matriz.
     *
     * @param population A população fornecida como String.
     * @return Uma matriz representando a população.
     */
    public int[][] parsePopulationToGridFormat(String population) {
        String[] populationArray = Objects.equals(population, "rnd") ? generateRandomPopulation().split("#") : population.split("#");

        int[][] parsedPopulationArray = new int[populationArray.length][populationArray.length];

        for (int i = 0; i < populationArray.length; i++) {
            String[] splitedPopulations = populationArray[i].split("");
            for (int j = 0; j < splitedPopulations.length; j++) {
                if (Objects.equals(splitedPopulations[j], "")) {
                    splitedPopulations[j] = "0";
                }

                parsedPopulationArray[i][j] = Integer.parseInt(splitedPopulations[j]);
            }
        }
        return parsedPopulationArray;
    }

    /**
     * Gera uma população aleatória como String.
     *
     * @return Uma sequência de população aleatória.
     */
    private String generateRandomPopulation() {
        Random random = new Random();
        StringBuilder sequencia = new StringBuilder();
        int len = 72;

        for (int i = 0; i < len; i++) {
            // Adiciona 0 ou 1 à sequência aleatoriamente
            sequencia.append(random.nextInt(2));

            // Adiciona '#' após cada terceiro dígito, exceto o último
            if ((i + 1) % 3 == 0 && i < len - 1) {
                sequencia.append("#");
            }
        }

        return sequencia.toString();
    }
}
