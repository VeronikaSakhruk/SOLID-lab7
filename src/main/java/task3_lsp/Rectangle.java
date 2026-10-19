package task3_lsp;

public record Rectangle(double width, double height) implements Shape {

    public Rectangle {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and height must be positive.");
        }
    }

    public Rectangle withWidth(double newWidth) {
        return new Rectangle(newWidth, height);
    }

    public Rectangle withHeight(double newHeight) {
        return new Rectangle(width, newHeight);
    }

    @Override
    public double area() {
        return width * height;
    }
}
