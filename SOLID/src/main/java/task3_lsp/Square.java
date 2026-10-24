package task3_lsp;

public record Square(double side) implements Shape {

    public Square {
        if (side <= 0) {
            throw new IllegalArgumentException("Side must be positive.");
        }
    }

    public Square withSide(double newSide) {
        return new Square(newSide);
    }

    @Override
    public double area() {
        return side * side;
    }
}
