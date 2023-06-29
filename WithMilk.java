/**
 * Implementation of the Coffee interface for Milk.
 */
import java.util.List;

public class WithMilk extends CoffeeDecorator {
    public WithMilk(Coffee c) {
        super(c);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.55;
    }
    /**
     * Returns the list of ingredients for Milk additive.
     *
     * @return ingredient Milk
     */
    @Override
    public List<String> getIngredients() {
        List<String> ingredients = super.getIngredients();
        ingredients.add("Milk");

        return ingredients;
    }

    @Override
    public String printCoffee() {
        return super.printCoffee() + " with milk";
    }
}
