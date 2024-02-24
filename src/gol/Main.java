package gol;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Adicione paramâtros ao array args!");

            return;
        }

        GolParamsValidation validation = new GolParamsValidation(args);
        GridRender gridRender = new GridRender(args, validation);

        validation.validateArgs();

        boolean[] validations = validation.validateInputs();

        ArrayList<String> validationMessages = validation.renderResultOfValidation(validations);

        for (String message : validationMessages) {
            if (message.contains("inválido")) {
                System.out.println("Foi encontrado um parâmetro inválido, conserte-o e rode o programa novamente");
                System.exit(1);
            }
        }

        gridRender.handleGridRender(args, validation);
    }
}