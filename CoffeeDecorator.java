/**
 * The CoffeeDecorator class is an abstract class that implements the
 * Coffee interface and serves as a base class for coffee decorators.
 */
import java.util.List;

public abstract class CoffeeDecorator implements Coffee {
    private Coffee coffee;

    public CoffeeDecorator(Coffee c) {
        coffee = c;
    }

    public double getCost() { return coffee.getCost(); }

    public List<String> getIngredients() {
        return null;
    }

    public String printCoffee() {
        return coffee.printCoffee();
    }
}
