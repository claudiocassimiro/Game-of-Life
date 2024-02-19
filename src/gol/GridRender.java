package gol;

public class GridRender {
    private String[] args;
    private GolParamsValidation validation;

    public GridRender(String[] args, GolParamsValidation validation) {
        this.args = args;
        this.validation = validation;
    }

    public void handleGridRender (String[] args, GolParamsValidation validation) {
        int width = Integer.parseInt(validation.removePrefixedText(args[0]));
        int height = Integer.parseInt(validation.removePrefixedText(args[1]));
        int generation = Integer.parseInt(validation.removePrefixedText(args[2]));
        int speed = Integer.parseInt(validation.removePrefixedText(args[3]));
        String population = validation.removePrefixedText(args[4]);

        int[][] parsedPopulationGrid = validation.parsePopulationToGridFormat(population);

        GolSettings goLSettings = new GolSettings(height, width, speed, generation);

        final GolGenerator generator = new GolRules(parsedPopulationGrid);

        SwingRenderer.render(generator, goLSettings);
    }
}
