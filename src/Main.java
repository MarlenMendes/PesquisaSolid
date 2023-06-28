import java.util.List;

// Dependency Inversion Principle
interface Shape {
    default double area() {
        return 0;
    }
}

// Open/Closed Principle
class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * Math.pow(radius, 2);
    }
}

// Liskov Substitution Principle
class Rectangle implements Shape {
    private double width, height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }
}

// Single Responsibility Principle
class AreaCalculator {
    public double totalArea(List<Shape> shapes) {
        double total = 0;
        for (Shape shape : shapes) total += shape.area();
        return total;
    }
}

// Interface Segregation Principle
interface Printer {
    void print(String message);
}

class ConsolePrinter implements Printer {
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}

class ReportGenerator {
    private Printer printer;

    public ReportGenerator(Printer printer) {
        this.printer = printer;
    }

    public void generateReport(List<Shape> shapes, AreaCalculator areaCalculator) {
        double totalArea = areaCalculator.totalArea(shapes);
        printer.print("Total area: " + totalArea);
    }
}

public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle(5);
        Rectangle rectangle = new Rectangle(4, 3);

        AreaCalculator areaCalculator = new AreaCalculator();
        ConsolePrinter consolePrinter = new ConsolePrinter();
        ReportGenerator reportGenerator = new ReportGenerator(consolePrinter);

        List<Shape> shapes = List.of(circle, rectangle);
        reportGenerator.generateReport(shapes, areaCalculator);
    }
}