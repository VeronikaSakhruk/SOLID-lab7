package task3_lsp;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        AreaCalculator calculator = new AreaCalculator();

        System.out.println("Resized rectangle area: " + calculator.resizeAndVerifyArea(new Rectangle(1, 1)));

        List<Shape> shapes = List.of(new Rectangle(5, 10), new Square(4));
        System.out.println("Total area: " + calculator.totalArea(shapes));

        try {
            new Square(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("Fail-fast: " + e.getMessage());
        }
    }
}
