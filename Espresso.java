/**
 * Implementation of the Coffee interface for Espresso.
 */
import java.util.ArrayList;
import java.util.List;

public class Espresso implements Coffee {
    @Override
    public double getCost() {
        return 1.75;
    }
    /**
     * Returns the list of ingredients for Espresso.
     *
     * @return the list of ingredients for Espresso
     */
    @Override
    public List<String> getIngredients() {

        List<String> ingredients = new ArrayList<String>();
        ingredients.add("Espresso");
       return ingredients;
    }

    @Override
    public String printCoffee() {
        return "An Espresso";
    }
}

