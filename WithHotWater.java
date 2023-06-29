/**
 * Implementation of the Coffee interface for Hot Water.
 */
import java.util.List;

public class WithHotWater extends CoffeeDecorator {
    public WithHotWater(Coffee c) {
        super(c);
    }

    @Override
    public double getCost() {
        return super.getCost();
    }
    /**
     * Returns the list of ingredients for Hot Water
     *
     * @return ingredient Hot Water
     */
    @Override
    public List<String> getIngredients() {
        List<String> ingredients = super.getIngredients();
        ingredients.add("Hot water");

        return ingredients;
    }

    @Override
    public String printCoffee() {
        return super.printCoffee() + " with hot water";
    }
}
