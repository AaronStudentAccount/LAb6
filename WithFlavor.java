/**
 * Implementation of the Coffee interface for Milk.
 */
import java.util.List;

public class WithFlavor extends CoffeeDecorator {
    /**
     * Enum of  possible syrup flavors
     */
    enum Syrup {
        CARAMEL,
        MOCHA,
        VANILLA
    }

    private Syrup flavor;

    public WithFlavor(Coffee c, Syrup s) {
        super(c);
        flavor = s;
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.35;
    }
    /**
     * Returns the list of ingredients for Syrup flavor.
     *
     * @return the list of ingredients for Syrup flavor
     */
    @Override
    public List<String> getIngredients() {


        List<String> ingredients = super.getIngredients();
        ingredients.add(flavor.toString().toLowerCase() + " Syrup");
        return ingredients;
    }

    @Override
    public String printCoffee() {
        return super.printCoffee() + " with " + flavor;
    }
}
