package gol;

import java.util.Arrays;
import java.util.regex.Pattern;

public class GolParamsValidation {
    private String[] args;

    public GolParamsValidation(String[] args) {
        this.args = args;
    }

    public void validateArgs () {
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

    public String removePrefixedText(String variable) {
        String aux = variable.substring(0, 2);
        return variable.replace(aux, "");
    }

    public boolean validateGridWidth(String width) {
        String[] validWidths = {"10", "20", "40", "80"};

        String formatedWidthValue = width.replace("w=", "");

        return Arrays.asList(validWidths).contains(formatedWidthValue);
    }

    public boolean validateGridHeight(String height) {
        String[] validHeights = {"10", "20", "40"};

        String formatedHeightValue = height.replace("h=", "");

        return Arrays.asList(validHeights).contains(formatedHeightValue);
    }

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

    public boolean validatePopulation(String population) {
        String formatedPopulationValue = population.replace("p=", "");

        if (formatedPopulationValue.equals("rnd")) return true;

        return Pattern.matches("^[01]+(#[01]{0,3}){2}[01]+$", formatedPopulationValue) || Pattern.matches("^#+$", formatedPopulationValue);
    }

    public boolean validateSpeed(String speed) {
        String formatedSpeed = speed.replace("s=", "");

        int speedInt = Integer.parseInt(formatedSpeed);

        return speedInt >= 250 && speedInt <= 1000;
    }

    public void renderResultOfValidation (boolean[] validations) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].length() > 2) {
                switch (args[i].substring(0, 2)) {
                    case "w=":
                        System.out.println(validations[i] ? "width = " + removePrefixedText(this.args[i]) : "width = [inválido]");
                        break;
                    case "h=":
                        System.out.println(validations[i] ? "height = " + removePrefixedText(this.args[i]) : "height = [inválido]");
                        break;
                    case "g=":
                        System.out.println(validations[i] ? "generation = " + removePrefixedText(this.args[i]) : "generation = [inválido]");
                        break;
                    case "s=":
                        System.out.println(validations[i] ? "speed = " + removePrefixedText(this.args[i]) : "speed = [inválido]");
                        break;
                    case "p=":
                        System.out.println(validations[i] ? "population = " + removePrefixedText(this.args[i]) : "population = [inválido]");
                }
            }
        }
    }

    public boolean[] validateInputs () {
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

    public int[][] parsePopulationToGridFormat (String population) {
        String[] populationArray = population.split("#");

        int[][] parsedPopulationArray = new int[populationArray.length][populationArray.length];

        for (int i = 0; i < populationArray.length; i++) {
            String[] splitedPopulations = populationArray[i].split("");
            for (int j = 0; j < splitedPopulations.length; j++) {
                parsedPopulationArray[i][j] = Integer.parseInt(splitedPopulations[j]);
            }
        }
        return parsedPopulationArray;
    }
}
