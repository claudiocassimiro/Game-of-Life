package gol;

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

        validation.renderResultOfValidation(validations);

        gridRender.handleGridRender(args, validation);
    }
}