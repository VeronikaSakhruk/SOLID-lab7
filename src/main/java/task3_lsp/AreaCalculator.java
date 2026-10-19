package task3_lsp;

import java.util.List;

public class AreaCalculator {

    private static final double EXPECTED_WIDTH = 5;
    private static final double EXPECTED_HEIGHT = 10;

    public double resizeAndVerifyArea(Rectangle rectangle) {
        Rectangle resized = rectangle.withWidth(EXPECTED_WIDTH).withHeight(EXPECTED_HEIGHT);
        double expectedArea = EXPECTED_WIDTH * EXPECTED_HEIGHT;
        if (resized.area() != expectedArea) {
            throw new IllegalStateException("Expected area " + expectedArea + ", but got " + resized.area());
        }
        return resized.area();
    }

    public double totalArea(List<Shape> shapes) {
        return shapes.stream().mapToDouble(Shape::area).sum();
    }
}
