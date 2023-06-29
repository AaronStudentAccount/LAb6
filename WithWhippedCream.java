/**
 * Implementation of the Coffee interface for Whipped Cream.
 */
import java.util.List;

public class WithWhippedCream extends CoffeeDecorator {
    public WithWhippedCream(Coffee c) {
        super(c);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.25;
    }
    /**
     * Returns the list of ingredients for Whipped Cream additive.
     *
     * @return ingredient Whipped Cream
     */
    @Override
    public List<String> getIngredients() {
        List<String> ingredients = super.getIngredients();
        ingredients.add("Whipped cream");

        return ingredients;
    }

    @Override
    public String printCoffee() {
        return super.printCoffee() + " with whipped cream";
    }
}
