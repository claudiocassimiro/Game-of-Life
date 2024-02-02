import java.util.Arrays;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Adicione paramâtros ao array args!");

            return;
        }

        validateArgs(args);

        boolean[] validations = validateInputs(args);

        renderResultOfValidation(args, validations);
    }

    private static void validateArgs (String[] args) {
        for (String arg : args) {
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

    public static String removePrefixedText(String variable) {
        String aux = variable.substring(0, 2);
        return variable.replace(aux, "");
    }

    public static boolean validateGridWidth(String width) {
        String[] validWidths = {"10", "20", "40", "80"};

        String formatedWidthValue = width.replace("w=", "");

        return Arrays.asList(validWidths).contains(formatedWidthValue);
    }

    public static boolean validateGridHeight(String height) {
        String[] validHeights = {"10", "20", "40"};

        String formatedHeightValue = height.replace("h=", "");

        return Arrays.asList(validHeights).contains(formatedHeightValue);
    }

    public static boolean validateGenarationValue(String generation) {
        try {
            String formatedGenerationValue = generation.replace("g=", "");

            int generationInt = Integer.parseInt(formatedGenerationValue);

            return generationInt >= 0;
        } catch (NumberFormatException e) {
            System.out.println("O número de geração deve ser um inteiro maior que zero");
            return false;
        }
    }

    public static boolean validatePopulation(String population) {
        String formatedPopulationValue = population.replace("p=", "");

        if (formatedPopulationValue.equals("rnd")) return true;

        return Pattern.matches("^[01]+(#[01]{0,3}){2}[01]+$", formatedPopulationValue) || Pattern.matches("^#+$", formatedPopulationValue);
    }

    public static boolean validateSpeed(String speed) {
        String formatedSpeed = speed.replace("s=", "");

        int speedInt = Integer.parseInt(formatedSpeed);

        return speedInt >= 250 && speedInt <= 1000;
    }

    public static void renderResultOfValidation (String[] args, boolean[] validations) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].length() > 2) {
                switch (args[i].substring(0, 2)) {
                    case "w=":
                        System.out.println(validations[i] ? "width = " + removePrefixedText(args[i]) : "width = [inválido]");
                        break;
                    case "h=":
                        System.out.println(validations[i] ? "height = " + removePrefixedText(args[i]) : "height = [inválido]");
                        break;
                    case "g=":
                        System.out.println(validations[i] ? "generation = " + removePrefixedText(args[i]) : "generation = [inválido]");
                        break;
                    case "s=":
                        System.out.println(validations[i] ? "speed = " + removePrefixedText(args[i]) : "speed = [inválido]");
                        break;
                    case "p=":
                        System.out.println(validations[i] ? "population = " + removePrefixedText(args[i]) : "population = [inválido]");
                }
            }
        }
    }

    public static boolean[] validateInputs (String[] args) {
        boolean widthIsValid = validateGridWidth(args[0]);
        boolean heightIsValid = validateGridHeight(args[1]);
        boolean generationIsValid = validateGenarationValue(args[2]);
        boolean speedIsValid = validateSpeed(args[3]);
        boolean populationIsValid = validatePopulation(args[4]);

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
}